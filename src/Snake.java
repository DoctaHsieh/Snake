import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Snake {

    List<Point> snakePoints;
    int xDir, yDir;
    boolean isMoving, elongateEasy, elongateNormal, elongateHard;
    final int STARTSIZE = 20, STARTX = 150, STARTY = 150;

    public Snake() {
        snakePoints = new ArrayList<Point>();
        xDir = 0;
        yDir = 0;
        isMoving = false;
        elongateEasy = false;
        snakePoints.add(new Point(STARTX, STARTY));
        for (int i = 1; i < STARTSIZE; i++) {
            snakePoints.add(new Point(STARTX - i * 4, STARTY));
        }

    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        for (Point p : snakePoints) {
            g.fillRect(p.getX(), p.getY(), 4, 4);
            //System.out.println("Snake.draw X Y = " + p.x + " " + p.y);
        }
    }


    public int getXDir() {
        return xDir;

    }

    public int getYDir() {
        return yDir;

    }

    public void setXDir(int x) {
        xDir = x;
    }

    public void setYDir(int y) {
        yDir = y;
    }

    public int getX() {
        return snakePoints.get(0).getX();
    }

    public int getY() {
        return snakePoints.get(0).getY();

    }

    public boolean isMoving(){
        return isMoving;
    }

    public void setIsMoving(boolean b){
        isMoving = b;
    }
    public void setElongateEasy(boolean b) { elongateEasy = b; }
    public void setElongateNormal(boolean b) { elongateNormal = b;}
    public void setElongateHard(boolean b){elongateHard = b;}


    public void move() {
        if (isMoving) {
            Point temp = snakePoints.get(0);
            Point last = snakePoints.get(snakePoints.size() - 1);


            Point newStart = new Point(temp.getX() + xDir * 4, temp.getY() + yDir * 4);

            for (int i = snakePoints.size() - 1; i >= 1; i--) {
                snakePoints.set(i, snakePoints.get(i - 1));
            }
            snakePoints.set(0, newStart);
            if(elongateEasy){
                snakePoints.add(last);
                elongateEasy = false;
            }
            if(elongateNormal){
                snakePoints.add(last);
                snakePoints.add(last);
                snakePoints.add(last);
                elongateNormal = false;
            }
            if(elongateHard){
                snakePoints.add(last);
                snakePoints.add(last);
                snakePoints.add(last);
                snakePoints.add(last);
                snakePoints.add(last);

                elongateHard = false;
            }
        }
    }



    public boolean snakeCollision() {
        int x = this.getX();
        int y = this.getY();
        for (int i = 1; i < snakePoints.size(); i++) {
            if (snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y) {

                return true;
            }

        }
        return false;
    }


}

