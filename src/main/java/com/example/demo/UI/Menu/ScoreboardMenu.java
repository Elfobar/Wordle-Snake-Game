package com.example.demo.UI.Menu;

import com.example.demo.GameCore.GameConfig;
import com.example.demo.Util.Util;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashSet;

public class ScoreboardMenu extends AbstractMenu {
    private static final double SCOREBOARD_BOX_HEIGHT = 200;
    private static final double SCOREBOARD_BOX_WIDTH = 100;
    private static final String SCOREBOARD_LABEL_IMAGE = "ScoreboardLabel";
    private static final double SCOREBOARD_LABEL_WIDTH = 550;
    private static final double SCOREBOARD_LABEL_HEIGHT = 150;
    private static final String BACK_BUTTON_IMAGE = "ArrowsImg";
    private static final double BACK_BUTTON_WIDTH = 150;
    private static final double BACK_BUTTON_HEIGHT = 130;


    public ScoreboardMenu(){
        super();
    }

    public StackPane createContent(){
        StackPane stackPane = createStackPane();
        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        //VBox scoreboardBox = createLeaderboardBox();
        BorderPane leaderboardBox = createLeaderboardBox();

        ImageView back = getBackButton();

        StackPane.setMargin(back, new Insets(0, 0, 50, 50));
        stackPane.getChildren().addAll(leaderboardBox, back);

        return stackPane;
    }

    private StackPane createStackPane(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT);
        stackPane.setAlignment(Pos.BOTTOM_LEFT);
        return stackPane;
    }

    private BorderPane createLeaderboardBox() {
        BorderPane pane = new BorderPane();
        VBox scoreboardBox = new VBox();
        pane.setTop(scoreboardBox);
        scoreboardBox.setAlignment(Pos.TOP_CENTER);
        scoreboardBox.setPrefHeight(SCOREBOARD_BOX_HEIGHT);
        scoreboardBox.setPrefWidth(SCOREBOARD_BOX_WIDTH);

        VBox vbox = new VBox();
        ArrayList<Integer> scores = Util.getHighestScoresFromFile();
        String[] places = {"First: ", "Second: ", "Third: ", "Fourth: ", "Fifth: "};

        for (int i = 0; i < Math.min(scores.size(), places.length); i++) {
            Label label = new Label(places[i] + ": " + scores.get(i));
            label.setFont(Font.font(20));
            label.setTextFill(Color.LIGHTBLUE);
            vbox.getChildren().add(label);
        }
        pane.setCenter(vbox);


        ImageView label = createScoreboardLabel();
        scoreboardBox.getChildren().add(label);

        return pane;
    }

    private ImageView createScoreboardLabel() {
        ImageView label = new ImageView(cache.getImage(SCOREBOARD_LABEL_IMAGE));
        label.setPickOnBounds(true);
        label.setPreserveRatio(true);
        label.setFitWidth(SCOREBOARD_LABEL_WIDTH);
        label.setFitHeight(SCOREBOARD_LABEL_HEIGHT);
        return label;
    }

    public ImageView getBackButton(){

        ImageView back = new ImageView(cache.getImage(BACK_BUTTON_IMAGE));
        back.setId("back");
        back.setFitWidth(BACK_BUTTON_WIDTH);
        back.setFitHeight(BACK_BUTTON_HEIGHT);
        back.setPreserveRatio(true);

        return back;
    }
}
