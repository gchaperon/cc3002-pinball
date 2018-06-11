package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.bumper.KickerBumper;
import logic.gameelements.bumper.PopBumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import visitor.Visitor;

import java.util.*;

/**
 * Concrete implementation of the {@link logic.table.Table} interface.
 *
 * @author Gabriel Chaperon
 */
public class ConcreteTable extends Observable implements Table, Observer {
    private int currentlyDroppedDropTargets;
    private final int numberOfDropTarget;
    private String name;
    // first the popBumpers
    private List<Bumper> bumpers;
    // first the dropTargets
    private List<Target> targets;

    /**
     * Creates a table with the variables used in {@link facade.HomeworkTwoFacade}.
     *
     * @param seed  The seed used to create bumper split with a given probability.
     *              This is for testing purposes, so when a table is created with
     *              the {@link facade.HomeworkTwoFacade} class it is used as
     *              System.currentTimeMillis().
     * @param name  The name of the table.
     * @param numberOfBumpers   The total number of bumpers.
     * @param prob  The probability of a bumper being a pop bumper.
     * @param numberOfSpotTargets   The number of spot targets.
     * @param numberOfDropTargets   The number of drop targets.
     */
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

    /**
     * This type of table should always be playable by design.
     * @return true if this table is playable.
     */
    @Override
    public boolean isPlayableTable() {
        return true;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the <code>notifyObservers</code>
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.accept((Visitor) arg);
        setChanged();
        notifyObservers(arg);
    }

    /**
     * Method that accepts a {@link visitor.Visitor} and uses double dispatch.
     *
     * @param visitor   The visitor that will be given this game and will
     *                  trigger its specific actions.
     */
    private void accept(Visitor visitor) {
        visitor.visitTable(this);
    }
}
