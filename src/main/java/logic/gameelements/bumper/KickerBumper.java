package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper implements Bumper {

    public KickerBumper() {
        super(5);
    }

    @Override
    public int getScore() {
        return (this.isUpgraded()) ? 1000 : 500;
    }
}
