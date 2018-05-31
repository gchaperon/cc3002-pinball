package logic.gameelements.target;

import java.util.Observable;

public abstract class AbstractTarget extends Observable implements Target {
    boolean isActive;
    double probBonus;
    long seed;

    AbstractTarget(double probBonus, long seed) {
        this.isActive = true;
        this.probBonus = probBonus;
        this.seed = seed;
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
