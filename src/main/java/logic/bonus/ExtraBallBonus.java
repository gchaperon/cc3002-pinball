package logic.bonus;

import controller.Game;

public class ExtraBallBonus extends AbstractBonus {
    /**
     * When this bonus is triggered, an extra ball is added to the game.
     *
     * @param game  the game controller object.
     */
    @Override
    public void trigger(Game game) {
        this.timesTriggered++;
        game.extraBall();
    }
}
