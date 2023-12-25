package com.example.demo.Menu;

import com.example.demo.SnakeConfig;
import com.example.demo.SnakeGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LeaderboardMenu extends AbstractMenu {

    public LeaderboardMenu(){
        super();
    }

    public StackPane createContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(SnakeConfig.ROWS*SnakeConfig.CELL_SIZE, SnakeConfig.COLUMNS*SnakeConfig.CELL_SIZE + SnakeConfig.HEADER_SPACE);
        stackPane.setAlignment(Pos.BOTTOM_LEFT);

        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox leaderboardBox = new VBox();
        leaderboardBox.setAlignment(Pos.TOP_CENTER);
        leaderboardBox.setPrefHeight(200);
        leaderboardBox.setPrefWidth(100);

        ImageView label = new ImageView(cache.getImage("LeaderboardLabel"));
        label.setPickOnBounds(true);
        label.setPreserveRatio(true);
        label.setFitWidth(550);
        label.setFitHeight(150);

        ImageView back = getBackButton();

        leaderboardBox.getChildren().add(label);

        stackPane.getChildren().add(leaderboardBox);
        StackPane.setMargin(back, new Insets(0, 0, 50, 50));
        stackPane.getChildren().add(back);

        return stackPane;
    }

    public ImageView getBackButton(){

        ImageView back = new ImageView(cache.getImage("ArrowsImg"));
        back.setId("back");
        back.setFitWidth(150.0);
        back.setFitHeight(130.0);
        back.setPreserveRatio(true);

        return back;
    }

}
