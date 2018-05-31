package logic.gameelements.target;

import visitor.AddScoreVisitor;
import visitor.DropTargetBonusVisitor;
import visitor.ExtraBallBonusVisitor;

public class DropTarget extends AbstractTarget {

    public DropTarget(long seed) {
        super(0.3, seed);
    }

    @Override
    public int hit() {
        if (this.isActive()) {
            this.isActive = false;
            setChanged();
            notifyObservers(new ExtraBallBonusVisitor(this.probBonus, this.seed));
            setChanged();
            notifyObservers(new DropTargetBonusVisitor());
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
        return 100;
    }
}
