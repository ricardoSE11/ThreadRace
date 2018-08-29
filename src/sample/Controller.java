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

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Controller {

    //TODO: Change this to a ArrayList<Lane>
    private ArrayList<Lane> lanes;
    private ArrayList<MovingVehicle> vehicles;

    //Vars
    private boolean areBarriersOn;
    private boolean pause;

    //Vars GUI
    private int speed=0;
    private boolean barrierOne;
    private boolean barrierTwo;
    private boolean barrierThree;
    private boolean barrierFour;
    private boolean barrierFive;
    private boolean barrierSix;
    private boolean barrierSeven;
    private boolean barrierEight;
    private boolean barrierNine;
    private boolean barrierTen;
    private boolean barrierEleven;
    private Random aleatorio;

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

    @FXML private ImageView imageBarrierOne;
    @FXML private ImageView imageBarrierTwo;
    @FXML private ImageView imageBarrierThree;
    @FXML private ImageView imageBarrierFour;
    @FXML private ImageView imageBarrierFive;
    @FXML private ImageView imageBarrierSix;
    @FXML private ImageView imageBarrierSeven;
    @FXML private ImageView imageBarrierEight;
    @FXML private ImageView imageBarrierNine;
    @FXML private ImageView imageBarrierTen;
    @FXML private ImageView imageBarrierEleven;



    public Controller()
    {
        System.out.println("Instantiating a new (RSE)Controller");
    }


    public void init(Stage stage)
    {
        this.vehicles = new ArrayList<>();
        this.lanes = new ArrayList<>();
        this.areBarriersOn = false;

        this.goingDown = true;

        barrierOne = false;
        barrierTwo = false;
        barrierThree = false;
        barrierFour = false;
        barrierFive = false;
        barrierSix = false;
        barrierSeven = false;
        barrierEight = false;
        barrierNine = false;
        barrierTen = false;
        barrierEleven = false;

        this.pause= false;

        this.aleatorio = new Random(System.currentTimeMillis());
        this.stage = stage;

        initiLanes();
    }

    public void initiLanes()
    {
        System.out.println("Created Lanes ArrayList");
        int yTop = 0;
        int yBottom = 630;
        int barrierUp=365;
        int barrierBottom=370;

        Lane laneOne = new Lane(145 , 235 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneTwo = new Lane(285 , 365 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneThree = new Lane(420 , 500 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneFour = new Lane(555 , 635 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneFive = new Lane(690 , 770 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneSix = new Lane(828 , 908 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneSeven = new Lane(962 , 1042 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneEight = new Lane(1100 , 1180 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneNine = new Lane(1235 , 1315 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneTen = new Lane(1370 , 1450 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);
        Lane laneEleven = new Lane(1506 , 1586 , yTop , yBottom ,barrierUp , barrierBottom , this.goingDown);

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

        for (Lane currentLane:this.lanes)
        {
            currentLane.start();
        }

    }

    public void setUpSimulationGraphics()
    {
        try
        {
            bgImage = new Image(new FileInputStream("src/Assets/background_icon_0.png"));

            g = this.canvasDibujo.getGraphicsContext2D();
            canvasDibujo.setWidth(1900);
            canvasDibujo.setHeight(1000);

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

        switch (speed){
            case 0 :
                vel=Speeds.SLOW;
                if (number  == 1) {
                    insertVh_LessCharged(vel);
                }
                else
                {
                    insertVh_RandomLane(speed,number,vel);
                }
                break;
            case 1 :
                vel=Speeds.MEDIUM;
                if (number  == 1) {
                    insertVh_LessCharged(vel);
                }
                else
                {
                    insertVh_RandomLane(speed,number,vel);
                }
                break;
            case 2 :
                vel=Speeds.FAST;
                if (number  == 1) {
                    insertVh_LessCharged(vel);
                }
                else
                {
                    insertVh_RandomLane(speed,number,vel);
                }
                break;
        }
    }

    public void insertVh_LessCharged(Speeds vel) throws FileNotFoundException {
        ArrayList<Integer> vehiclesByLane = new ArrayList<>();
        for(int i = 0; i < lanes.size(); i++){
            vehiclesByLane.add(lanes.get(i).getVehicles().size());
        }

        if(this.goingDown){
            int minIndex = vehiclesByLane.indexOf(Collections.min(vehiclesByLane));
            MovingVehicle jc0 = new MovingVehicle(lanes.get(minIndex).getxLeft(), 0, speed , true , this.goingDown , vel , true);
            lanes.get(minIndex).addVehicle(jc0);
            jc0.start();
        }
        else{
            int minIndex = vehiclesByLane.indexOf(Collections.min(vehiclesByLane));
            MovingVehicle newVehicle = new MovingVehicle(lanes.get(minIndex).getxLeft(), 630, speed , true , false , vel , true);
            lanes.get(minIndex).addVehicleLane(newVehicle);
            newVehicle.start();
        }
    }

    public void insertVh_RandomLane(int speed, int howMany, Speeds vel) throws FileNotFoundException
    {
        int i = 0;
        while(i < howMany)
        {
            int intAleatorio = aleatorio.nextInt(11);
            if(this.goingDown){
                        aleatorio.setSeed(System.currentTimeMillis());
                        MovingVehicle jc0 = new MovingVehicle(this.lanes.get(intAleatorio).getxLeft(), 0, speed , true , this.goingDown , vel , true);
                        this.lanes.get(intAleatorio).addVehicleLane(jc0);
                        jc0.start();

            }
            else{

                aleatorio.setSeed(System.currentTimeMillis());
                MovingVehicle newVehicle = new MovingVehicle(this.lanes.get(intAleatorio).getxLeft(), 630, speed , true , false , vel , true);
                this.lanes.get(intAleatorio).addVehicleLane(newVehicle);
                newVehicle.start();
            }
            i+=1;
        }
    }

    public void reDraw(Image backgroundImage)
    {
        canvasDibujo.setWidth(1900);
        canvasDibujo.setHeight(1000);
        g = this.canvasDibujo.getGraphicsContext2D();
        g.clearRect(0, 0, 1900, 1000);
        g.drawImage(backgroundImage , 0 , 0);

        for (Lane currentLane : this.lanes)
        {
            for (MovingVehicle currentVehicle : currentLane.getVehicles())
            {
                if(currentVehicle.isRunning())
                {
                    g.drawImage(currentVehicle.getImage() , currentVehicle.getX() , currentVehicle.getY());
                }
            }
        }
    }

    //Voids GUI

    //TODO: Put this in Lane Class
    public void changeDirection()
    {
        this.goingDown=!this.goingDown;
        //Grab LaneArrayList and change direction
        for(int i = 0; i < this.lanes.size(); i++){
            this.lanes.get(i).changeMovementDirection(this.goingDown);
        }
    }

    public void increaseSpeed()
    {

        int new_position_tachomer = speed+1;
        if(new_position_tachomer<3)
        {
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

    public void reduceSpeed()
    {

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

    public void startSimulation()
    {


    }

    public void interruptVehicleMovement()
    {
        if(this.pause){
            Image imageTrafficLigthRed = new Image("Assets/traffic_light_green_icon.png");
            imageTrafficLigth.setImage(imageTrafficLigthRed);
            for (Lane currentLane : lanes) {
                currentLane.interruptVehicleMovement();
            }
            this.pause=false;
        }
        else{

            Image imageTrafficLigthGreen = new Image("Assets/traffic_light_red_icon.png");
            imageTrafficLigth.setImage(imageTrafficLigthGreen);
            for (Lane currentLane : lanes) {
                currentLane.interruptVehicleMovement();
            }
            this.pause=true;
        }
    }

    public void turnOnBarrierOne()
    {
        if (barrierOne)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierOne.setImage(imageBarrier);
            lanes.get(0).setWall(true);
            barrierOne=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierOne.setImage(imageBarrier);
            lanes.get(0).setWall(false);
            barrierOne=true;
        }
    }
    public void turnOnBarrierTwo()
    {
        if (barrierTwo)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierTwo.setImage(imageBarrier);
            lanes.get(1).setWall(true);
            barrierTwo=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierTwo.setImage(imageBarrier);
            lanes.get(1).setWall(false);
            barrierTwo=true;
        }
    }
    public void turnOnBarrierThree()
    {
        if (barrierThree)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierThree.setImage(imageBarrier);
            lanes.get(2).setWall(true);
            barrierThree=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierThree.setImage(imageBarrier);
            lanes.get(2).setWall(false);
            barrierThree=true;
        }
    }
    public void turnOnBarrierFour()
    {
        if (barrierFour)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierFour.setImage(imageBarrier);
            lanes.get(3).setWall(true);
            barrierFour=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierFour.setImage(imageBarrier);
            lanes.get(3).setWall(false);
            barrierFour=true;
        }
    }
    public void turnOnBarrierFive()
    {
        if (barrierFive)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierFive.setImage(imageBarrier);
            lanes.get(4).setWall(true);
            barrierFive=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierFive.setImage(imageBarrier);
            lanes.get(4).setWall(false);
            barrierFive=true;
        }
    }
    public void turnOnBarrierSix()
    {
        if (barrierSix)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierSix.setImage(imageBarrier);
            lanes.get(5).setWall(true);
            barrierSix=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierSix.setImage(imageBarrier);
            lanes.get(5).setWall(false);
            barrierSix=true;
        }
    }
    public void turnOnBarrierSeven()
    {
        if (barrierSeven)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierSeven.setImage(imageBarrier);
            lanes.get(6).setWall(true);
            barrierSeven=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierSeven.setImage(imageBarrier);
            lanes.get(6).setWall(false);
            barrierSeven=true;
        }
    }
    public void turnOnBarrierEight()
    {
        if (barrierEight)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierEight.setImage(imageBarrier);
            lanes.get(7).setWall(true);
            barrierEight=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierEight.setImage(imageBarrier);
            lanes.get(7).setWall(false);
            barrierEight=true;
        }
    }
    public void turnOnBarrierNine()
    {
        if (barrierNine)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierNine.setImage(imageBarrier);
            lanes.get(8).setWall(true);
            barrierNine=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierNine.setImage(imageBarrier);
            lanes.get(8).setWall(false);
            barrierNine=true;
        }
    }
    public void turnOnBarrierTen()
    {
        if (barrierTen)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierTen.setImage(imageBarrier);
            lanes.get(9).setWall(false);
            barrierTen=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierTen.setImage(imageBarrier);
            lanes.get(9).setWall(false);
            barrierTen=true;
        }
    }
    public void turnOnBarrierEleven()
    {
        if (barrierEleven)
        {
            Image imageBarrier = new Image("Assets/barrier_icon.png");
            imageBarrierEleven.setImage(imageBarrier);
            lanes.get(10).setWall(true);
            barrierEleven=false;
        }

        else
        {
            Image imageBarrier = new Image("Assets/gray_icon.png");
            imageBarrierEleven.setImage(imageBarrier);
            lanes.get(10).setWall(false);
            barrierEleven=true;
        }
    }

}
