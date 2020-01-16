package net.cnam.inf330;

public class BigPlane extends Plane {
    public BigPlane(int tick, String name, boolean isFlying, int fuelCapacity) {
        super(tick, name, isFlying, fuelCapacity);
    }

    @Override
    public void fly() {
        fuelCapacity=fuelCapacity-2;
    }
}
