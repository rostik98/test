package rooms.servlets;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rooms.domain.Coordinates;
import rooms.domain.Room;
import rooms.service.RoomService;
import rooms.service.impl.RoomServiceImpl;

/**
 * Servlet implementation class AddingRoomServlet
 */
public class AddingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddingRoomServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoomService roomService = null;
		try {
			roomService = new RoomServiceImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | SQLException e1) {
			e1.printStackTrace();
		}

		String name = request.getParameter("namee");
		Room q = new Room(name);
		int coords = Integer.parseInt(request.getParameter("noofcoords"));
		double maxSquare = Double.parseDouble(request.getParameter("square"));
		int check = 0;
		if (coords % 2 != 0 || coords < 4) {
			System.out.println("Must be at least 4 points!");
		} else {
			check = checkRightAngle(request, coords, check);
			if (check != 0) {
				System.out.println("not 90 degrees or overlay points!");
			}
			check = checkIntersectsLines(request, coords, check);
			if (check != 0) {
				System.out.println("INTERSECTED LINES!");
			} else {
				double square = squareOfFigure(request, coords);
				if (square <= 0) {
					System.out.println("anticlockwise");
				} else if (maxSquare < Math.abs(square)) {
					System.out.println("Square of room must be <= " + maxSquare);
				} else {
					insert(request, roomService, q, coords);
				}
			}
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);/////// *******************
	}

	/**
	 * @param request
	 * @param roomService
	 * @param q
	 * @param coords
	 */
	private void insert(HttpServletRequest request, RoomService roomService, Room q, int coords) {
		for (int i = 1; i <= coords; i++) {
			int x = Integer.parseInt(request.getParameter("X" + i));
			int y = Integer.parseInt(request.getParameter("Y" + i));
			Coordinates c = new Coordinates(x, y);

			try {
				roomService.create(q, c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param request
	 * @param coords
	 * @param check
	 * @return
	 */
	private int checkIntersectsLines(HttpServletRequest request, int coords, int check) {
		List<Point2D> parr = new LinkedList<>();
		List<Line2D> larr = new LinkedList<>();
		for (int i = 1; i <= coords; i++) {
			int xi = Integer.parseInt(request.getParameter("X" + i));
			int yi = Integer.parseInt(request.getParameter("Y" + i));
			Point2D pi = new Point2D.Double(xi, yi);
			parr.add(pi);
		}
		for (int i = 0; i < parr.size(); i++) {
			if (i < parr.size() - 1) {
				Line2D line = new Line2D.Double(parr.get(i), parr.get(i + 1));
				larr.add(line);
			} else {
				Line2D line = new Line2D.Double(parr.get(i), parr.get(1));
				larr.add(line);
			}
		}
		for (int i = 0; i < larr.size(); i++) {
			for (int j = i + 2; j < larr.size() - 1; j++) {
				boolean b = larr.get(i).intersectsLine(larr.get(j));
				if (b) {
					check++;
				}
			}
		}
		return check;
	}

	/**
	 * @param request
	 * @param coords
	 * @param check
	 * @return
	 */
	private int checkRightAngle(HttpServletRequest request, int coords, int check) {
		for (int i = 1; i <= coords; i++) {
			int xi = Integer.parseInt(request.getParameter("X" + i));
			int yi = Integer.parseInt(request.getParameter("Y" + i));
			int x1 = Integer.parseInt(request.getParameter("X" + 1));
			int y1 = Integer.parseInt(request.getParameter("Y" + 1));
			if (i < coords) {
				int xi1 = Integer.parseInt(request.getParameter("X" + (i + 1)));
				int yi1 = Integer.parseInt(request.getParameter("Y" + (i + 1)));
				if ((xi != xi1 && yi != yi1) || (xi == xi1 && yi == yi1)) {
					check++;
				}
			} else {
				if ((xi != x1 && yi != y1) || (xi == x1 && yi == y1)) {
					check++;
				}
			}
		}
		return check;
	}

	private double squareOfFigure(HttpServletRequest request, int coords) {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 1; i <= coords; i++) {
			if (i < coords) {
				sum1 += Integer.parseInt(request.getParameter("X" + i))
						* Integer.parseInt(request.getParameter("Y" + (i + 1)));
				sum2 += Integer.parseInt(request.getParameter("Y" + i))
						* Integer.parseInt(request.getParameter("X" + (i + 1)));
			} else {
				sum1 += Integer.parseInt(request.getParameter("X" + i))
						* Integer.parseInt(request.getParameter("Y" + 1));
				sum2 += Integer.parseInt(request.getParameter("Y" + i))
						* Integer.parseInt(request.getParameter("X" + 1));
			}
		}
		double squareWithoutABS = (sum2 - sum1) / 2;
		return squareWithoutABS;

	}
}
