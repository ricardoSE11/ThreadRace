package Domain;

import sun.awt.windows.ThemeReader;
import sun.security.provider.MD4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Lane extends Thread {


    private ArrayList<MovingVehicle> vehicles;
    private int xLeft;
    private int xRight;
    private int yTop;
    private int yBottom;
    private int positionBarrierUp;
    private int positionBarrierBottom;
    private boolean goingDown;
    private boolean trafficLightRed;
    private boolean wall;
    private boolean running;
    private Queue<MovingVehicle> queueVehicles;


    public Lane(int xLeft, int xRight, int yTop, int yBottom,
                int positionBarrierUp, int positionBarrierBottom , boolean goingDown , boolean trafficLightRed) {
        this.vehicles = new ArrayList<>();
        this.xLeft = xLeft;
        this.xRight = xRight;
        this.yTop = yTop;
        this.yBottom = yBottom;
        this.positionBarrierUp = positionBarrierUp;
        this.positionBarrierBottom = positionBarrierBottom;
        this.goingDown = goingDown;
        this.trafficLightRed = trafficLightRed;
        this.wall=false;
        this.queueVehicles = new LinkedList<>();
        this.running = true;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public ArrayList<MovingVehicle> getVehicles() {
        return vehicles;
    }

    public boolean isTrafficLightRed() {
        return trafficLightRed;
    }

    public void setTrafficLightRed(boolean trafficLightRed) {
        this.trafficLightRed = trafficLightRed;
    }

    public boolean isGoingDown() {
        return goingDown;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }

    public void setVehicles(ArrayList<MovingVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public int getxLeft() {
        return xLeft;
    }

    public void setxLeft(int xLeft) {
        this.xLeft = xLeft;
    }

    public int getxRight() {
        return xRight;
    }

    public void setxRight(int xRight) {
        this.xRight = xRight;
    }

    public int getyTop() {
        return yTop;
    }

    public void setyTop(int yTop) {
        this.yTop = yTop;
    }

    public int getyBottom() {
        return yBottom;
    }

    public void setyBottom(int yBottom) {
        this.yBottom = yBottom;
    }

    public int getPositionBarrierUp() {
        return positionBarrierUp;
    }

    public void setPositionBarrierUp(int positionBarrierUp) {
        this.positionBarrierUp = positionBarrierUp;
    }

    public int getPositionBarrierBottom() {
        return positionBarrierBottom;
    }

    public void setPositionBarrierBottom(int positionBarrierBottom) {
        this.positionBarrierBottom = positionBarrierBottom;
    }

    public void addVehicle(MovingVehicle vehicle)
    {
        this.vehicles.add(vehicle);
    }

    public void changeMovementDirection()
    {
        if(!goingDown)
        {
            for (MovingVehicle currentVehicle:vehicles)
            {
                currentVehicle.setGoingDown(false);
            }
            goingDown=true;
        }

        else{
            for (MovingVehicle currentVehicle:vehicles)
            {
                currentVehicle.setGoingDown(true);
            }
            goingDown=false;
        }

    }

    public void interruptVehicleMovement()
    {
        this.trafficLightRed=!this.trafficLightRed;
        for (MovingVehicle currentVehicle:vehicles) {
            currentVehicle.setMoving(!this.trafficLightRed);
            //System.out.println("set Moving false");
        }
    }

    public void verifyMovement(){

        if(goingDown){
            for(int i = 0; i < this.vehicles.size(); i++){
                MovingVehicle v = this.vehicles.get(i);
                int posY = (int)v.getY() + 10;//next iteration
                if(i == 0){
                    /*if(!youShallNotPass(posY)){
                        v.setMoving(false);
                    }else{
                        v.setMoving(true);
                    }*/
                }else{
                    MovingVehicle nextVehicle = this.vehicles.get(i-1);
                    int posY_Next = (int) nextVehicle.getY() + 10;
                    boolean accident = isThereAnAccident(posY_Next,posY,true);
                    if(accident/* && !youShallNotPass(posY)*/){
                        v.setMoving(false);
                    }else{
                        v.setMoving(true);
                    }
                }
            }
        }
        else
            {
            int last = this.vehicles.size()-1;
            for(int i = 0; i < this.vehicles.size(); i++){
                MovingVehicle v = this.vehicles.get(i);
                int posY = (int)v.getY() + 10;//next iteration
                if(i == last){
                    /*if(!youShallNotPass(posY)){
                        v.setMoving(false);
                    }else{
                        v.setMoving(true);
                    }*/
                }else{
                    MovingVehicle nextVehicle = this.vehicles.get(i+1);
                    int posY_Next = (int) nextVehicle.getY() - 10;
                    boolean accident = isThereAnAccident(posY_Next,posY,false);
                    if(accident /*&& !youShallNotPass(posY)*/){
                        v.setMoving(false);
                    }else{
                        v.setMoving(true);
                    }
                }
            }
        }
    }
    public boolean isThereAnAccident(int posY_VehicleOne, int posY_VehicleTwo, boolean direction){

        if(direction){
            if((posY_VehicleOne - 66) - (posY_VehicleTwo + 66) < 0){
                return true;
            }else{
                return false;
            }
        }else{
            if((posY_VehicleTwo - 66) - (posY_VehicleOne + 66) < 0){
                return true;
            }else{
                return false;
            }
        }
    }

    public boolean youShallNotPass(int posY_Vehicle){
        if(this.wall){
            //the vehicle is far of the wall
            if(365 > (posY_Vehicle - 64) && 370 < (posY_Vehicle +64)){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public void deleteFirstVehicle(){

        if(!this.vehicles.isEmpty()){
            MovingVehicle temp = this.vehicles.get(0);
            int posY_FirstVehicle = (int)temp.getY() - 64;
            if((posY_FirstVehicle+66) > 630){
                ((MovingVehicle) temp).setRunning(false);
                this.vehicles.remove(0);
            }
        }

    }

    public int getPosY_LastVehicle(){
        int last_Vehicle = this.vehicles.size() - 1;
        Vehicle temp = this.vehicles.get(last_Vehicle);
        int posY_LastVehicle = (int)temp.getY();
        return posY_LastVehicle;
    }

    public void deleteVehicle(int i){
        this.vehicles.remove(i);
    }

    public void addVehicleLane(MovingVehicle v){
        v.setX(this.xLeft);
        if(!this.vehicles.isEmpty()){
            int posY_LastVehicle = getPosY_LastVehicle();
            if((posY_LastVehicle-66) < 66){//I suppose this is the min posY of a vehicle
                v.setMoving(false);
                this.queueVehicles.add(v);
            }else{
                v.setMoving(true);
                v.setGoingDown(goingDown);
                this.vehicles.add(v);
            }
        }else{
            v.setMoving(true);
            v.setGoingDown(goingDown);
            this.vehicles.add(v);
        }
    }
    
    public void checkQueue(){

        if(!this.queueVehicles.isEmpty()){
            int posY_LastVehicle = getPosY_LastVehicle();
            if((posY_LastVehicle-66) > 66){
                MovingVehicle v = this.queueVehicles.poll();
                v.setMoving(true);
                v.setGoingDown(goingDown);
            }
        }
    }
    //Here we validate vehicle movement
    @Override
    public void run() {
        while (running){
            try {
                verifyMovement();
                checkQueue();
                deleteFirstVehicle();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
