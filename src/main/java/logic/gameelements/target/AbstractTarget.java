package logic.gameelements.target;

import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target {
    protected boolean isActive;

    AbstractTarget() {
        this.isActive = true;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public void reset() {
        this.isActive = true;
    }

    @Override
    abstract public int hit();

    @Override
    abstract public int getScore();
}
