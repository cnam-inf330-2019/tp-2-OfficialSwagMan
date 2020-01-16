package net.cnam.inf330;

/**
 * Main class for running the AirportSimulator application.
 */
public class Main {

    /**
     * Main method for running the Airport Simulator application.
     * @param args --
     */
    public static void main(String[] args) {
        AirportSimulator simulator = new AirportSimulator();

        try {
            simulator.simulateTurnWithNewPlanes(3, 3, new int[]{1, 1, 1});
            simulator.simulateTurnWithNewPlanes(3, 3, new int[]{2, 2, 2});
            simulator.simulateTurnWithNewPlanes(0, 0, new int[]{0, 0, 0});
            simulator.simulateTurnWithNewPlanes(2, 3, new int[]{7, 5, 9});
            simulator.simulateTurnWithNewPlanes(2, 3, new int[]{6, 7, 5});
            simulator.simulateTurnWithNewPlanes(2, 3, new int[]{2, 9, 4});
            simulator.simulateTurnWithNewPlanes(3, 3, new int[]{1, 5, 0});
            simulator.simulateTurnWithNewPlanes(2, 3, new int[]{6, 2, 0});
            simulator.simulateTurnWithNewPlanes(1, 3, new int[]{0, 0, 0});
        } catch (InvalidFuelCapacityException e) {
            e.printStackTrace();
        }

        while (!simulator.isSimulationOver())
            simulator.simulateTurn();


    }
}