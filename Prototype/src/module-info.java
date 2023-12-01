module org.apache.maven {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.apache.maven to javafx.fxml;
    exports org.apache.maven.SnakeGame;
}
