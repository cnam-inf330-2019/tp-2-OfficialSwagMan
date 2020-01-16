package net.cnam.inf330;

import java.util.Comparator;

public class PlaneFuelComparator implements Comparator<Plane> {

    @Override
    public int compare(Plane o1, Plane o2) {
        if (o1.getFuelCapacity() < o2.getFuelCapacity())
            return 1;
        else if (o1.getFuelCapacity() > o2.getFuelCapacity())
            return -1;
        return 0;
    }

}
