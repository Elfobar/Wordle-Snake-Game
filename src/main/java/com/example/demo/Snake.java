import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Snake {
    private List<Coordinate> body;
    private GridPane gridPane;
    private List<Rectangle> snakeParts;
    private Direction direction;
    private final int startLength;
    private boolean isAlive;
    private final String headPath = "/images/snakeHead.png";
    private final String segmentPath = "/images/snakeSegment.png";
    private final String tailPath = "/images/snakeTail.png";
    private final Image headImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream(headPath)));
    private final Image segmentImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream(segmentPath)));
    private final Image tailImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tailPath)));

    public Snake(GridPane gridPane, int startLength){
        this.snakeParts = new ArrayList<>();
        this.startLength = startLength;
        this.gridPane = gridPane;
        this.isAlive = true;
        this.direction = Direction.UP;
        this.body = new ArrayList<>();
        initializeSnake();
    }

    private void initializeSnake() {
        int middleColumn = Game.COLUMNS / 2;
        int middleRow = Game.ROWS / 2;

        for (int i = 0; i < startLength; i++) {
            body.add(new Coordinate(middleColumn + i, middleRow));
        }
    }

    public void drawSnake(){
        gridPane.getChildren().removeAll(snakeParts);
        snakeParts.clear();

        for(int i = 0; i < body.size(); i++) {
            Rectangle square = new Rectangle(Game.CELL_SIZE, Game.CELL_SIZE);

            Glow glow = new Glow();
            glow.setLevel(0.2);
            square.setEffect(glow);

            if (i == 0) {
                square.setFill(new ImagePattern(headImg));
            } else if (i == body.size() - 1){
                square.setFill(new ImagePattern(tailImg));
            }else{
                square.setFill(new ImagePattern(segmentImg));
            }
            snakeParts.add(square);
            gridPane.add(square, body.get(i).getX(), body.get(i).getY());
        }
    }

    public void moveSnake(){
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setX(body.get(i - 1).getX());
            body.get(i).setY(body.get(i - 1).getY());
        }

        Coordinate head = getHead();
        head.setX(head.getX() + direction.getX());
        head.setY(head.getY() + direction.getY());

        if (head.getX() >= Game.COLUMNS) {
            head.setX(0);
        } else if (head.getX() < 0) {
            head.setX(Game.COLUMNS - 1);
        }
        if (head.getY() >= Game.ROWS) {
            head.setY(0);
        } else if (head.getY() < 0) {
            head.setY(Game.ROWS - 1);
        }
        checkIfCollided();
    }

    public void changeDirection(KeyCode keyCode) {
        Direction currentDirection = direction;
        Direction newDirection = direction.getDirectionFromKeyCode(keyCode);
        if (newDirection != currentDirection.getOpposite()) {
            this.direction = newDirection;
        }
    }

    public void rotateHead(){
        Rectangle head = snakeParts.get(0);
        if(direction == Direction.UP){
            head.setRotate(180);
        } else if(direction == Direction.DOWN){
            head.setRotate(360);
        } else if(direction == Direction.LEFT){
            head.setRotate(90);
        } else{
            head.setRotate(-90);
        }
    }

    public void rotateTail(){
        Rectangle tailRect = snakeParts.get(body.size() - 1);
        Coordinate tail = body.get(body.size() - 1);
        Coordinate lastBody = body.get(body.size() - 2);

        if(tail.getX() == lastBody.getX()) {
            if (tail.getY() > lastBody.getY()) {
                tailRect.setRotate(360);
            } else {
                tailRect.setRotate(180);
            }
        } else if(tail.getY() == lastBody.getY()) {
            if (tail.getX() > lastBody.getX()) {
                tailRect.setRotate(-90);
            } else {
                tailRect.setRotate(90);
            }
        }
    }

    public void checkIfCollided(){
        boolean isCollided = false;
        for(int i = 1; i < body.size(); i++){
            if(getHead().equals(body.get(i))){
                isCollided = true;
            }
        }
        if(isCollided){
            this.isAlive = false;
        }
    }

    public void grow(){
        Coordinate tail = body.get(body.size() - 1);
        body.add(new Coordinate(tail.getX(), tail.getY()));
    }

    public Coordinate getHead(){
        return body.get(0);
    }

    public boolean isAlive() {
        return isAlive;
    }
}
