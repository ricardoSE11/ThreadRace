package Domain;

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
    private boolean pause;
    private boolean wall;
    private boolean running;
    private Queue<MovingVehicle> queueVehicles;


    public Lane(int xLeft, int xRight, int yTop, int yBottom,
                int positionBarrierUp, int positionBarrierBottom , boolean goingDown , boolean wall) {

        this.vehicles = new ArrayList<>();
        this.xLeft = xLeft;
        this.xRight = xRight;
        this.yTop = yTop;
        this.yBottom = yBottom;
        this.positionBarrierUp = positionBarrierUp;
        this.positionBarrierBottom = positionBarrierBottom;
        this.goingDown = goingDown;
        this.wall=wall;
        this.queueVehicles = new LinkedList<>();
        this.running = true;
        this.pause = true;
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

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
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
        if(this.pause)
        {
            for (MovingVehicle currentVehicle:vehicles) {
                currentVehicle.setMoving(false);
            }
            this.pause=false;
        }
        else{
            for (MovingVehicle currentVehicle:vehicles) {
                currentVehicle.setMoving(true);
            }
            this.pause=true;
        }
    }

    public void verifyMovement()
    {
        if(this.pause){
            if(goingDown)
            {
                for(int i = 0; i < this.vehicles.size(); i++){
                    MovingVehicle vehicleTemp = this.vehicles.get(i);
                    int posY = vehicleTemp.getY();

                    if(i == 0)
                    {
                        if(!isBarrierInFront(posY))
                        {
                            vehicleTemp.setMoving(false);
                        }else{
                            vehicleTemp.setMoving(true);
                        }
                    }
                    else
                    {
                        MovingVehicle nextVehicle = this.vehicles.get(i-1);
                        int posY_Next = (int) nextVehicle.getY();
                        boolean accident = isThereAnAccident(posY_Next+10,posY+10,true);

                        if(accident || !isBarrierInFront(posY))
                        {
                            vehicleTemp.setMoving(false);

                        }
                        else{

                            vehicleTemp.setMoving(true);

                        }

                    }
                }
            }
            else
            {
                for(int i = 0; i < this.vehicles.size(); i++){
                    MovingVehicle vehicleTemp = this.vehicles.get(i);
                    int posY = vehicleTemp.getY();

                    if(i == this.vehicles.size()-1)
                    {
                        if(!isBarrierInFront(posY))
                        {
                            vehicleTemp.setMoving(false);
                        }else{
                            vehicleTemp.setMoving(true);
                        }
                    }
                    else
                    {
                        MovingVehicle nextVehicle = this.vehicles.get(i+1);
                        int posY_Next = (int) nextVehicle.getY();
                        boolean accident = isThereAnAccident(posY-10,posY_Next-10,true);

                        if(accident || !isBarrierInFront(posY_Next))
                        {
                            vehicleTemp.setMoving(false);

                        }
                        else{

                            vehicleTemp.setMoving(true);

                        }
                    }
                }
            }
        }


    }

    public boolean isThereAnAccident(int posY_VehicleOne, int posY_VehicleTwo, boolean direction)
    {
        if(posY_VehicleOne - posY_VehicleTwo >= 160){

            return false;

        }
        else{
            return true;
        }
    }

    public boolean isBarrierInFront(int posY_Vehicle){

        if(this.wall){
            //the vehicle is far of the wall
            if(this.goingDown){
                if(posY_Vehicle >=230 && posY_Vehicle<=250)
                {
                    return false;
                }
                else{
                    return true;
                }
            }else{
                if(posY_Vehicle >=240 && posY_Vehicle<=420)
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
            if(goingDown)
            {
                int posY_LastVehicle = getPosY_LastVehicle();
                if((posY_LastVehicle) < 70){//I suppose this is the min posY of a vehicle
                    v.setMoving(false);
                    this.queueVehicles.add(v);
                }else{
                    v.setMoving(true);
                    this.vehicles.add(v);
                }
            }
            else{
                int posY_FirstVehicle = this.vehicles.get(0).getY();
                if((posY_FirstVehicle+70) > 550){//I suppose this is the min posY of a vehicle
                    v.setMoving(false);
                    this.queueVehicles.add(v);
                }else{
                    v.setMoving(true);
                    this.vehicles.add(0,v);
                }
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
            if((posY_LastVehicle) > 70){
                MovingVehicle v = this.queueVehicles.poll();
                v.setMoving(true);
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
