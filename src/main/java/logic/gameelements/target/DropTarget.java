package logic.gameelements.target;

import visitor.*;

public class DropTarget extends AbstractTarget {

    private Visitor extraBallBonusVisitor;

    public DropTarget(long seed) {
        super();
        extraBallBonusVisitor = new ExtraBallBonusVisitor(0.3, seed);
    }

    @Override
    public int hit() {
        if (this.isActive()) {
            setChanged();
            notifyObservers(new AddScoreVisitor(this.getScore()));
            this.isActive = false;
            setChanged();
            notifyObservers(extraBallBonusVisitor);
            setChanged();
            notifyObservers(new DropTargetBonusVisitor());
        }
        return this.getScore();
    }

    @Override
    public int getScore() {
        return (isActive()) ? 100 : 0;
    }

    @Override
    public void reset() {
        super.reset();
        setChanged();
        notifyObservers(new ResetDropTargetVisitor());
    }
}
