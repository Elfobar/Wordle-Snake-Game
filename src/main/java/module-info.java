module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    exports com.example.demo.UI.Menu;
    opens com.example.demo.UI.Menu to javafx.fxml;
    exports com.example.demo.Util;
    opens com.example.demo.Util to javafx.fxml;
    exports com.example.demo.Game;
    opens com.example.demo.Game to javafx.fxml;
    exports com.example.demo.GameCore;
    opens com.example.demo.GameCore to javafx.fxml;
    exports com.example.demo.UI;
    opens com.example.demo.UI to javafx.fxml;
}