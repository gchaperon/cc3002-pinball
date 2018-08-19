package logic.gameelements.target;

import java.util.Observable;

/**
 * Abstract class that implements part of the {@link logic.gameelements.target.Target}
 * interface. The classes that extend this one should implement the methods that are
 * lacking here.
 *
 * @author Gabriel Chaperon
 * @see DropTarget
 * @see SpotTarget
 */
public abstract class AbstractTarget extends Observable implements Target {
    protected boolean isActive;

    /**
     * Creates a new Target object.
     * Initially the target is set to be active.
     * Constructors of subclasses may have additional fields.
     */
    AbstractTarget() {
        this.isActive = true;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public void reset() {
        if (!this.isActive)
            this.isActive = true;
    }

    @Override
    public boolean isSpotTarget() {
        return false;
    }

    @Override
    public boolean isDropTarget() {
        return false;
    }

    @Override
    abstract public int hit();

    @Override
    abstract public int getScore();
}
