package com.example.demo.UI.Menu;

import com.example.demo.GameCore.GameConfig;
import com.example.demo.Util.Util;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class ScoreboardMenu extends AbstractMenu {// Final constants to be used in other methods.
    private static final double SCOREBOARD_BOX_HEIGHT = 200;
    private static final double SCOREBOARD_BOX_WIDTH = 100;
    private static final String SCOREBOARD_LABEL_IMAGE = "ScoreboardLabel";
    private static final double SCOREBOARD_LABEL_WIDTH = 650;
    private static final double SCOREBOARD_LABEL_HEIGHT = 180;
    private static final String BACK_BUTTON_IMAGE = "ArrowsImg";
    private static final double BACK_BUTTON_WIDTH = 150;
    private static final double BACK_BUTTON_HEIGHT = 130;
    private final Font font;


    public ScoreboardMenu(){
        super();
        this.font = Util.loadCustomFont();// Initializes custom font.
    }

    public StackPane createContent(){ // Creates background and buttons and returns it.git 
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

    private BorderPane createLeaderboardBox() {// Displays the elements of the scoreboard.
        BorderPane pane = new BorderPane();
        VBox scoreboardBox = new VBox();
        pane.setTop(scoreboardBox);
        scoreboardBox.setAlignment(Pos.TOP_CENTER);
        scoreboardBox.setPrefHeight(SCOREBOARD_BOX_HEIGHT);
        scoreboardBox.setPrefWidth(SCOREBOARD_BOX_WIDTH);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setSpacing(10.0);
        vbox.setPadding(new Insets(0, 0, 0, 180.0));
        ArrayList<Integer> scores = Util.getHighestScoresFromFile();
        // Array representing places in the leaderboard
        String[] places = {"First: ", "Second: ", "Third: ", "Fourth: ", "Fifth: "};

        for (int i = 0; i < Math.min(scores.size(), places.length); i++) {
            Label label = new Label(places[i] + scores.get(i));
            label.setFont(this.font);

            Glow glow = new Glow();
            glow.setLevel(1.0-(i * 0.2));

            label.setEffect(glow);
            label.setTextFill(Color.web("#aeb7ff"));
            vbox.getChildren().add(label);
        }
        pane.setCenter(vbox);


        ImageView label = createScoreboardLabel();
        scoreboardBox.getChildren().add(label);

        return pane;
    }

    private ImageView createScoreboardLabel() {// Sets the label and dimensions.
        ImageView label = new ImageView(cache.getImage(SCOREBOARD_LABEL_IMAGE));
        label.setMouseTransparent(true);
        label.setPreserveRatio(true);
        label.setFitWidth(SCOREBOARD_LABEL_WIDTH);
        label.setFitHeight(SCOREBOARD_LABEL_HEIGHT);
        return label;
    }

    public ImageView getBackButton(){ // Shows the back button and sets the dimensions.

        ImageView back = new ImageView(cache.getImage(BACK_BUTTON_IMAGE));
        back.setId("back");
        back.setFitWidth(BACK_BUTTON_WIDTH);
        back.setFitHeight(BACK_BUTTON_HEIGHT);
        back.setPreserveRatio(true);

        return back;
    }
}
