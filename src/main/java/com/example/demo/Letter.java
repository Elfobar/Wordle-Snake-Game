package com.example.demo;
//try converting then to a record
public class Letter {

    private final char value;
    private final Coordinate position;

    public Letter(char value, Coordinate position){
        this.value = value;
        this.position = position;
    }

    public char getValue(){
        return this.value;
    }

    public Coordinate getPosition(){
        return this.position;
    }
}


