package rooms.servlets;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

import rooms.domain.Coordinates;
import rooms.service.RoomService;
import rooms.service.impl.RoomServiceImpl;

public class ShowingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoomService roomService = null;
		try {
			roomService = new RoomServiceImpl();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = request.getParameter("name");
		List<Point2D> points = new LinkedList<Point2D>();
		try {
			List<Coordinates> readAll = roomService.readAll(name);
			Iterator<Coordinates> iterator = readAll.iterator();
			while (iterator.hasNext()) {
				Coordinates coordinates = (Coordinates) iterator.next();
				points.add(new Point2D.Double(coordinates.getX(), coordinates.getY()));
			}
			request.setAttribute("AmountOfPoints", points.size());
			for (int i = 0; i < points.size(); i++) {
				request.setAttribute("X" + (i + 1), points.get(i).getX());
				request.setAttribute("Y" + (i + 1), points.get(i).getY());
			}
			final String title = "Test Window";
			final int width = 2000;
			final int height = width / 16 * 9;

			// Creating the frame.
			JFrame frame = new JFrame(title);

			frame.setSize(width, height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setVisible(true);

			// Creating the canvas.
			Canvas canvas = new Canvas();

			canvas.setSize(width, height);
			canvas.setBackground(Color.BLACK);
			canvas.setVisible(true);
			canvas.setFocusable(false);

			// Putting it all together.
			frame.add(canvas);

			canvas.createBufferStrategy(3);
			boolean running = true;
			BufferStrategy bufferStrategy;
			Graphics graphics;

			while (running) {

				bufferStrategy = canvas.getBufferStrategy();
				graphics = bufferStrategy.getDrawGraphics();
				graphics.clearRect(0, 0, width, height);

				graphics.setColor(Color.GREEN);
				graphics.drawString("This is some text placed in the top left corner.", 5, 15);
				for (int i = 0; i < readAll.size() - 1; i++) {
					double d = 0 + (readAll.get(i).getX() * 50 - 0) * Math.cos(Math.PI / 2)
							- (readAll.get(i).getY() * 50 - 0) * Math.sin(Math.PI / 2);
					double d1 = 0 + (readAll.get(i).getX() * 50 - 0) * Math.sin(Math.PI / 2)
							+ (readAll.get(i).getY() * 50 - 0) * Math.cos(Math.PI / 2);
					double d2 = 0 + (readAll.get(i + 1).getX() * 50 - 0) * Math.cos(Math.PI / 2)
							- (readAll.get(i + 1).getY() * 50 - 0) * Math.sin(Math.PI / 2);
					double d3 = 0 + (readAll.get(i + 1).getX() * 50 - 0) * Math.sin(Math.PI / 2)
							+ (readAll.get(i + 1).getY() * 50 - 0) * Math.cos(Math.PI / 2);
					graphics.drawLine((int) d + 400, (int) d1 + 200, (int) d2 + 400, (int) d3 + 200);

				}
				double d4 = 0 + (readAll.get(readAll.size() - 1).getX() * 50 - 0) * Math.cos(Math.PI / 2)
						- (readAll.get(readAll.size() - 1).getY() * 50 - 0) * Math.sin(Math.PI / 2);
				double d5 = 0 + (readAll.get(readAll.size() - 1).getX() * 50 - 0) * Math.sin(Math.PI / 2)
						+ (readAll.get(readAll.size() - 1).getY() * 50 - 0) * Math.cos(Math.PI / 2);
				double d6 = 0 + (readAll.get(0).getX() * 50 - 0) * Math.cos(Math.PI / 2)
						- (readAll.get(0).getY() * 50 - 0) * Math.sin(Math.PI / 2);
				double d7 = 0 + (readAll.get(0).getX() * 50 - 0) * Math.sin(Math.PI / 2)
						+ (readAll.get(0).getY() * 50 - 0) * Math.cos(Math.PI / 2);
				graphics.drawLine((int) d4 + 400, (int) d5 + 200, (int) d6 + 400, (int) d7 + 200);
				bufferStrategy.show();
				graphics.dispose();
			}
			// System.out.println(points);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
