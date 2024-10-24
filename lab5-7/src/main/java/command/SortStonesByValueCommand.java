package command;

import gems.Necklace;

public class SortStonesByValueCommand implements Command {
    private Necklace necklace;

    public SortStonesByValueCommand(Necklace necklace) {
        this.necklace = necklace;
    }

    @Override
    public void execute() {
        necklace.getNecklaceStones().sort((s1, s2) -> Double.compare(s2.getValue(), s1.getValue()));
        System.out.println("Stones in the necklace sorted by value.");
    }

    @Override
    public String printInfo() {
        return "Sort stones by value";
    }
}
