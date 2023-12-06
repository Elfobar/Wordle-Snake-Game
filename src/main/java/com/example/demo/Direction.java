import javafx.scene.input.KeyCode;

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final int x;
    public final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction getOpposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return null;
        }
    }

    public Direction getDirectionFromKeyCode(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                return UP;
            case DOWN:
                return DOWN;
            case LEFT:
                return LEFT;
            case RIGHT:
                return RIGHT;
            default:
                return null;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

