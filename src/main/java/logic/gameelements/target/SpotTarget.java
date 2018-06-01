package logic.gameelements.target;

import visitor.AddScoreVisitor;
import visitor.JackPotBonusVisitor;

public class SpotTarget extends AbstractTarget {
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
