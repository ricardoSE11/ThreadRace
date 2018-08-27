package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Utility.Speeds;
import javafx.scene.image.Image;

public class MovingVehicle extends Vehicle {



    public MovingVehicle(int x, int y, int imgNum , boolean isMoving , boolean isGoingDown, Speeds speed) throws FileNotFoundException {
        super(x, y, imgNum , isMoving , isGoingDown , speed);
        setSprite();

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

        while (true) {
            try {
                 {
                    if (!this.isMoving()) {

                    }else{
                        if(!this.isGoingDown()){
                            if (this.getY() < 630)
                            {
                                switch (this.getSpeed()){
                                    case SLOW:
                                        Thread.sleep(100);
                                        break;

                                    case MEDIUM:
                                        Thread.sleep(75);
                                        break;

                                    case FAST:
                                        Thread.sleep(50);
                                        break;
                                }
                                super.setImage(sprite.get(getImgNum()));
                                this.setY(getY() + 10);
                                //System.out.println(" -DOWN- ");
                            }

                            else {
                                this.setY(0);
                            }
                        }else{
                            if (this.getY() > 0)
                            {
                                switch (this.getSpeed()){
                                    case SLOW:
                                        Thread.sleep(100);
                                        break;

                                    case MEDIUM:
                                        Thread.sleep(75);
                                        break;

                                    case FAST:
                                        Thread.sleep(50);
                                        break;
                                }
                                super.setImage(sprite.get(getImgNum()+3));
                                this.setY(getY() - 10);
                                //System.out.println(" -DOWN- ");
                            }

                            else {
                                this.setY(630);
                            }
                        }
                    }
                }
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }
}
