package logic.bonus;

import controller.Game;

public class JackPotBonus extends AbstractBonus {
    @Override
    public void trigger(Game game) {
        this.timesTriggered++;
        game.addScore(100000);
    }
}
