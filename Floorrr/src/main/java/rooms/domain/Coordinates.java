package rooms.domain;

public class Coordinates {
	private int id;
	private int x;
	private int y;

	/**
	 * @param id
	 * @param x
	 * @param y
	 */
	public Coordinates(int id, int x, int y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
	}

	/**
	 * @param x
	 * @param y
	 */
	public Coordinates(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coordinates [id=" + id + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (id != other.id)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
