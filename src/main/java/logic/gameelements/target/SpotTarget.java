package logic.gameelements.target;

import visitor.AddScoreVisitor;
import visitor.JackPotBonusVisitor;

public class SpotTarget extends AbstractTarget {

    public SpotTarget(long seed) {
        super(1.0, seed);
    }

    @Override
    public int hit() {
        if (this.isActive()) {
            this.isActive = false;
            setChanged();
            notifyObservers(new JackPotBonusVisitor());
            setChanged();
            notifyObservers(new AddScoreVisitor(this.getScore()));
            return this.getScore();
        }
        else {
            return 0;
        }
    }

    @Override
    public int getScore() {
        return 0;
    }
}
