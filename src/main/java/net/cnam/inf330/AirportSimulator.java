package net.cnam.inf330;

import java.util.*;

/**
 * Class for implementing the simulation system.
 */
public class AirportSimulator implements Observer {

    private final int NUM_RUNWAYS = 3;

    private int tick;
    private int planeCount;

    private PriorityQueue<Plane> flyingPlanes;
    // FIXME The type should be Queue<Plane>
    private Queue landedPlanes;

    public AirportSimulator() {
        this.tick = 1;
        this.planeCount = 0;
        flyingPlanes = new PriorityQueue<Plane>(10, new PlaneFuelComparator());
        // FIXME The type should be LinkedList<Plane>
        landedPlanes = new LinkedList();
    }

    /**
     * Simulate a turn while inserting new planes into the system.
     *
     * @param numNewLanding         The number of new planes in the air waiting to land
     * @param numNewTakingOff       The number of new planes on the ground waiting to take off
     * @param fuelCapacitiesLanding The starting fuel capacity of each plane in the air waiting to land
     */
    public void simulateTurnWithNewPlanes(int numNewLanding, int numNewTakingOff, int[] fuelCapacitiesLanding) throws InvalidFuelCapacityException {
        System.out.println();
        System.out.println("=====================================================================");
        System.out.println("Turn " + this.tick + " : creating new planes");
        System.out.println("=====================================================================");

        // Step 1 : Insert new planes into the system
        for (int i = 0; i < numNewLanding; i++)
            createPlane(fuelCapacitiesLanding[i], true);
        for (int i = 0; i < numNewTakingOff; i++)
            // The fuel capacity of planes waiting to take off doesn't actually matter
            // since they are removed from the system once they take off
            createPlane(Plane.MAX_FUEL_CAPACITY, false);

        this.simulateTurn();
    }

    /**
     * Simulate a turn without inserting any new planes into the system
     *
     * @return true if the simulation can continue, false otherwise
     */
    public void simulateTurn() {
        System.out.println();
        System.out.println("=====================================================================");
        System.out.println("Turn " + this.tick + " : simulating");
        System.out.println("=====================================================================");

        // Step 2 : Decrement fuel capacity of flying planes
        for (Plane plane : flyingPlanes)
            plane.fly();

        // Step 3 : Land planes with a fuel capacity of zero
        int numRunwaysUsed = 0;
        int numPlanesCrashed = 0;
        if (!flyingPlanes.isEmpty()) {
            int i = 0;
            boolean isLandingEmergency = flyingPlanes.peek().getFuelCapacity() == 0;
            // Land at most NUM_RUNWAYS planes that have reached zero fuel
            while (i < NUM_RUNWAYS && isLandingEmergency) {
                Plane planeToLand = flyingPlanes.poll();
                planeToLand.land(i);
                numRunwaysUsed++;
                isLandingEmergency = !flyingPlanes.isEmpty() && flyingPlanes.peek().getFuelCapacity() == 0;
                i++;
            }
            // If there are more planes with zero fuel still in the air, crash them
            if (isLandingEmergency) {
                while (isLandingEmergency) {
                    Plane planeToLand = flyingPlanes.poll();
                    planeToLand.crash();
                    numPlanesCrashed++;
                    isLandingEmergency = !flyingPlanes.isEmpty() && flyingPlanes.peek().getFuelCapacity() == 0;
                }
            }
        }

        // Step 4 : If less than NUM_RUNWAYS planes were landed during the previous step,
        // Use the remaining runways to either land or fly planes
        while ((!landedPlanes.isEmpty() || !flyingPlanes.isEmpty()) && numRunwaysUsed < NUM_RUNWAYS) {
            // If there are more landed planes than flying planes, fly planes
            if (landedPlanes.size() > flyingPlanes.size()) {
                Plane planeToTakeOff = (Plane) landedPlanes.poll();
                planeToTakeOff.takeOff(numRunwaysUsed);
            }
            // Otherwise, land planes
            else {
                Plane planeToLand = (Plane) flyingPlanes.poll();
                planeToLand.land(numRunwaysUsed);
            }
            numRunwaysUsed++;
        }

        // Step 5 : Increment clock
        this.tick++;

        System.out.println();
        System.out.println("Number of crashed planes : " + numPlanesCrashed);
        System.out.println("Number of planes on the ground : " + landedPlanes.size());
        System.out.println("Number of planes in the air : " + flyingPlanes.size());
    }

    /**
     * blabla
     *
     * @param fuelCapacity
     * @param flying
     */
    public void createPlane(int fuelCapacity, boolean flying) throws InvalidFuelCapacityException {

        if(fuelCapacity<0) throw new InvalidFuelCapacityException("Fuel capacity is negative");

        String name = "Plane" + planeCount++;
        Plane plane = new NormalPlane(this.tick, name, flying, fuelCapacity);
        System.out.println("Created plane : " + name + " (" + fuelCapacity + ", " +
                (flying ? "air" : "ground") + ")");
        if (flying)
            flyingPlanes.add(plane);
        else
            landedPlanes.add(plane);
    }

    /**
     * blabla
     *
     * @return
     */
    public boolean isSimulationOver() {
        // Simulation is over if both queues are empty, otherwise it can continue
        return this.flyingPlanes.isEmpty() && this.landedPlanes.isEmpty();
    }

    public PriorityQueue<Plane> getFlyingPlanes() {
        return flyingPlanes;
    }

    public void setFlyingPlanes(PriorityQueue<Plane> flyingPlanes) {
        this.flyingPlanes = flyingPlanes;
    }

    public Queue getLandedPlanes() {
        return landedPlanes;
    }

    public void setLandedPlanes(Queue landedPlanes) {
        this.landedPlanes = landedPlanes;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Action"+(String)arg);
    }
}