package DataObjects;

import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Slots {
    private LinkedHashSet<Integer> totalSlots;
    private TreeSet<Integer> freeSlots;

    public LinkedHashSet<Integer> getTotalSlots() {
        if (totalSlots == null) {
            totalSlots = new LinkedHashSet<>();
        }
        return totalSlots;
    }

    public TreeSet<Integer> getFreeSlots() {
        if (freeSlots == null) {
            freeSlots = new TreeSet<>();
        }
        return freeSlots;
    }
}
