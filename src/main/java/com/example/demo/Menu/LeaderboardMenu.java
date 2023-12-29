package com.example.demo.Menu;

import com.example.demo.GameConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    }

    public StackPane createContent(){
        StackPane stackPane = createStackPane();
        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox leaderboardBox = createLeaderboardBox();

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

    private VBox createLeaderboardBox() {
        VBox leaderboardBox = new VBox();
        leaderboardBox.setAlignment(Pos.TOP_CENTER);
        leaderboardBox.setPrefHeight(LEADERBOARD_BOX_HEIGHT);
        leaderboardBox.setPrefWidth(LEADERBOARD_BOX_WIDTH);

        ImageView label = createLeaderboardLabel();
        leaderboardBox.getChildren().add(label);

        return leaderboardBox;
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
