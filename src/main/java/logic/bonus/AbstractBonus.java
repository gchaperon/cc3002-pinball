package logic.bonus;

import controller.Game;

public class AbstractBonus implements Bonus {
    int timesTriggered;

    AbstractBonus() {
        this.timesTriggered = 0;
    }

    @Override
    public int timesTriggered() {
        return this.timesTriggered;
    }

    @Override
    public void trigger(Game game) {

    }
}
