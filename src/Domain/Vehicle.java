package Domain;

import java.util.ArrayList;

import Utility.Speeds;
import javafx.scene.image.Image;

public class Vehicle extends Thread{

    private int x;
    private int y;
    private Speeds speed;
    private int imgNum;
    private Image image;
    private ArrayList<Image> sprite;

    private boolean isMoving;
    private boolean isGoingDown;
    

    public Vehicle(int x, int y, int imgNum , boolean isMoving , boolean isGoingDown , Speeds speed) {
        this.x = x;
        this.y = y;
        this.imgNum = imgNum;
        this.sprite = new ArrayList<Image>();

        this.speed = speed;

        this.isMoving = isMoving;
        this.isGoingDown = isGoingDown;

    }

    public Speeds getSpeed() {
        return speed;
    }

    public void setSpeed(Speeds speed) {
        this.speed = speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isGoingDown() {
        return isGoingDown;
    }

    public void setGoingDown(boolean goingDown) {
        isGoingDown = goingDown;
    }

    public Vehicle() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImgNum() {
        return imgNum;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public ArrayList<Image> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<Image> sprite) {
        this.sprite = sprite;
    }
}
