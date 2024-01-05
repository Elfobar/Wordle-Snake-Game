package com.example.demo.GameCore;
public class Letter {

    private final char value;
    private final Coordinate position;
    private int letterLeftRightIndex;

    //Two constructors exist because we have the main game and the mini game, which both use this class
    //In main game the information that letters needs to store is coordinate and value as a char
    //Mini game need to know whether the letter should come from the left or the right side
    public Letter(char value, Coordinate position){
        this.value = value;
        this.position = position;
        this.letterLeftRightIndex = -1;
    }

    public Letter(char value){
        this.value = value;
        this.position = new Coordinate(0,0);
        this.letterLeftRightIndex = -1;
    }

    public void setLetterLeftRightIndex(int index){
        this.letterLeftRightIndex = index;
    }

    public int getLetterLeftRightIndex(){
        return this.letterLeftRightIndex;
    }

    public char getValue(){
        return this.value;
    }

    public Coordinate getPosition(){
        return this.position;
    }
}


