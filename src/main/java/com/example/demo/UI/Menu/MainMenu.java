package com.example.demo.UI.Menu;

import com.example.demo.GameCore.GameConfig;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenu extends AbstractMenu {
    private static final String LOGO_IMAGE = "NameImg"; //Image id (name), used by ImgCache
    private static final double LOGO_WIDTH = 800;  //Image width, manually calculated
    private static final double LOGO_HEIGHT = 180; //Image height, manually calculated
    private static final String START_LABEL_IMAGE = "StartLabel";
    private static final double START_LABEL_WIDTH = 180;
    private static final double LABEL_HEIGHT = 130;  //Height of all  labels is the same
    private static final String MINIGAME_LABEL_IMAGE = "MiniGameLabel";
    private static final double MINIGAME_LABEL_WIDTH = 230;
    private static final String SETTINGS_LABEL_IMAGE = "SettingsLabel";
    private static final double SETTINGS_LABEL_WIDTH = 230;
    private static final String SCOREBOARD_LABEL_IMAGE = "ScoreboardLabel";
    private static final double SCOREBOARD_LABEL_WIDTH = 330;
    private static final String EXIT_LABEL_IMAGE = "ExitLabel";
    private static final double EXIT_LABEL_WIDTH = 150;
    private static final String BUTTON_FRAME_IMAGE = "ButtonFrame";
    private static final double BUTTON_FRAME_WIDTH = 400;
    private static final double BUTTON_FRAME_HEIGHT = 130; //Button frame height is the same as label height

    //Constructor of the AbstractMenu is used, ImageCache is inherited
    public MainMenu() {
        super();
    }

    @Override
    public StackPane createContent() {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT); //StackPane is created with the same width and height as window size
        stackPane.setAlignment(Pos.TOP_CENTER);

        ImageView background = getBackground(stackPane); //Background image is applied
        stackPane.getChildren().add(background);

        VBox menuBox = createMenuBox(); //Vbox with menu content is created and later added on top of background

        stackPane.getChildren().add(menuBox);

        return stackPane; //StackPane with background and menu items is returned
    }

    private VBox createMenuBox() {
        VBox menuBox = new VBox(); //Vbox is used to display items vertically
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setSpacing(5.0);
        menuBox.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT); //VBox size is adjusted to window size

        ImageView logo = new ImageView(cache.getImage(LOGO_IMAGE)); //Image is found through image cache (preloaded by ImgCache)
        logo.setFitWidth(LOGO_WIDTH);
        logo.setFitHeight(LOGO_HEIGHT);
        logo.setMouseTransparent(true); //Behavior: image is not clickable (transparent to mouse)
        logo.setPreserveRatio(true); //Behavior: ratio height to width of the image is preserved/not adjusted to the VBox size

        ImageView startLabel = createLabel(START_LABEL_IMAGE, "start", START_LABEL_WIDTH, LABEL_HEIGHT); //Labels for menu items are created
        ImageView miniGameLabel = createLabel(MINIGAME_LABEL_IMAGE, "minigame", MINIGAME_LABEL_WIDTH, LABEL_HEIGHT);
        ImageView settingsLabel = createLabel(SETTINGS_LABEL_IMAGE, "settings", SETTINGS_LABEL_WIDTH, LABEL_HEIGHT);
        ImageView scoreboardLabel = createLabel(SCOREBOARD_LABEL_IMAGE, "scoreboard", SCOREBOARD_LABEL_WIDTH, LABEL_HEIGHT);
        ImageView exitLabel = createLabel(EXIT_LABEL_IMAGE, "exit", EXIT_LABEL_WIDTH, LABEL_HEIGHT);

        StackPane firstBtnPane = createButton(startLabel); //StackPanes used as buttons (labels plus frames) are created
        StackPane secondBtnPane = createButton(miniGameLabel);
        StackPane thirdBtnPane = createButton(settingsLabel);
        StackPane fourthBtnPane = createButton(scoreboardLabel);
        StackPane fifthBtnPane = createButton(exitLabel);

        menuBox.getChildren().add(logo); //Logo is added
        menuBox.getChildren().addAll(firstBtnPane, secondBtnPane, thirdBtnPane, fourthBtnPane, fifthBtnPane); //All buttons are added

        return menuBox; //Vbox is returned with all items
    }

    private ImageView createLabel(String imageName, String id, double width, double height) {
        ImageView label = new ImageView(cache.getImage(imageName)); //Image is found through image cache (preloaded by ImgCache)
        label.setId(id); //ID is assigned to each label for mouse event recognition
        label.setFitWidth(width); //Appropriate width and height are assigned, important as images are different in size
        label.setFitHeight(height);
        label.setPreserveRatio(true); //Behavior: ratio height to width of the image is preserved
        return label;
    }

    private StackPane createButton(ImageView label) {
        StackPane button = new StackPane();
        button.setAlignment(Pos.CENTER); //Alignment is set to display label in the center

        ImageView frame = new ImageView(cache.getImage(BUTTON_FRAME_IMAGE)); //Frames are created, size adjusted
        frame.setFitWidth(BUTTON_FRAME_WIDTH);
        frame.setFitHeight(BUTTON_FRAME_HEIGHT);
        frame.setPickOnBounds(true); //Behavior: transparent areas are clickable (frames are of irregular shape, so more user-friendly)
        frame.setPreserveRatio(true); //Behavior: ratio height to width of the image is preserved
        frame.setId(label.getId()); //Frames are set the same ID as labels for event recognition

        button.getChildren().addAll(frame, label); //Labels are added on top of frames (according to StackPane property)

        return button; //StackPane that is used as button is returned
    }
}