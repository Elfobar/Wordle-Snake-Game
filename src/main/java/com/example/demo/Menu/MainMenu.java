package com.example.demo.Menu;

import com.example.demo.SnakeConfig;
import com.example.demo.SnakeGame;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenu extends AbstractMenu {


    public MainMenu(){
        super();
    }
    @Override
    public StackPane createContent() {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(SnakeConfig.ROWS*SnakeConfig.CELL_SIZE, SnakeConfig.COLUMNS*SnakeConfig.CELL_SIZE + SnakeConfig.HEADER_SPACE);
        stackPane.setAlignment(Pos.TOP_CENTER);

        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setSpacing(15.0);
        menuBox.setPrefSize(SnakeConfig.ROWS*SnakeConfig.CELL_SIZE, SnakeConfig.COLUMNS*SnakeConfig.CELL_SIZE + SnakeConfig.HEADER_SPACE);

        ImageView logo = new ImageView(cache.getImage("NameImg"));
        logo.setFitWidth(800.0);
        logo.setFitHeight(180.0);
        logo.setPickOnBounds(true);
        logo.setPreserveRatio(true);

        ImageView startLabel = new ImageView(cache.getImage("StartLabel"));
        startLabel.setId("start");
        startLabel.setFitWidth(180.0);
        startLabel.setFitHeight(150.0);
        startLabel.setPickOnBounds(true);
        startLabel.setPreserveRatio(true);

        ImageView settingsLabel = new ImageView(cache.getImage("SettingsLabel"));
        settingsLabel.setId("settings");
        settingsLabel.setFitWidth(230.0);
        settingsLabel.setFitHeight(150.0);
        settingsLabel.setPickOnBounds(true);
        settingsLabel.setPreserveRatio(true);

        ImageView leaderboardLabel = new ImageView(cache.getImage("LeaderboardLabel"));
        leaderboardLabel.setId("leaderboard");
        leaderboardLabel.setFitWidth(330.0);
        leaderboardLabel.setFitHeight(150.0);
        leaderboardLabel.setPickOnBounds(true);
        leaderboardLabel.setPreserveRatio(true);

        ImageView exitLabel = new ImageView(cache.getImage("ExitLabel"));
        exitLabel.setId("exit");
        exitLabel.setFitWidth(150.0);
        exitLabel.setFitHeight(130.0);
        exitLabel.setPickOnBounds(true);
        exitLabel.setPreserveRatio(true);

        StackPane firstBtnPane = createButton(startLabel);
        StackPane secondBtnPane = createButton(settingsLabel);
        StackPane thirdBtnPane = createButton(leaderboardLabel);
        StackPane fourthBtnPane = createButton(exitLabel);

        menuBox.getChildren().add(logo);
        menuBox.getChildren().add(firstBtnPane);
        menuBox.getChildren().add(secondBtnPane);
        menuBox.getChildren().add(thirdBtnPane);
        menuBox.getChildren().add(fourthBtnPane);

        stackPane.getChildren().add(menuBox);

        return stackPane;
    }

    private StackPane createButton(ImageView label){
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
