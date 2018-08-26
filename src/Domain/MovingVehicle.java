package Domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class MovingVehicle extends Vehicle {


    public MovingVehicle(int x, int y, int imgNum , boolean isMoving , boolean isGoingDown) throws FileNotFoundException {
        super(x, y, imgNum , isMoving , isGoingDown);
        setSprite();

    }
    
    public void setSprite() throws FileNotFoundException{
        ArrayList<Image> sprite = super.getSprite();
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_1.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_3.png")));
        sprite.add(new Image(new FileInputStream("src/Assets/car_icon_5.png")));
    }


    @Override
    public void run()
    {
        ArrayList<Image> sprite = super.getSprite();
        super.setImage(sprite.get(getImgNum()));
        while (true) {
            try
            {
                while(this.isMoving())
                {
                    if (this.getY() < 600)
                    {
                        Thread.sleep(100);
                        super.setImage(sprite.get(getImgNum()));
                        this.setY(getY() + 10);
                        //System.out.println(" -DOWN- ");
                    }

                    else {
                        this.setY(100);
                    }
                }

            }

            catch (InterruptedException ex) {} 
        }
    }

}
