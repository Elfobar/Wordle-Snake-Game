package org.apache.maven.SnakeGame;

public enum Letter {
    A("file:C:/Users/Maks/Desktop/pics/letterA.png"),
    B("file:C:/Users/Maks/Desktop/pics/letterB.png"),
    C("file:C:/Users/Maks/Desktop/pics/letterC.png"),
    D("file:C:/Users/Maks/Desktop/pics/letterD.png"),
    O("file:C:/Users/Maks/Desktop/pics/letterO.png"),
    G("file:C:/Users/Maks/Desktop/pics/letterG.png");
    public String url;

    Letter(String str){
        url = str;
    }

    public String getUrl(){
        return this.url;
    }
}
