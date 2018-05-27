package logic.bonus;

import controller.Game;

public abstract class AbstractBonus implements Bonus {
    int timesTriggered;

    AbstractBonus() {
        this.timesTriggered = 0;
    }

    @Override
    public int timesTriggered() {
        return this.timesTriggered;
    }

    @Override
    abstract public void trigger(Game game);
}
