package logic.gameelements.bumper;

import visitor.AddScoreVisitor;
import visitor.ExtraBallBonusVisitor;
import visitor.Visitor;

import java.util.Observable;

/**
 * Abstract class that implements most of the {@link logic.gameelements.bumper.Bumper}
 * interface. The specific behaviour of the getScore method is implemented in
 * each concrete bumper.
 * This class extends the {@link Observable} class and is design to notify the
 * table it will be contained in when it's hit.
 *
 * @author  Gabriel Chaperon
 */
public abstract class AbstractBumper extends Observable implements Bumper {

    private final int hitsToUpgrade;
    private int currentHits;
    private boolean isUpgraded;
    private Visitor extraBallBonusVisitor;

    /**
     * Creates a new bumper.
     *
     * @param hitsToUpgrade the hits needed to upgrade the bumper.
     * @param seed  the seed used to generate random numbers in the instance of
     *              {@link visitor.ExtraBallBonusVisitor}.
     */
    AbstractBumper(int hitsToUpgrade, long seed) {
        this.hitsToUpgrade = hitsToUpgrade;
        this.currentHits = 0;
        this.isUpgraded = false;
        this.extraBallBonusVisitor = new ExtraBallBonusVisitor(0.1, seed);
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

    /**
     * Adds one to the number of times this bumper has been hit.
     * Notifies its observers when it's been hit, so the correct score, depending
     * on whether it's upgraded or not, can be added to the score of the player.
     * When the bumper is upgraded because of a hit, it notifies its
     * observers so the {@link logic.bonus.ExtraBallBonus} instance of the game
     * can be triggered.
     *
     * @return  the score the player obtained hitting the object.
     */
    @Override
    public int hit() {
        // send upgrade message
        if (!this.isUpgraded() && this.remainingHitsToUpgrade()==1) {
            this.upgrade();
            setChanged();
            notifyObservers(extraBallBonusVisitor);
        }
        this.currentHits++;
        setChanged();
        notifyObservers(new AddScoreVisitor(this.getScore()));
        return this.getScore();
    }

    @Override
    abstract public int getScore();
}
