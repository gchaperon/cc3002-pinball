package logic.gameelements.target;

import visitor.*;

public class DropTarget extends AbstractTarget {

    private Visitor extraBallBonusVisitor;

    /**
     * Since a drop target may trigger an ExtraBallBonus, an extraBallBonusVisitor is needed
     * for this class.
     * @param seed  The seed that will be given to the instance of {@link visitor.ExtraBallBonusVisitor}.
     */
    public DropTarget(long seed) {
        super();
        extraBallBonusVisitor = new ExtraBallBonusVisitor(0.3, seed);
    }

    /**
     * If the target is active, notifies its observers of the score obtained by hitting it.
     * If the target is active, sends a message to trigger an ExtraBallBonus.
     * If the target is active, sends a message to trigger an DropTargetBonus.
     * The bonus triggered with a given probability are handled by the visitor, which
     * decides if the bonus should finally be triggered or not.
     * The visitor also checks if all other drop targets have been hit in order to trigger
     * a drop target bonus.
     *
     * @return  the score obtained by hitting this hittable.
     */
    @Override
    public int hit() {
        int score = getScore();
        if (this.isActive()) {
            setChanged();
            notifyObservers(new AddScoreVisitor(score));
            this.isActive = false;
            setChanged();
            notifyObservers(extraBallBonusVisitor);
            setChanged();
            notifyObservers(new DropTargetBonusVisitor());
        }
        return score;
    }

    @Override
    public int getScore() {
        return (isActive()) ? 100 : 0;
    }

    /**
     * Resetting a drop target should be notified to its observer, because its the
     * observer that keeps track of the number of currently dropped drop targets.
     */
    @Override
    public void reset() {
        if (!this.isActive) {
            this.isActive = true;
            setChanged();
            notifyObservers(new ResetDropTargetVisitor());
        }
    }

    @Override
    public boolean isDropTarget() {
        return true;
    }
}
