package logic.bonus;

import controller.Game;

public class DropTargetBonus extends AbstractBonus {
    /**
     * When this bonus is triggered, a million points are added to the game and
     * all bumpers are upgraded.
     *
     * @param game  the game controller object.
     */
    @Override
    public void trigger(Game game) {
        this.timesTriggered++;
        game.addScore(1000000);
        game.getCurrentTable().upgradeAllBumpers();
    }
}
