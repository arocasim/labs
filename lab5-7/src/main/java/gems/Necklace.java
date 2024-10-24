package gems;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Necklace implements Serializable {

    private List<Stone> availableStones;
    private List<Stone> necklaceStones;

    public Necklace() {
        this.availableStones = new ArrayList<>();
        this.necklaceStones = new ArrayList<>();
    }

    public double getTotalWeight() {
        return necklaceStones.stream().mapToDouble(Stone::getWeight).sum();
    }

    public double getTotalValue() {
        return necklaceStones.stream().mapToDouble(Stone::getValue).sum();
    }

    public List<Stone> getNecklaceStones() {
        return necklaceStones;
    }

    public List<Stone> getAvailableStones() {
        return availableStones;
    }

    public void showAvailableStones() {
        if (availableStones.isEmpty()) {
            System.out.println("No available stones.");
        } else {
            System.out.println("Available stones:");
            for (int i = 0; i < availableStones.size(); i++) {
                System.out.println((i + 1) + ". " + availableStones.get(i));
            }
        }
    }
}
