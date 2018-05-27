package logic.gameelements.target;

import visitor.JackPotBonusVisitor;

public class SpotTarget extends AbstractTarget {

    SpotTarget() {
        super(1.0);
    }

    @Override
    public int hit() {
        if (this.isActive()) {
            setChanged();
            notifyObservers(new JackPotBonusVisitor(this.probBonus));
            this.isActive = false;

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
