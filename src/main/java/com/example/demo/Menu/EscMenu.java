package com.example.demo.Menu;

import com.example.demo.SnakeGame;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EscMenu extends AbstractMenu {


    public EscMenu(){
        super("Menu");
    }

    public StackPane createContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(SnakeGame.ROWS*SnakeGame.CELL_SIZE, SnakeGame.COLUMNS*SnakeGame.CELL_SIZE + SnakeGame.HEADER_SPACE);
        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setSpacing(30.0);

        ImageView continueLabel = new ImageView(cache.getImage("ContinueLabel"));
        continueLabel.setFitWidth(280.0);
        continueLabel.setFitHeight(70.0);
        continueLabel.setId("continue");

        StackPane continueBtn = getMenuButton(continueLabel);

        ImageView exitLabel = new ImageView(cache.getImage("ExitLabel"));
        exitLabel.setFitWidth(130.0);
        exitLabel.setFitHeight(65.0);
        exitLabel.setId("back");
        StackPane exitBtn = getMenuButton(exitLabel);

        menuBox.getChildren().add(continueBtn);
        menuBox.getChildren().add(exitBtn);

        stackPane.getChildren().add(menuBox);

        return stackPane;
    }

    public StackPane getMenuButton(ImageView label){
        StackPane button = new StackPane();
        button.setAlignment(Pos.CENTER);

        ImageView frame = new ImageView(cache.getImage("ButtonFrame"));
        frame.setFitWidth(400.0);
        frame.setFitHeight(150.0);
        frame.setPickOnBounds(true);
        frame.setPreserveRatio(true);
        frame.setId(label.getId());

        button.getChildren().addAll(frame, label);

        return button;
    }
}
