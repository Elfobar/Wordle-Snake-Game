package com.example.demo.Game;

import com.example.demo.UI.ImageInfo;
import javafx.scene.image.Image;

import java.util.Objects;

public enum MiniGameImage implements ImageInfo {
    SNAKE_UPFRONT("SnakeUpfront", "/images/Front.png"),
    SNAKE_LEFT("SnakeLeft", "/images/Left.png"),
    SNAKE_RIGHT("SnakeRight", "/images/Right.png"),
    FRAME ("Frame", "/images/MiniGameFrameDefault.png"),
    FRAME_LEFT("FrameLeft", "/images/MiniGameFrameLeft.png"),
    FRAME_RIGHT("FrameRight", "/images/MiniGameFrameRight.png"),
    OBSTACLE_BOTTOM("ObstacleBottom", "/images/ObstacleBottom.png"),
    OBSTACLE_TOP("ObstacleTop", "/images/ObstacleTop.png"),
    BACKGROUND("Background", "/images/Background.png");

        private final String name;
        private final String path;

        MiniGameImage(String name, String path) {
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public Image getImage() {
            // Use the method from the supertype to retrieve the image
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            return image;
        }
}
