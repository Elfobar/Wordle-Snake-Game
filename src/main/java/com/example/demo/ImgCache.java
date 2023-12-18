package com.example.demo;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class ImgCache {
    private ArrayList<MenuImage> menuImageInfo;
    private ArrayList<MiniGameImage> miniGameImageInfo;
    private ArrayList<Image> images;
    private String type;

    public ImgCache(String type) {
        this.images = new ArrayList<>();
        this.menuImageInfo = new ArrayList<>();
        this.miniGameImageInfo = new ArrayList<>();
        this.type = type;
        if(type.equals("Menu")) {
            initializeMenuImages();
        } else if(type.equals("MiniGame")) {
            initializeMiniGameImages();
        }
    }

    private void initializeMenuImages() {
        for (MenuImage image : MenuImage.values()) {
            images.add(image.getImage());
            menuImageInfo.add(image);
        }
    }

    private void initializeMiniGameImages() {
        for (MiniGameImage image : MiniGameImage.values()) {
            images.add(image.getImage());
            miniGameImageInfo.add(image);
        }
    }

    public Image getImage(String name) {
        if(type.equals("Menu")) {
            for (int i = 0; i < menuImageInfo.size(); i++) {
                if (menuImageInfo.get(i).getName().equals(name)) {
                    return images.get(i);
                }
            }
        } else if(type.equals("MiniGame")) {
            for (int i = 0; i < miniGameImageInfo.size(); i++) {
                if (miniGameImageInfo.get(i).getName().equals(name)) {
                    return images.get(i);
                }
            }
        }
        return null;
    }
}