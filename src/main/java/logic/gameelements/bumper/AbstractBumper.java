package logic.gameelements.bumper;

import visitor.ExtraBallBonusVisitor;

import java.util.Observable;

public abstract class AbstractBumper extends Observable implements Bumper {

    private final int hitsToUpgrade;
    private int currentHits;
    private boolean isUpgraded;
    private double probBonus;


    AbstractBumper(int hitsToUpgrade) {
        this.hitsToUpgrade = hitsToUpgrade;
        this.currentHits = 0;
        this.isUpgraded = false;
        this.probBonus = 0.1;
    }

    @Override
    public int remainingHitsToUpgrade() {
        return (this.isUpgraded()) ? 0 : (this.hitsToUpgrade - this.currentHits);
    }

    @Override
    public boolean isUpgraded() {
        return isUpgraded;
    }

    @Override
    public void upgrade() {
        isUpgraded = true;
    }

    @Override
    public void downgrade() {
        currentHits = 0;
        isUpgraded = false;
    }

    @Override
    public int hit() {
        // send upgrade message
        if (!this.isUpgraded() && this.remainingHitsToUpgrade()==1) {
            setChanged();
            notifyObservers(new ExtraBallBonusVisitor(this.probBonus));

            this.currentHits++;
            this.upgrade();
        }
        return this.getScore();
    }

    @Override
    abstract public int getScore();
}
