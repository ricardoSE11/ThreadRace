package sample;

import Domain.Vehicle;
import Domain.MovingVehicle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class Controller {

    ArrayList<Vehicle> vehicles;
    //Vars
    private boolean areBarriersOn;

    //UI
    private Stage stage;
    private GraphicsContext g;
    private Image bgImage;

    @FXML private TextField txfPrueba;
    @FXML private Canvas canvasDibujo;


    public Controller()
    {
        System.out.println("Instantiating a new (RSE)Controller");
    }


    public void init(Stage stage)
    {
        try
        {
            vehicles = new ArrayList<>();
            this.stage = stage;

            areBarriersOn = false;

            MovingVehicle jc2 = new MovingVehicle(555, 45, 1 , true , true );
            //jc2.start();
            MovingVehicle jc = new MovingVehicle(420, 370, 0 , true , true);
            //jc.start();

            vehicles.add(jc2);
            vehicles.add(jc);
        }

        catch (FileNotFoundException e)
        {
        e.printStackTrace();
        }


    }

    public void prueba()
    {
        if (txfPrueba.getText().toString().equals(""))
            System.out.println("ME DOY CUENTA DE QUE ESTOY VACÍO YEIBOR");
        System.out.println("Txf input: " + txfPrueba.getText());
        setUpSimulationGraphics();

        vehicles.get(1).setMoving(true);
    }

    public void setUpSimulationGraphics()
    {
        try
        {
            bgImage = new Image(new FileInputStream("src/Assets/background_icon_0.png"));

            g = this.canvasDibujo.getGraphicsContext2D();
            canvasDibujo.setWidth(1750);
            canvasDibujo.setHeight(1050);

            g.drawImage(bgImage , 0 , 0);

        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


    }

    public void initVehicles()
    {
        for (Vehicle currentVehicle : this.vehicles)
        {
            currentVehicle.start();
        }
    }

    public void turnOnBarrier() throws InterruptedException {
        String laneNumbersString = txfPrueba.getText().toString();
        /*if(!areBarriersOn)
        {
            System.out.println("Attempting to pause Vehicle Threads");
            if (laneNumbersString.equals(""))
            {
                //TODO: Pause all Threads.
                areBarriersOn = true;

                for (Vehicle currentVehicle : this.vehicles)
                {
                    while(areBarriersOn)
                    {
                        currentVehicle.wait();
                    }
                }
            }

            else{
                //divide string and pause given lanes.
            }
        }

        else{
            System.out.println("LOG: Attempting to resume Vehicle Threads");
            for (Vehicle currentVehicle : this.vehicles)
            {
                currentVehicle.wait();
            }
        }*/
        vehicles.get(1).setMoving(false);


    }

    public void reDraw(Image backgroundImage)
    {
        canvasDibujo.setWidth(1750);
        canvasDibujo.setHeight(1050);
        g = this.canvasDibujo.getGraphicsContext2D();
        g.clearRect(0, 0, 1750, 1050);
        g.drawImage(backgroundImage , 0 , 0);

        for (Vehicle currentVehicle : this.vehicles)
        {
            g.drawImage(currentVehicle.getImage() , currentVehicle.getX() , currentVehicle.getY());
        }
        /*g.drawImage(this.image, 0, 0);
        g.drawImage(this.sc.getImage(), this.sc.getX(), this.sc.getY());*/
    }


}
