package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a null table, following the null object design pattern.
 * It's designed to be the first table in a new game. It is no possible to play
 * on this table.
 *
 * @author Gabriel Chaperon
 */
public class NullTable implements Table {
    private List<Bumper> bumpers;
    private List<Target> targets;

    /**
     * Creates a new table with zero bumpers and zero targets.
     */
    public NullTable() {
        this.bumpers = new ArrayList<>();
        this.targets = new ArrayList<>();
    }

    /**
     * The table name should always be the empty string.
     * @return  the table name.
     */
    @Override
    public String getTableName() {
        return "";
    }

    /**
     * The number of drop targets should always be zero.
     * @return  always zero
     */
    @Override
    public int getNumberOfDropTargets() {
        return 0;
    }

    /**
     * The number of currently dropped drop targets should always be zero.
     * @return  always zero.
     */
    @Override
    public int getCurrentlyDroppedDropTargets() {
        return 0;
    }

    /**
     * Does nothing by design.
     * @param num the new number of currentlyDroppedDropTargets
     */
    @Override
    public void setCurrentlyDroppedDropTargets(int num) {
    }

    /**
     * Always returns an empty list.
     * @return  the list of bumpers.
     */
    @Override
    public List<Bumper> getBumpers() {
        return this.bumpers;
    }

    /**
     * Always returns an empty list.
     * @return  the list of targets.
     */
    @Override
    public List<Target> getTargets() {
        return this.targets;
    }

    /**
     * Does nothing by design.
     */
    @Override
    public void resetDropTargets() {
    }

    /**
     * Does nothing by design.
     */
    @Override
    public void upgradeAllBumpers() {
    }

    /**
     * This type of table should not be playable by design.
     * @return  if this table is playable or not.
     */
    @Override
    public boolean isPlayableTable() {
        return false;
    }
}
