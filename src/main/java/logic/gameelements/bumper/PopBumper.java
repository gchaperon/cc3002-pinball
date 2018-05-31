package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper {
    public PopBumper(long seed) {
        super(3, seed);
    }

    @Override
    public int getScore() {
        return (this.isUpgraded()) ? 300 : 100;
    }
}
