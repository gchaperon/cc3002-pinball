package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {
    public KickerBumper(long seed) {
        super(5, seed);
    }

    @Override
    public int getScore() {
        return (this.isUpgraded()) ? 1000 : 500;
    }
}
