package logic.bonus;

import controller.Game;

public class JackPotBonus extends AbstractBonus {
    /**
     * When this bonus is triggered, a hundred thousand points are added to the game.
     *
     * @param game  the game controller object.
     */
    @Override
    public void trigger(Game game) {
        this.timesTriggered++;
        game.addScore(100000);
    }
}
