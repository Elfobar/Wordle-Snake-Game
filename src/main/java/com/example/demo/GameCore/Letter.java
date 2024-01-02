package com.example.demo.GameCore;
//try converting then to a record
public class Letter {

    private final char value;
    private final Coordinate position;
    private int letterLeftRightIndex;

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


