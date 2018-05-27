package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper {
    PopBumper() {
        super(3);
    }

    @Override
    public int getScore() {
        return (this.isUpgraded()) ? 300 : 100;
    }
}
