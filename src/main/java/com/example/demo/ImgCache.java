package com.example.demo;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class ImgCache {
    private ArrayList<MenuImage> imageInfo;
    private ArrayList<Image> images;

    public ImgCache() {
        this.images = new ArrayList<>();
        this.imageInfo = new ArrayList<>();
        initializeImages();
    }

    private void initializeImages() {
        for (MenuImage image : MenuImage.values()) {
            images.add(image.getImage());
            imageInfo.add(image);
        }
    }

    public Image getImage(String name) {
        for (int i = 0; i < imageInfo.size(); i++) {
            if (imageInfo.get(i).getName().equals(name)) {
                return images.get(i);
            }
        }
        return null;
    }
}
