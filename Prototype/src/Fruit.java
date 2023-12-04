package org.apache.maven.SnakeGame;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.util.HashMap;

public class Fruit {
    private String[] letter =  {"A","B","C","D","O","G"};
    private Circle circle;
    private int x;
    private int y;
    private HashMap<String, Image> letterImages = new HashMap<>();
    private String userData;
    


    public Fruit(GridPane grid, int x, int y, String word, String currentWord){
        this.circle = new Circle();
        this.x = x;
        this.y = y;
        initializeHashmap();
        String let = chooseFirstLetter(currentWord, word);
        Image letImage = getImageToLetter(let);
        circle.setUserData(let);
        userData = let;
        circle.setRadius(20);
        circle.setFill(new ImagePattern(letImage));
        grid.add(circle, x, y);
    }

    public Fruit(GridPane grid, int x, int y){
        this.circle = new Circle();
        this.x = x;
        this.y = y;
        initializeHashmap();
        String let = chooseRandomLetter();
        Image letImage = getImageToLetter(let);
        circle.setUserData(let);
        userData = let;
        circle.setRadius(20);
        circle.setFill(new ImagePattern(letImage));
        grid.add(circle, x, y);
    }

    public void initializeHashmap(){
        letterImages.put("A", new Image("file:C:/Users/Maks/Desktop/pics/letterA.png"));
        letterImages.put("B", new Image("file:C:/Users/Maks/Desktop/pics/letterB.png"));
        letterImages.put("C", new Image("file:C:/Users/Maks/Desktop/pics/letterC.png"));
        letterImages.put("D", new Image("file:C:/Users/Maks/Desktop/pics/letterD.png"));
        letterImages.put("O", new Image("file:C:/Users/Maks/Desktop/pics/letterO.png"));
        letterImages.put("G", new Image("file:C:/Users/Maks/Desktop/pics/letterG.png"));
    }

    public int getFruitX(){
        return this.x;
    }

    public int getFruitY(){
        return this.y;
    }
    
    public Fruit getFruit(){
        return this;
    }

    public Circle getNode(){
        return this.circle;
    }

    public Image getImageToLetter(String letter){
        for(String s : letterImages.keySet()){
            if(s.equals(letter)){
                return letterImages.get(letter);
            }
        }
        return null;
    }

    public String chooseFirstLetter(String currentWord, String word) {
        String neededLetter = "";
        if (currentWord.length() == 0) {
            char neededChar = word.charAt(0);
            neededLetter = neededChar + "";
        } else {
            for (int i = 0; i < currentWord.length(); i++) {
                System.out.println(currentWord);
                if (currentWord.charAt(i) == word.charAt(i)) {
                    char neededChar = word.charAt(i + 1);
                    neededLetter = neededChar + "";
                } else{
                    System.out.println("Error");
                }
            }
        }
        return neededLetter;
    }
    // public Character chooseFirstLetter(String currentWord, String word){
    //     char letter;
    //     for(int i = 0; i < letter.length; i++){
    //         if(currentWord.contains(String.valueOf(letter[i]))){
    //             letter = letter[i];
    //         }
    //     }
    //     return null;
    // }

    public String chooseRandomLetter(){
        int randIndex = (int) (Math.random() * letter.length);
        return letter[randIndex];
    }

    public String getUserData(){
        return this.userData;
    }
    
}
