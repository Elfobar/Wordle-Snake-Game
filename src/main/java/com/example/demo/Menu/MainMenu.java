package com.example.demo.Menu;

import com.example.demo.GameConfig;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenu extends AbstractMenu {
    private static final String LOGO_IMAGE = "NameImg";
    private static final double LOGO_WIDTH = 800;
    private static final double LOGO_HEIGHT = 180;
    private static final String START_LABEL_IMAGE = "StartLabel";
    private static final double START_LABEL_WIDTH = 180;
    private static final double LABEL_HEIGHT = 150;
    private static final String SETTINGS_LABEL_IMAGE = "SettingsLabel";
    private static final double SETTINGS_LABEL_WIDTH = 230;
    private static final String LEADERBOARD_LABEL_IMAGE = "LeaderboardLabel";
    private static final double LEADERBOARD_LABEL_WIDTH = 330;
    private static final String EXIT_LABEL_IMAGE = "ExitLabel";
    private static final double EXIT_LABEL_WIDTH = 150;
    private static final double EXIT_LABEL_HEIGHT = 130;
    private static final String BUTTON_FRAME_IMAGE = "ButtonFrame";
    private static final double BUTTON_FRAME_WIDTH = 400;
    private static final double BUTTON_FRAME_HEIGHT = 150;

    public MainMenu() {
        super();
    }

    @Override
    public StackPane createContent() {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT);
        stackPane.setAlignment(Pos.TOP_CENTER);

        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);

        VBox menuBox = createMenuBox();

        stackPane.getChildren().add(menuBox);

        return stackPane;
    }

    private VBox createMenuBox() {
        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setSpacing(15.0);
        menuBox.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT);

        ImageView logo = new ImageView(cache.getImage(LOGO_IMAGE));
        logo.setFitWidth(LOGO_WIDTH);
        logo.setFitHeight(LOGO_HEIGHT);
        logo.setPickOnBounds(true);
        logo.setPreserveRatio(true);

        ImageView startLabel = createLabel(START_LABEL_IMAGE, "start", START_LABEL_WIDTH, LABEL_HEIGHT);
        ImageView settingsLabel = createLabel(SETTINGS_LABEL_IMAGE, "settings", SETTINGS_LABEL_WIDTH, LABEL_HEIGHT);
        ImageView leaderboardLabel = createLabel(LEADERBOARD_LABEL_IMAGE, "leaderboard", LEADERBOARD_LABEL_WIDTH, LABEL_HEIGHT);
        ImageView exitLabel = createLabel(EXIT_LABEL_IMAGE, "exit", EXIT_LABEL_WIDTH, EXIT_LABEL_HEIGHT);

        StackPane firstBtnPane = createButton(startLabel);
        StackPane secondBtnPane = createButton(settingsLabel);
        StackPane thirdBtnPane = createButton(leaderboardLabel);
        StackPane fourthBtnPane = createButton(exitLabel);

        menuBox.getChildren().add(logo);
        menuBox.getChildren().addAll(firstBtnPane, secondBtnPane, thirdBtnPane, fourthBtnPane);

        return menuBox;
    }

    private ImageView createLabel(String imageName, String id, double width, double height) {
        ImageView label = new ImageView(cache.getImage(imageName));
        label.setId(id);
        label.setFitWidth(width);
        label.setFitHeight(height);
        label.setPickOnBounds(true);
        label.setPreserveRatio(true);
        return label;
    }

    private StackPane createButton(ImageView label) {
        StackPane button = new StackPane();
        button.setAlignment(Pos.CENTER);

        ImageView frame = new ImageView(cache.getImage(BUTTON_FRAME_IMAGE));
        frame.setFitWidth(BUTTON_FRAME_WIDTH);
        frame.setFitHeight(BUTTON_FRAME_HEIGHT);
        frame.setPickOnBounds(true);
        frame.setPreserveRatio(true);
        frame.setId(label.getId());

        button.getChildren().addAll(frame, label);

        return button;
    }
}