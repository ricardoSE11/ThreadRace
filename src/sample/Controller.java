package sample;

import Domain.Lane;
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
import java.util.Random;


public class Controller {

    //TODO: Change this to a ArrayList<Lane>
    private ArrayList<Lane> lanes;
    private ArrayList<MovingVehicle> vehicles;

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
        lanes = new ArrayList<>();
        areBarriersOn = false;
        goingDown = false;
        trafficLigth= true;

        this.stage = stage;

        initiLanes();
    }

    public void initiLanes()
    {
        System.out.println("Created Lanes ArrayList");
        int yTop = 0;
        int yBottom = 630;

        Lane laneOne = new Lane(145 , 235 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneTwo = new Lane(285 , 365 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneThree = new Lane(420 , 500 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneFour = new Lane(555 , 635 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneFive = new Lane(690 , 770 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneSix = new Lane(828 , 908 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneSeven = new Lane(962 , 1042 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneEight = new Lane(1100 , 1180 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneNine = new Lane(1235 , 1315 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneTen = new Lane(1370 , 1450 , yTop , yBottom ,310 , 315 , true , false );
        Lane laneEleven = new Lane(1506 , 1586 , yTop , yBottom ,310 , 315 , true , false );

        this.lanes.add(laneOne);
        this.lanes.add(laneTwo);
        this.lanes.add(laneThree);
        this.lanes.add(laneFour);
        this.lanes.add(laneFive);
        this.lanes.add(laneSix);
        this.lanes.add(laneSeven);
        this.lanes.add(laneEight);
        this.lanes.add(laneNine);
        this.lanes.add(laneTen);
        this.lanes.add(laneEleven);

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

    //TODO: Put this in Lane Class
    public void initVehicles() throws FileNotFoundException
    {
        int number = Integer.parseInt(txfNumberOfCars.getText());
        Speeds vel;
        int y = 45;
        for(int i = 0 ; i<number ; i++){
            Random aleatorio = new Random(System.currentTimeMillis());
            int intAletorio = aleatorio.nextInt(10);
            aleatorio.setSeed(System.currentTimeMillis());
            int x =lanes.get(intAletorio).getxLeft();
            switch (speed){
                case 0 :
                    vel=Speeds.SLOW;
                    MovingVehicle jc0 = new MovingVehicle(x, y, speed , true , false , vel);
                    lanes.get(intAletorio).getVehicles().add(jc0);
                    vehicles.add(jc0);
                    break;
                case 1 :
                    vel=Speeds.MEDIUM;
                    MovingVehicle jc1 = new MovingVehicle(x, y, speed , true , false , vel);
                    lanes.get(intAletorio).getVehicles().add(jc1);
                    vehicles.add(jc1);
                    break;
                case 2 :
                    vel=Speeds.FAST;
                    MovingVehicle jc2 = new MovingVehicle(x, y, speed , true , false , vel);
                    lanes.get(intAletorio).getVehicles().add(jc2);
                    vehicles.add(jc2);
                    break;
            }
            x+=140;
        }
        for (Lane currentLane:lanes)
        {
            for (Vehicle currentVehicle:currentLane.getVehicles())
            {
                currentVehicle.start();
            }
        }
    }

    public void turnOnBarrier() throws InterruptedException
    {
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
    //TODO: Put this in Lane Class
    public void changeDirection()
    {
        //Grab LaneArrayList and change direction
        for (Lane currentLane : lanes) {
            currentLane.changeMovementDirection();
            //System.out.println("set Moving false");
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
            for (Lane currentLane : lanes) {
                currentLane.setTrafficLightRed(true);
                //System.out.println("set Moving false");
            }
            trafficLigth=false;
            //System.out.println("Is Moving false");

        }

        else{

            Image imageTrafficLigthGreen = new Image("Assets/traffic_light_green_icon.png");
            imageTrafficLigth.setImage(imageTrafficLigthGreen);
            for (Lane currentLane : lanes) {
                currentLane.setTrafficLightRed(false);
                //System.out.println("set Moving false");
            }
            trafficLigth=true;
            //System.out.println("Is Moving true");
        }
    }

}
