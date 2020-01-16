package net.cnam.inf330;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.sql.SQLOutput;

import static org.junit.Assert.*;

/**
 * Class for testing Airport Simulator application.
 */
public class AirportSimulatorTest {

    @Test
    public void testPriorityQueue() throws InvalidFuelCapacityException {
        AirportSimulator simulator = new AirportSimulator();
        simulator.createPlane(2, true);
        simulator.createPlane(1, true);
        simulator.createPlane(3, true);
        String list = "";
        while (!simulator.getFlyingPlanes().isEmpty()) {
            list+=simulator.getFlyingPlanes().poll().getName();
        }

        assertEquals("Plane2Plane0Plane1",list.toString());
    }

    @Test
    public void testInvalidFuelCapacityException() {
        AirportSimulator simulator = new AirportSimulator();
        ThrowingRunnable tr = () -> simulator.simulateTurnWithNewPlanes(3, 3, new int[]{1, 1, -1});

        assertThrows(InvalidFuelCapacityException.class, tr);
    }

    @Test
    public void testScenario1() throws InvalidFuelCapacityException {
        // Simulate scenario 1
        AirportSimulator simulator = new AirportSimulator();
        simulator.simulateTurnWithNewPlanes(3, 3, new int[]{1, 1, 1});
        simulator.simulateTurnWithNewPlanes(3, 3, new int[]{2, 2, 2});
        simulator.simulateTurnWithNewPlanes(0, 0, new int[]{0, 0, 0});
        simulator.simulateTurnWithNewPlanes(2, 3, new int[]{7, 5, 9});
        simulator.simulateTurnWithNewPlanes(2, 3, new int[]{6, 7, 5});
        simulator.simulateTurnWithNewPlanes(2, 3, new int[]{2, 9, 4});
        simulator.simulateTurnWithNewPlanes(3, 3, new int[]{1, 5, 0});
        simulator.simulateTurnWithNewPlanes(2, 3, new int[]{6, 2, 0});
        simulator.simulateTurnWithNewPlanes(1, 3, new int[]{0, 0, 0});

        // TODO 7. Test that scenario 1 does not cause any plane crashes
        // assert...
    }
}
