package logic.gameelements.bumper;

import logic.gameelements.Hittable;

/**
 * Interface that represents operations related to a bumper behavior.
 *
 * @author Juan-Pablo Silva
 * @see KickerBumper
 * @see PopBumper
 */
public interface Bumper extends Hittable {
    /**
     * Gets the remaining hits the bumper has to receive to upgrade.
     *
     * @return the remaining hits to upgrade the bumper
     */
    int remainingHitsToUpgrade();

    /**
     * Gets whether the bumper is currently upgraded or not.
     *
     * @return true if the bumper is upgraded, false otherwise
     */
    boolean isUpgraded();

    /**
     * Upgrades a bumper making {@link #isUpgraded()} return true.
     */
    void upgrade();

    /**
     * Downgrades a bumper making {@link #isUpgraded()} return false.
     */
    void downgrade();

    /**
     * Method to check if bumper is kicker bumper
     * @return true if it's a kicker bumpers, false otherwise
     */
    boolean isKickerBumper();

    /**
     * Method to check if bumper is pop bumper
     * @return true if it's a pop bumpers, false otherwise
     */
    boolean isPopBumper();
}
