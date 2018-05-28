package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConcreteTable implements Table {
    private int currentlyDroppedDropTargets;
    private int numberOfDropTarget;
    private String name;
    private List<Bumper> bumpers; //primero van los popBumpers
    private List<Target> targets; //primero van los dropTargets
    private long seed;

    public ConcreteTable(String name, int numberOfBumbers, double prob,
                         int numberOfSpotTargets, int numberOfDropTargets) {
        this(System.currentTimeMillis(), name, numberOfBumbers, prob, numberOfSpotTargets, numberOfDropTargets);
    }

    public ConcreteTable(long seed, String name, int numberOfBumpers, double prob,
                         int numberOfSpotTargets, int numberOfDropTargets) {
        this.name = name;
        this.seed = seed;

        bumpers = new ArrayList<>(numberOfBumpers);
        List<KickerBumper> kickerBumpers = new ArrayList<>();
        List<PopBumper> popBumpers = new ArrayList<>();
        Random rnd = new Random(seed);
        for (int i = 0; i < numberOfBumpers; i++) {
            if (rnd.nextFloat() < prob)
                popBumpers.add(new PopBumper());
            else
                kickerBumpers.add(new KickerBumper());
        }
        bumpers.addAll(popBumpers);
        bumpers.addAll(kickerBumpers);

        targets = new ArrayList<>(numberOfSpotTargets + numberOfDropTargets);
        this.numberOfDropTarget = numberOfDropTargets;
        for (int i = 0; i < numberOfDropTargets; i++)
            targets.add(i, new DropTarget());
        for (int i = numberOfSpotTargets; i < targets.size(); i++)
            targets.add(i, new SpotTarget());


    }

    @Override
    public String getTableName() {
        return this.name;
    }

    @Override
    public int getNumberOfDropTargets() {
        return this.numberOfDropTarget;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        return this.currentlyDroppedDropTargets;
    }

    @Override
    public void setCurrentlyDroppedDropTargets(int num) {
        this.currentlyDroppedDropTargets = num;
    }

    @Override
    public List<Bumper> getBumpers() {
        return this.bumpers;
    }

    @Override
    public List<Target> getTargets() {
        return this.targets;
    }

    @Override
    public void resetDropTargets() {
        for (Target dropTarget : this.targets.subList(0, numberOfDropTarget))
            dropTarget.reset();
    }

    @Override
    public void upgradeAllBumpers() {
        for (Bumper bumper : this.bumpers)
            bumper.upgrade();
    }

    @Override
    public boolean isPlayableTable() {
        return true;
    }
}
