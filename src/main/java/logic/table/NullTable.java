package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;

public class NullTable implements Table {
    private List<Bumper> bumpers;
    private List<Target> targets;

    public NullTable() {
        this.bumpers = new ArrayList<>();
        this.targets = new ArrayList<>();
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public int getNumberOfDropTargets() {
        return 0;
    }

    @Override
    public int getCurrentlyDroppedDropTargets() {
        return 0;
    }

    @Override
    public void setCurrentlyDroppedDropTargets(int num) {
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
    }

    @Override
    public void upgradeAllBumpers() {
    }

    @Override
    public boolean isPlayableTable() {
        return false;
    }
}
