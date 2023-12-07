package com.example.demo;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class Menu {

    private int state;

    public Menu() {
        this.state = 0;
    }

    public StackPane createMenuContent() {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(800, 800);
        stackPane.setAlignment(Pos.TOP_CENTER);

        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setSpacing(15.0);
        menuBox.setPrefSize(800, 800);

        ImageView logo = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/NameImg.png"))));
        logo.setFitWidth(800.0);
        logo.setFitHeight(180.0);
        logo.setPickOnBounds(true);
        logo.setPreserveRatio(true);

        ImageView startLabel = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/StartLabel.png"))));
        startLabel.setId("start");
        startLabel.setFitWidth(180.0);
        startLabel.setFitHeight(150.0);
        startLabel.setPickOnBounds(true);
        startLabel.setPreserveRatio(true);

        ImageView settingsLabel = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SettingsLabel.png"))));
        settingsLabel.setId("settings");
        settingsLabel.setFitWidth(230.0);
        settingsLabel.setFitHeight(150.0);
        settingsLabel.setPickOnBounds(true);
        settingsLabel.setPreserveRatio(true);

        ImageView leaderboardLabel = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/LeaderboardLabel.png"))));
        leaderboardLabel.setId("leaderboard");
        leaderboardLabel.setFitWidth(330.0);
        leaderboardLabel.setFitHeight(150.0);
        leaderboardLabel.setPickOnBounds(true);
        leaderboardLabel.setPreserveRatio(true);

        ImageView exitLabel = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ExitLabel.png"))));
        exitLabel.setId("exit");
        exitLabel.setFitWidth(150.0);
        exitLabel.setFitHeight(130.0);
        exitLabel.setPickOnBounds(true);
        exitLabel.setPreserveRatio(true);

        StackPane firstBtnPane = getMenuButton(startLabel);
        StackPane secondBtnPane = getMenuButton(settingsLabel);
        StackPane thirdBtnPane = getMenuButton(leaderboardLabel);
        StackPane fourthBtnPane = getMenuButton(exitLabel);

        menuBox.getChildren().add(logo);
        menuBox.getChildren().add(firstBtnPane);
        menuBox.getChildren().add(secondBtnPane);
        menuBox.getChildren().add(thirdBtnPane);
        menuBox.getChildren().add(fourthBtnPane);

        stackPane.getChildren().add(menuBox);

        return stackPane;
    }

    public StackPane createLeaderboardContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(800, 800);
        stackPane.setAlignment(Pos.BOTTOM_LEFT);

        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox leaderboardBox = new VBox();
        leaderboardBox.setAlignment(Pos.TOP_CENTER);
        leaderboardBox.setPrefHeight(200);
        leaderboardBox.setPrefWidth(100);

        ImageView label = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/LeaderboardLabel.png"))));
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

    public StackPane createSettingsContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(800, 800);

        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox settingsBox = new VBox();
        settingsBox.setAlignment(Pos.TOP_CENTER);
        settingsBox.setPrefHeight(200);
        settingsBox.setPrefWidth(100);

        ImageView label = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SettingsLabel.png"))));
        label.setPickOnBounds(true);
        label.setPreserveRatio(true);
        label.setFitWidth(550);
        label.setFitHeight(150);

        ImageView frame = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/SettingsFrame.png"))));
        frame.setFitWidth(700.0);
        frame.setFitHeight(400.0);

        ImageView back = getBackButton();

        settingsBox.getChildren().add(label);
        settingsBox.getChildren().add(frame);
        settingsBox.getChildren().add(back);

        stackPane.getChildren().add(settingsBox);

        return stackPane;
    }

    public StackPane createGameOverContent(){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(800, 800);
        ImageView background = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/GameOver.png"))));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        stackPane.getChildren().add(background);

        return stackPane;
    }

    public StackPane getMenuButton(ImageView label){
        StackPane button = new StackPane();

        ImageView frame = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ButtonFrame.png"))));
        frame.setFitWidth(400.0);
        frame.setFitHeight(150.0);
        frame.setPickOnBounds(true);
        frame.setPreserveRatio(true);
        frame.setId(label.getId());

        EventHandler<MouseEvent> eventHandler = getEventHandler();

        label.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        frame.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);

        button.getChildren().addAll(frame, label);

        return button;
    }

    public ImageView getBackButton(){

        ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ArrowsImg.png"))));
        back.setId("back");
        back.setFitWidth(150.0);
        back.setFitHeight(130.0);
        back.setPickOnBounds(true);
        back.setPreserveRatio(true);
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, getEventHandler());

        return back;
    }

    public ImageView getBackground(StackPane stackPane){
        ImageView background = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Background.png"))));
        background.fitWidthProperty().bind(stackPane.widthProperty());
        background.fitHeightProperty().bind(stackPane.heightProperty());
        return background;
    }

    public EventHandler<MouseEvent> getEventHandler(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getSource() instanceof ImageView) {
                    ImageView source = (ImageView) e.getSource();
                    if (source.getId().equals("start")) {
                        state = 1;
                    } else if (source.getId().equals("settings")) {
                        state = 2;
                    } else if (source.getId().equals("leaderboard")) {
                        state = 3;
                    } else if (source.getId().equals("exit")) {
                        state = 4;
                    } else if (source.getId().equals("back")) {
                        state = 5;
                    }
                }
            }
        };
        return eventHandler;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
    }
}
