package logic.gameelements.bumper;

public abstract class AbstractBumper implements Bumper {
    private final int hitsToUpgrade;
    private int currentHits;

    AbstractBumper(int hitsToUpgrade) {
        this.hitsToUpgrade = hitsToUpgrade;
        this.currentHits = 0;
    }

    @Override
    public int remainingHitsToUpgrade() {
        return this.hitsToUpgrade - this.currentHits;
    }

    @Override
    public boolean isUpgraded() {
        return this.currentHits >= this.hitsToUpgrade;
    }

    @Override
    public void downgrade() {
        this.currentHits = 0;
    }

    @Override
    public int hit() {
        this.currentHits++;
        return this.getScore();
    }

    @Override
    public void upgrade() {

    }
}
