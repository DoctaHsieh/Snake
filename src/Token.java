import java.awt.Color;
import java.awt.Graphics;

public class Token {
    SnakeGame SG;
    private int x, y;
    private Snake snake;

    public Token(Snake s){
        x = (int)(Math.random() * 395);
        y = (int)(Math.random() * 395);
        snake = s;
    }
    public void changePosition(){
        x = (int)(Math.random() * 395);
        y = (int)(Math.random() * 395);

    }



    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(x,y, 6 , 6);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void EasySnakeCollision(){
        snake.setElongateEasy(true);
        snake.setElongateNormal(false);
        snake.setElongateHard(false);
    }
    public void NormalSnakeCollision(){
        snake.setElongateNormal(true);
        snake.setElongateHard(false);
        snake.setElongateEasy(false);
    }
    public void HardSnakeCollision(){
        snake.setElongateHard(true);
        snake.setElongateEasy(false);
        snake.setElongateNormal(false);
    }

}