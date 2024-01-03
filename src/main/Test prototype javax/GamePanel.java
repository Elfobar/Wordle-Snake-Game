import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    static int DELAY = 100;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    int amountOfWalls = 12;
    int wallsX[] = new int[GAME_UNITS];
    int wallsY[] = new int[GAME_UNITS];
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    final int wallx[] = new int[GAME_UNITS];
    final int wally[] = new int[GAME_UNITS];


    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        newApple();
        walls();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        if(running){
            //Grid
            for(int i = 0; i<SCREEN_HEIGHT/UNIT_SIZE; i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }

            //Fruit
            g.setColor(Color.green);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //Snake
            for(int i = 0; i < bodyParts; i++){
                if(i == 0){
                    g.setColor(Color.green);
                }
                else{
                    g.setColor(new Color(45, 180, 0));
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            //Wall
            g.setColor(Color.red);
            for(int i = 0; i < amountOfWalls; i++){
                g.fillRect(wallsX[i], wallsY[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        }
        else{
            gameOver(g);
        }
    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        for (int i = 0; i < UNIT_SIZE; i++){
                if (appleX == x[i] && appleY == y[i]){
                newApple();
                break;
            }
        }
    }

    public void walls(){
        int box;
        int boy;
        for (int i = 0; i < amountOfWalls; i++){
            box = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            boy = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
            wallsX[i] = box;
            wallsY[i] = boy;
        }
    }

    public void move() {
        for(int i = bodyParts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;

            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
                
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
                
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
            DELAY = DELAY - 10;
            timer.setDelay(DELAY);
        }
    }

    public void checkCollisions(){
        //Check if head collides with body
        for (int i = bodyParts; i > 0; i--){
            if((x[0] == x[i])&&(y[0] == y[i])){
                running = false;
            }
        }
        for (int i = amountOfWalls; i > 0; i--){
            if ((x[0] == wallsX[i])&&(y[0] == wallsY[i])){
                running = false;
            }
        }

        //Check if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        //Check if head touches right border
        if (x[0] > SCREEN_WIDTH-1) {
            running = false;
        }  
        
        //Check if head touches top border
        if (y[0] < 0) {
            running = false;
        }
        //Check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT-1) {
            running = false;
        }

        if(!running){
            timer.stop();
        }
        
    }

    public void gameOver(Graphics g){
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
