import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeGame extends Applet implements Runnable, KeyListener {

    Graphics gfx;
    Image img;
    Thread thread;
    Snake snake;
    boolean gameOver;
    Token token;
    boolean easy, normal, hard;
    private int score;

    public void init(){
        this.resize(400, 400);
        gameOver = false;
        easy = false;
        normal = false;
        hard = false;
        img = createImage(400, 400);
        gfx = img.getGraphics();
        this.addKeyListener(this);
        snake = new Snake();
        token = new Token(snake);
        thread = new Thread(this);
        thread.start();
    }
    public int getScore(){
        return score;
    }

    public void paint(Graphics g) {
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, 400, 400);
        if (!gameOver){
            snake.draw(gfx);
            token.draw(gfx);
        }
        else {
            gfx.setColor(Color.red);
            gfx.drawString("Game Over", 180, 150);
            gfx.drawString("Score: " +getScore(), 180, 170);

        }
            g.drawImage(img, 0, 0, null);


    }

    public void update(Graphics g){
        paint(g);
    }

    public void repaint(Graphics g){
        paint(g);
    }

    public void run(){
        for(;;){

            if(!gameOver) { 
                snake.move();
                this.checkGameOver();
                snakeCollision();

            }

            this.repaint();
            try{
                Thread.sleep(30);
            }catch(InterruptedException e){
                e.printStackTrace();

            }


        }


    }

    public void checkGameOver(){
        if(snake.getX() < 0 || snake.getX() > 396)
            gameOver = true;
        if(snake.getY() < 0 || snake.getY() > 396)
            gameOver = true;
        if(snake.snakeCollision()){
            gameOver = true;
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(!snake.isMoving()){


            }
            if (e.getKeyCode() == KeyEvent.VK_1) {
                easy = true;
                normal = false;
                hard = false;
                snake.setIsMoving(true);
                snake.setXDir(1);
                snake.setYDir(0);

            }
            if(e.getKeyCode() == KeyEvent.VK_2){
                normal = true;
                snake.setXDir(1);
                snake.setYDir(0);


                snake.setIsMoving(true);
            }
            if(e.getKeyCode() == KeyEvent.VK_3){
                hard =  true;

                snake.setIsMoving(true);
                snake.setXDir(1);
                snake.setYDir(0);
            }



        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (snake.getYDir() != 1) {
                snake.setYDir(-1);
                snake.setXDir(0);
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (snake.getYDir() != -1) {
                snake.setYDir(1);
                snake.setXDir(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (snake.getXDir() != 1) {
                snake.setXDir(-1);
                snake.setYDir(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (snake.getXDir() != -1) {
                snake.setXDir(1);
                snake.setYDir(0);
            }
        }
    }
        public void keyReleased(KeyEvent e) {

    }

    public boolean snakeCollision() {
        int snakeX = snake.getX() + 2;
        int snakeY = snake.getY() +2;
        if (snakeX >= (token.getX() - 1) && snakeX <= (token.getX()+7)) {


                if(easy == true){
                    if (snakeY >= (token.getY() - 1) && snakeY <= (token.getY() + 7)) {
                        token.changePosition();
                        score++;
                        token.EasySnakeCollision();

                    }
                }
                if(normal == true){
                    if (snakeY >= (token.getY() - 1) && snakeY <= (token.getY() + 7)) {

                        token.changePosition();
                        score++;
                        token.NormalSnakeCollision();

                    }
                }
                if(hard == true) {
                    if (snakeY >= (token.getY() - 1) && snakeY <= (token.getY() + 7)) {

                        token.changePosition();
                        score++;
                        token.HardSnakeCollision();

                    }
                }

                return true;
            }

        return false;
    }



}