package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Utility.Speeds;
import javafx.scene.image.Image;

public class MovingVehicle extends Vehicle {

    private int speedThread;
    private boolean running;


    public MovingVehicle(int x, int y, int imgNum , boolean isMoving , boolean isGoingDown, Speeds speed , Boolean running) throws FileNotFoundException {
        super(x, y, imgNum , isMoving , isGoingDown , speed);
        this.running = running;
        setSprite();
        switch (this.getSpeed()){
            case SLOW:
                speedThread=100;
                break;

            case MEDIUM:
                speedThread=75;
                break;

            case FAST:
                speedThread=50;
                break;
        }

    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setSprite() throws FileNotFoundException{
        ArrayList<Image> sprite = super.getSprite();
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_0.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_1.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_2.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_6.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_7.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_8.png")));
    }

    @Override
    public void run() {

        ArrayList<Image> sprite = super.getSprite();
        super.setImage(sprite.get(getImgNum()));

        /*while (this.running && this.isMoving())
        {
            try {
                 {

                    if(!this.isGoingDown()){
                        if (this.getY() < 630)
                        {
                            super.setImage(sprite.get(getImgNum()));
                            this.setY(getY() + 10);
                            //System.out.println(" -DOWN- ");
                        }
                    }
                    else{
                        if (this.getY() > 0)
                        {
                            super.setImage(sprite.get(getImgNum()+3));
                            this.setY(getY() - 10);
                            //System.out.println(" -DOWN- ");
                        }
                    }
                    Thread.sleep(speedThread);
                }
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }*/

        while (running)
        {
            try {
                {
                    if(this.isMoving()){
                        if(this.isGoingDown()){
                            if ((this.getY()-80) < 630)
                            {
                                super.setImage(sprite.get(getImgNum()));
                                this.setY(getY() + 10);
                                //System.out.println(" -DOWN- ");
                            }
                        }
                        else{
                            if (this.getY() > 0)
                            {
                                super.setImage(sprite.get(getImgNum()+3));
                                this.setY(getY() - 10);
                                //System.out.println(" -DOWN- ");
                            }
                        }
                    }
                    Thread.sleep(speedThread);
                }
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }
}
