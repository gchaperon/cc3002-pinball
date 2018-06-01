package visitor;

import java.util.Random;

public abstract class BonusVisitor extends Visitor {
    final Random rnd;
    double probBonus;

    BonusVisitor() {
        this(1.0, System.currentTimeMillis());
    }

    BonusVisitor(double prob, long seed) {
        this.probBonus = prob;
        this.rnd = new Random(seed);
    }
}
