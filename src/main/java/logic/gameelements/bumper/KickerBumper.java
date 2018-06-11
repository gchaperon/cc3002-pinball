package logic.gameelements.bumper;

public class KickerBumper extends AbstractBumper {
    public KickerBumper(long seed) {
        super(5, seed);
    }

    /**
     * Returns 1000 or 500 (the score gained by hitting this bumper) if this
     * bumper if upgraded or not, respectively.
     * @return  the score given by this bumper.
     */
    @Override
    public int getScore() {
        return (this.isUpgraded()) ? 1000 : 500;
    }
}
