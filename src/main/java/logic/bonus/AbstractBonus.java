package logic.bonus;

import controller.Game;

/**
 * Abstract class that implements the {@link logic.bonus.Bonus} interface.
 * The specific actions done by the trigger method are explained in each of
 * the subclasses of this class.
 *
 * @author Gabriel Chaperon
 * @see ExtraBallBonus
 * @see JackPotBonus
 * @see DropTargetBonus
 */
public abstract class AbstractBonus implements Bonus {
    int timesTriggered;

    /**
     * Creates a new Bonus instance.
     * Any bonus should start with its timesTriggered attribute at zero.
     */
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
