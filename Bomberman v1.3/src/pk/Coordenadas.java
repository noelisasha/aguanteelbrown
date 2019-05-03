package pk;

public class Coordenadas {
	private double xPos;
	private double yPos;

	public Coordenadas(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public Coordenadas suma(double xPos, double yPos) {
		Coordenadas resul = new Coordenadas(this.xPos + xPos, this.yPos + yPos);
		return resul;
	}

	public double compararX(Coordenadas cord) {
		return this.xPos - cord.xPos;
	}

	public double compararY(Coordenadas cord) {
		return this.yPos - cord.yPos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(xPos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(yPos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Coordenadas other = (Coordenadas) obj;
		if (Double.doubleToLongBits(xPos) != Double.doubleToLongBits(other.xPos))
			return false;
		if (Double.doubleToLongBits(yPos) != Double.doubleToLongBits(other.yPos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "( " + xPos + ", " + yPos + ")";
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

}
