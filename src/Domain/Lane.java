package Domain;

import java.util.ArrayList;

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
        if(trafficLightRed){

            for (MovingVehicle currentVehicle:vehicles) {
                currentVehicle.setMoving(true);
                //System.out.println("set Moving false");
            }
            trafficLightRed =false;
            //System.out.println("Is Moving false");

        }

        else{
            for (MovingVehicle currentVehicle:vehicles) {
                currentVehicle.setMoving(false);
                //System.out.println("set Moving true");
            }
            trafficLightRed =true;
            //System.out.println("Is Moving true");
        }
    }

    //Here we validate vehicle movement
    @Override
    public void run() {

    }
}
