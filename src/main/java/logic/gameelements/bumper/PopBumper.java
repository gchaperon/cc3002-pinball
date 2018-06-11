package logic.gameelements.bumper;

public class PopBumper extends AbstractBumper {
    public PopBumper(long seed) {
        super(3, seed);
    }

    /**
     * Returns 300 or 100 (the score gained by hitting this bumper) if this
     * bumper if upgraded or not, respectively.
     * @return  the score given by this bumper.
     */
    @Override
    public int getScore() {
        return (this.isUpgraded()) ? 300 : 100;
    }
}
