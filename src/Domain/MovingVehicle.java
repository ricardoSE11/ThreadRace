package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Utility.Speeds;
import javafx.scene.image.Image;

public class MovingVehicle extends Vehicle {

    private int speedThread;
    private boolean running;
    private boolean figureImage;


    public MovingVehicle(int x, int y, int imgNum , boolean isMoving , boolean isGoingDown, Speeds speed , Boolean running) throws FileNotFoundException {
        super(x, y, imgNum , isMoving , isGoingDown , speed);
        System.out.println("Vehicle with: " + x + " , " + y);
        this.running = running;
        setSprite();
        this.figureImage = false;
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


    public boolean isFigureImage() {
        return figureImage;
    }

    public void setFigureImage(boolean figureImage) {
        this.figureImage = figureImage;
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
        sprite.add(new Image(new FileInputStream("src/Assets/matrix_icon_1.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/matrix_icon_2.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/matrix_icon_3.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/matrix_icon_4.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/bullbasaur_icon.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/charmander_icon.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/pikachu_icon.png")));

    }

    @Override
    public void run() {

        ArrayList<Image> sprite = super.getSprite();

        super.setImage(sprite.get(getImgNum()));

        while (running)
        {
            try {
                {
                    if(this.isMoving())
                    {
                        if(this.isGoingDown())
                        {
                            if ((this.getY()-80) < 630)
                            {
                                if (this.figureImage){
                                    super.setImage(sprite.get(getImgNum()+6));
                                }
                                else{
                                    super.setImage(sprite.get(getImgNum()));
                                }
                                this.setY(getY() + 10);
                                //System.out.println(" -DOWN- ");
                            }
                        }
                        else{
                            if (this.getY() > 0)
                            {
                                if (this.figureImage){
                                    super.setImage(sprite.get(getImgNum()+6));
                                }
                                else{
                                    super.setImage(sprite.get(getImgNum()+3));
                                }
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
