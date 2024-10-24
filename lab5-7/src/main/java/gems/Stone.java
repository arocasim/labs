package gems;

import java.io.Serializable;
import java.util.Locale;

public class Stone implements Serializable {
    private String name;
    private double weight;
    private double value;
    private double transparency;
    private boolean isPrecious;
    private double rarity;

    public Stone(String name, double weight, double value, double transparency, boolean isPrecious, double rarity) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.transparency = transparency;
        this.isPrecious = isPrecious;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public double getTransparency() {
        return transparency;
    }

    @Override
    public String toString() {
        String type = isPrecious ? "Precious stone" : "Semi-precious stone";
        return String.format(Locale.ENGLISH, "%s: %s [Weight: %.2f carats, Price: %.2f USD, Transparency: %.2f%%, Rarity: %.2f]",
                type, name, weight, value, transparency, rarity);
    }

    public double getRarity() {
        return rarity;
    }

    public boolean isPrecious() {
        return isPrecious;
    }

    public void setPrecious(boolean precious) {
        isPrecious = precious;
    }
}
