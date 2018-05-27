package logic.gameelements.target;

import visitor.DropTargetBonusVisitor;
import visitor.ExtraBallBonusVisitor;

public class DropTarget extends AbstractTarget {

    DropTarget() {
        super(0.3);
    }

    @Override
    public int hit() {
        if (this.isActive()) {
            setChanged();
            notifyObservers(new ExtraBallBonusVisitor(this.probBonus));
            setChanged();
            notifyObservers(new DropTargetBonusVisitor());
            this.isActive = false;

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
