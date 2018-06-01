package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;

import java.util.*;

public class ConcreteTable extends Observable implements Table, Observer {
    private int currentlyDroppedDropTargets;
    private final int numberOfDropTarget;
    private String name;
    private List<Bumper> bumpers; //primero van los popBumpers
    private List<Target> targets; //primero van los dropTargets

    public ConcreteTable(long seed, String name, int numberOfBumpers, double prob,
                         int numberOfSpotTargets, int numberOfDropTargets) {
        this.name = name;
        this.numberOfDropTarget = numberOfDropTargets;

        bumpers = new ArrayList<>(numberOfBumpers);
        List<KickerBumper> kickerBumpers = new ArrayList<>();
        List<PopBumper> popBumpers = new ArrayList<>();
        Random rnd = new Random(seed);
        PopBumper popBumper;
        KickerBumper kickerBumper;
        for (int i = 0; i < numberOfBumpers; i++) {
            if (rnd.nextFloat() < prob) {
                popBumper = new PopBumper(seed);
                popBumper.addObserver(this);
                popBumpers.add(popBumper);
            }
            else {
                kickerBumper = new KickerBumper(seed);
                kickerBumper.addObserver(this);
                kickerBumpers.add(kickerBumper);
            }
        }
        bumpers.addAll(popBumpers);
        bumpers.addAll(kickerBumpers);

        targets = new ArrayList<>();
        SpotTarget spotTarget;
        DropTarget dropTarget;
        for (int i = 0; i < numberOfDropTargets; i++) {
            dropTarget = new DropTarget(seed);
            dropTarget.addObserver(this);
            targets.add(dropTarget);
        }
        for (int i = 0; i < numberOfSpotTargets; i++) {
            spotTarget = new SpotTarget();
            spotTarget.addObserver(this);
            targets.add(spotTarget);
        }
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
        this.currentlyDroppedDropTargets = 0;
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

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
