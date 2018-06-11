package visitor;

import java.util.Random;

/**
 * Abstract class to generalize some behaviour of visitors design to trigger actions
 * related to bonuses.
 *
 * @author Gabriel Chaperon
 */
abstract class BonusVisitor extends Visitor {
    final Random rnd;
    double probBonus;

    /**
     * Creates a new BonusVisitor object.
     *
     * @param prob  The probability of a bonus to be triggered.
     * @param seed  The seed used to generate random numbers to simulate
     *              a probability.
     */
    BonusVisitor(double prob, long seed) {
        this.probBonus = prob;
        this.rnd = new Random(seed);
    }

    /**
     * There are some bonuses that may not be related to a probability of being triggered.
     * In that case this constructor is used.
     */
    BonusVisitor() {
        this(1.0, System.currentTimeMillis());
    }
}
