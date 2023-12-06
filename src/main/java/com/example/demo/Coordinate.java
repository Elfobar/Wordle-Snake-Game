public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y= y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object other) {

        boolean isEqual = false;

        if(other == null){
            isEqual = false;
        } else if (other == this) {
            isEqual = true;
        } else if (other instanceof Coordinate) {
            Coordinate otherCoordinate = (Coordinate) other;
            isEqual = this.x == otherCoordinate.x && this.y == otherCoordinate.y;
        } else{
            isEqual = false;
        }
        return isEqual;
    }
}