package com.example.demo.Menu;

import com.example.demo.AppConfig;
import com.example.demo.GameConfig;
import com.example.demo.Util;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class LeaderboardMenu extends AbstractMenu {

    private static final double LEADERBOARD_BOX_HEIGHT = 200;
    private static final double LEADERBOARD_BOX_WIDTH = 100;
    private static final String LEADERBOARD_LABEL_IMAGE = "LeaderboardLabel";
    private static final double LEADERBOARD_LABEL_WIDTH = 550;
    private static final double LEADERBOARD_LABEL_HEIGHT = 150;
    private static final String BACK_BUTTON_IMAGE = "ArrowsImg";
    private static final double BACK_BUTTON_WIDTH = 150;
    private static final double BACK_BUTTON_HEIGHT = 130;


    public LeaderboardMenu(){
        super();
        initialize();
    }

    public StackPane createContent(){
        StackPane stackPane = createStackPane();
        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

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

    private void initialize() {
        BorderPane leaderboardBox = createLeaderboardBox();
        ArrayList<Integer> scores = Util.readScores(); // Use the readScores method from Util
        populateLeaderboard(leaderboardBox, scores);
    }

    private void populateLeaderboard(BorderPane leaderboardBox, ArrayList<Integer> scores) {
        for (int i = 0; i < scores.size(); i++) {
            Label scoreLabel = new Label((i + 1) + ". " + scores.get(i));
            leaderboardBox.getChildren().add(scoreLabel);
        }
    }

    private BorderPane createLeaderboardBox() {
        BorderPane pane = new BorderPane();
        VBox leaderboardBox = new VBox();
        pane.setTop(leaderboardBox);
        leaderboardBox.setAlignment(Pos.TOP_CENTER);
        leaderboardBox.setPrefHeight(LEADERBOARD_BOX_HEIGHT);
        leaderboardBox.setPrefWidth(LEADERBOARD_BOX_WIDTH);


        VBox vbox = new VBox();
        ArrayList<Integer> scores = Util.getHighestScoresFromFile();
        for(int i = 0; i < scores.size(); i++) {
            Label label1 = new Label();
            if(i == 0){
                label1 = new Label("First: " + scores.get(i).toString());
            }
            if(i == 1){
                label1 = new Label("Second: " + scores.get(i).toString());
            }
            if(i == 2){
                label1 = new Label("Third: " + scores.get(i).toString());
            }
            label1.setFont(Font.font(20));
            vbox.getChildren().add(label1);
            label1.setTextFill(Color.LIGHTBLUE);
        }
        pane.setCenter(vbox);


        ImageView label = createLeaderboardLabel();
        leaderboardBox.getChildren().add(label);

        return pane;
    }



    private ImageView createLeaderboardLabel() {
        ImageView label = new ImageView(cache.getImage(LEADERBOARD_LABEL_IMAGE));
        label.setPickOnBounds(true);
        label.setPreserveRatio(true);
        label.setFitWidth(LEADERBOARD_LABEL_WIDTH);
        label.setFitHeight(LEADERBOARD_LABEL_HEIGHT);
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
