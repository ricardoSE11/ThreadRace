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
        this.wall=true;
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

    public void changeMovementDirection(Boolean direction)
    {
        this.goingDown = direction;
        if(!this.goingDown){
            for(int i = vehicles.size()-1 ; i >= 0  ; i--)
            {
                this.vehicles.get(i).setGoingDown(this.goingDown);
            }
        }
        else{
            for(int x = 0 ; x < vehicles.size()  ; x++)
            {
                this.vehicles.get(x).setGoingDown(this.goingDown);
            }
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

    public void verifyMovement()
    {
        if(goingDown)
        {
            for(int i = 0; i < this.vehicles.size(); i++){
                MovingVehicle v = this.vehicles.get(i);
                int posY = (int)v.getY() + 10;//next iteration
                if(i == 0)
                {
                    if(!youShallNotPass(posY))
                    {
                        v.setMoving(false);
                    }else{
                        v.setMoving(true);
                    }
                }else{
                    MovingVehicle nextVehicle = this.vehicles.get(i-1);
                    int posY_Next = (int) nextVehicle.getY() + 10;
                    boolean accident = isThereAnAccident(posY,posY_Next,true);
                    if(!accident || !youShallNotPass(posY))
                    {
                        v.setMoving(false);
                    }else{
                        v.setMoving(true);
                    }
                }
            }
        }
        else{
            for(int i = 0; i < this.vehicles.size(); i++)
            {
                MovingVehicle me = this.vehicles.get(i);
                int posY_Me = me.getY() - 10;
                if(i == this.vehicles.size()-1)
                {
                    System.out.println("Hcesss");
                    if(!youShallNotPass(posY_Me))
                    {
                        me.setMoving(false);
                    }else{
                        me.setMoving(true);
                    }
                }else{
                    MovingVehicle beforeMe = this.vehicles.get(i + 1);
                    int posY_BeforeMe = beforeMe.getY() - 10;
                    boolean accident = isThereAnAccident(posY_BeforeMe, posY_Me,false);
                    if(!accident || !youShallNotPass(posY_Me))
                    {
                        me.setMoving(false);
                    }else{
                        me.setMoving(true);
                    }
                }
            }
        }
    }

    public boolean isThereAnAccident(int posY_VehicleOne, int posY_VehicleTwo, boolean direction)
    {
        if(posY_VehicleOne - posY_VehicleTwo >= 128){
            return false;
        }else{
            return true;
        }
    }

    public boolean youShallNotPass(int posY_Vehicle){
        if(this.wall){
            //the vehicle is far of the wall
            if(this.goingDown){
                if(posY_Vehicle >=240 && posY_Vehicle<=250)
                {
                    return false;
                }
                else{
                    return true;
                }
            }else{
                if(posY_Vehicle >=240 && posY_Vehicle<=400)
                {
                    return false;
                }
                else{
                    return true;
                }
            }
        }else{
            return true;
        }
    }

    public void deleteFirstVehicle()
    {
        if(!this.vehicles.isEmpty()){
            MovingVehicle temp = this.vehicles.get(0);
            int posY_FirstVehicle = temp.getY() - 64;
            if((posY_FirstVehicle) > 630){
                ((MovingVehicle) temp).setRunning(false);
                this.vehicles.remove(0);
            }
        }
    }

    public void deleteLastVehicle()
    {
        if(!this.vehicles.isEmpty()){
            MovingVehicle temp = this.vehicles.get(vehicles.size()-1);
            int posY_LastVehicle = temp.getY();
            if((posY_LastVehicle) <= 0){
                ((MovingVehicle) temp).setRunning(false);
                this.vehicles.remove(vehicles.size()-1);
            }
        }
    }

    public int getPosY_LastVehicle()
    {
        int last_Vehicle = this.vehicles.size() - 1;
        Vehicle temp = this.vehicles.get(last_Vehicle);
        int posY_LastVehicle = (int)temp.getY();
        return posY_LastVehicle;
    }

    public void deleteVehicle(int i)
    {
        this.vehicles.remove(i);
    }

    public void addVehicleLane(MovingVehicle v)
    {
        v.setX(this.xLeft);
        if(!this.vehicles.isEmpty()){
            int posY_LastVehicle = getPosY_LastVehicle();
            if((posY_LastVehicle-80) < 80){//I suppose this is the min posY of a vehicle
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
    
    public void checkQueue()
    {
        if(!this.queueVehicles.isEmpty()){
            int posY_LastVehicle = getPosY_LastVehicle();
            if((posY_LastVehicle-80) > 80) {
                MovingVehicle v = this.queueVehicles.poll();
                v.setMoving(true);
                v.setGoingDown(goingDown);
                this.vehicles.add(v);
            }
        }
    }
    //Here we validate vehicle movement
    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                verifyMovement();
                checkQueue();
                deleteFirstVehicle();
                deleteLastVehicle();

                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
