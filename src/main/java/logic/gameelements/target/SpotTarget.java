package logic.gameelements.target;

import visitor.AddScoreVisitor;
import visitor.JackPotBonusVisitor;

public class SpotTarget extends AbstractTarget {
    /**
     * If the target is active, notifies its observers of the score obtained by hitting it.
     * Although this target does not grant score to the player, this feature is
     * added for completeness.
     * If the target is active, sends a message to trigger a JackPotBonus.
     *
     * @return  the score obtained by hitting this hittable.
     */
    @Override
    public int hit() {
        if (this.isActive()) {
            setChanged();
            notifyObservers(new AddScoreVisitor(this.getScore()));
            this.isActive = false;
            setChanged();
            notifyObservers(new JackPotBonusVisitor());
        }
        return this.getScore();
    }

    @Override
    public int getScore() {
        return 0;
    }
}
