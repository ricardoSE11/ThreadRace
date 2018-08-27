package sample;

import Domain.Vehicle;
import Domain.MovingVehicle;
import Utility.Speeds;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class Controller {

    public ArrayList<MovingVehicle> vehicles;

    //Vars
    private boolean areBarriersOn;
    private boolean trafficLigth;

    //Vars GUI
    private int speed=0;

    //UI
    private Stage stage;
    private GraphicsContext g;
    private Image bgImage;
    private Boolean goingDown;

    @FXML private TextField txfNumberOfCars;
    @FXML private Canvas canvasDibujo;
    @FXML private ImageView imageCar;
    @FXML private ImageView imageSpeedometer;
    @FXML private ImageView imageTrafficLigth;


    public Controller()
    {
        System.out.println("Instantiating a new (RSE)Controller");
    }


    public void init(Stage stage)
    {
        vehicles = new ArrayList<>();
        this.stage = stage;

        areBarriersOn = false;
        goingDown = false;
        trafficLigth= true;
            /*for(int i = 0 ; i<11 ; i++){
                MovingVehicle jc2 = new MovingVehicle(x, y, 1 , true , true );
                vehicles.add(jc2);
                x+=140;
            }*/


    }

    public void prueba()
    {
        if (txfNumberOfCars.getText().toString().equals(""))
            System.out.println("ME DOY CUENTA DE QUE ESTOY VACÍO YEIBOR");
        System.out.println("Txf input: " + txfNumberOfCars.getText());
        setUpSimulationGraphics();
        vehicles.get(1).setMoving(false);
        vehicles.get(0).setMoving(false);
        vehicles.get(3).setMoving(false);
        vehicles.get(7).setMoving(false);
        vehicles.get(8).setMoving(false);
        vehicles.get(5).setMoving(false);


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

    public void initVehicles() throws FileNotFoundException {
        int number = Integer.parseInt(txfNumberOfCars.getText());
        int x =145;
        int y = 45;
        Speeds vel;
        for(int i = 0 ; i<number ; i++){
            switch (speed){
                case 0 :
                    vel=Speeds.SLOW;
                    MovingVehicle jc0 = new MovingVehicle(x, y, speed , true , false , vel);
                    vehicles.add(jc0);
                    jc0.start();
                    break;
                case 1 :
                    vel=Speeds.MEDIUM;
                    MovingVehicle jc1 = new MovingVehicle(x, y, speed , true , false , vel);
                    vehicles.add(jc1);
                    jc1.start();
                    break;
                case 2 :
                    vel=Speeds.FAST;
                    MovingVehicle jc2 = new MovingVehicle(x, y, speed , true , false , vel);
                    vehicles.add(jc2);
                    jc2.start();
                    break;
            }
            x+=140;
        }
    }

    public void turnOnBarrier() throws InterruptedException {
        String laneNumbersString = txfNumberOfCars.getText().toString();
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
        vehicles.get(1).setMoving(true);
        vehicles.get(0).setMoving(true);
        vehicles.get(3).setMoving(true);
        vehicles.get(7).setMoving(true);
        vehicles.get(8).setMoving(true);
        vehicles.get(5).setMoving(true);
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



    //Voids GUI

    public void changeDirection(){

        if(goingDown==false){
            vehicles.get(0).setGoingDown(true);
            goingDown=true;
        }else{
            vehicles.get(0).setGoingDown(false);
            goingDown=false;
        }


    }
    public void increaseSpeed(){

        int new_position_tachomer = speed+1;
        if(new_position_tachomer<3){
            speed=speed+1;
            Image imageSpeed = new Image("Assets/speedometer_icon_"+speed+".png");
            imageSpeedometer.setImage(imageSpeed);
            Image imageC = new Image("Assets/car_icon_"+speed+".png");
            imageCar.setImage(imageC);
        }
        else {
            System.out.println("Alcanzo la velocidad maxima");
        }
    }

    public void reduceSpeed(){

        int new_position_tachomer = speed;
        if(new_position_tachomer>0){
            speed=speed-1;
            Image imageSpeed = new Image("Assets/speedometer_icon_"+speed+".png");
            imageSpeedometer.setImage(imageSpeed);
            Image imageC = new Image("Assets/car_icon_"+speed+".png");
            imageCar.setImage(imageC);
        }
        else {
            System.out.println("Alcanzo la velocidad minima");
        }
    }

    public void interruptVehicleMovement(){

        if(trafficLigth){

            Image imageTrafficLigthRed = new Image("Assets/traffic_light_red_icon.png");
            imageTrafficLigth.setImage(imageTrafficLigthRed);
            for (MovingVehicle currentVehicle:vehicles) {
                currentVehicle.setMoving(false);
                //System.out.println("set Moving false");
            }
            trafficLigth=false;
            //System.out.println("Is Moving false");

        }else{

            Image imageTrafficLigthGreen = new Image("Assets/traffic_light_green_icon.png");
            imageTrafficLigth.setImage(imageTrafficLigthGreen);
            for (MovingVehicle currentVehicle:vehicles) {
                currentVehicle.setMoving(true);
                //System.out.println("set Moving true");
            }
            trafficLigth=true;
            //System.out.println("Is Moving true");
        }
    }
}
