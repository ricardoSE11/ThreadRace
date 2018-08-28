package Domain;

import java.util.ArrayList;

public class Lane {

    //
    private ArrayList<MovingVehicle> vehicles;
    private int xLeft;
    private int xRight;
    private int yTop;
    private int yBottom;
    private int positionBarrierUp;
    private int positionBarrierBottom;

    public Lane(ArrayList<MovingVehicle> vehicles, int xLeft, int xRight, int yTop, int yBottom, int positionBarrierUp, int positionBarrierBottom) {
        this.vehicles = vehicles;
        this.xLeft = xLeft;
        this.xRight = xRight;
        this.yTop = yTop;
        this.yBottom = yBottom;
        this.positionBarrierUp = positionBarrierUp;
        this.positionBarrierBottom = positionBarrierBottom;
    }

    public ArrayList<MovingVehicle> getVehicles() {
        return vehicles;
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

    public void funchionMoveichon(){

        vehicles.get(0).getY();

    }
}
