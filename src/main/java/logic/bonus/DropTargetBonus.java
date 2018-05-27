package logic.bonus;

import controller.Game;

public class DropTargetBonus extends AbstractBonus {
    @Override
    public void trigger(Game game) {
        this.timesTriggered++;
        game.addScore(1000000);
        game.getCurrentTable().upgradeAllBumpers();
    }
}
