package com.example.demo.UI.Menu;

import com.example.demo.GameCore.GameConfig;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//EscMenu represents the game menu that appears when the is paused
public class EscMenu extends AbstractMenu {

    private static final String CONTINUE_LABEL_IMAGE = "ContinueLabel";
    private static final double CONTINUE_LABEL_WIDTH = 280;
    private static final double CONTINUE_LABEL_HEIGHT = 70;
    private static final String EXIT_LABEL_IMAGE = "ExitLabel";
    private static final double EXIT_LABEL_WIDTH = 130;
    private static final double EXIT_LABEL_HEIGHT = 65;
    private static final String BUTTON_FRAME_IMAGE = "ButtonFrame";
    private static final double BUTTON_FRAME_WIDTH = 400;
    private static final double BUTTON_FRAME_HEIGHT = 150;
    public EscMenu(){
        super();
    }

    //Method that creates the whole EscMenu that consists of background(StackPane) and buttons(Vbox).
    public StackPane createContent(){
        StackPane stackPane = createStackPane();
        VBox menuBox = createMenuBox();
        stackPane.getChildren().add(menuBox);

        return stackPane;
    }

    //Loads the background which is adjusted by specific Width and Height
    private StackPane createStackPane() {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(GameConfig.WIDTH, GameConfig.HEIGHT);
        ImageView background = getBackground(stackPane);
        stackPane.getChildren().add(background);
        return stackPane;
    }

    //Creates 2 buttons that consist of two pictures one on top of the other: label picture and button frame; assembles
    // the Vbox layout that will store them
    private VBox createMenuBox() {
        VBox menuBox = new VBox();
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setSpacing(30.0);

        ImageView continueLabel = createLabel(CONTINUE_LABEL_IMAGE, CONTINUE_LABEL_WIDTH, CONTINUE_LABEL_HEIGHT, "continue");
        StackPane continueBtn = getMenuButton(continueLabel);

        ImageView exitLabel = createLabel(EXIT_LABEL_IMAGE, EXIT_LABEL_WIDTH, EXIT_LABEL_HEIGHT, "back");
        StackPane exitBtn = getMenuButton(exitLabel);

        menuBox.getChildren().addAll(continueBtn, exitBtn);
        return menuBox;
    }

    //Sets the picture of the name of the button, assigns its unique ID which then can be accessed to add behaviour by clicking on it
    private ImageView createLabel(String imageName, double width, double height, String id) {
        ImageView label = new ImageView(cache.getImage(imageName));
        label.setFitWidth(width);
        label.setFitHeight(height);
        label.setId(id);
        return label;
    }


    //Sets the background of the button, in other words its frame.
    public StackPane getMenuButton(ImageView label){
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
