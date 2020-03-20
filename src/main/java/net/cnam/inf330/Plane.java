package net.cnam.inf330;

import java.util.Observable;

/**
 * Class for implementing the planes operated by the simulation system.
 */
public abstract class Plane extends Observable implements IPlane {

    public static final int MAX_FUEL_CAPACITY = 5;

    private int creationTick;
    private String name;
    private boolean isFlying;
    protected int fuelCapacity;

    public Plane(int tick, String name, boolean isFlying, int fuelCapacity) {
        this.creationTick = tick;
        this.name = name;
        this.isFlying = isFlying;
        this.fuelCapacity = fuelCapacity;
        // FIXME this.addObserver();
    }

    public void fly() {
        this.fuelCapacity--;
    }

    public void takeOff(int runway) {
        this.isFlying = true;
        System.out.println("Plane taking off from runway " + runway + " : " + this.name);
        notifyObservers("takeoff");
    }

    public void land(int runway) {
        this.isFlying = false;
        if (this.fuelCapacity == 0)
            System.out.println("Plane landing (emergency) on runway " + runway + " : " + this.name);
        else
            System.out.println("Plane landing on runway " + runway + " : " + this.name);
        notifyObservers("land");
    }

    public void crash() {
        this.isFlying = false;
        System.out.println("Plane crashing : " + this.name);
        notifyObservers("crash");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getCreationTick() {
        return creationTick;
    }

    public void setCreationTick(int creationTick) {
        this.creationTick = creationTick;
    }

}