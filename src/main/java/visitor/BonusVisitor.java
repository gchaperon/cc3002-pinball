package visitor;

public abstract class BonusVisitor extends Visitor {
    double probBonus;
    long seed;

    BonusVisitor() {
        this(1.0, System.currentTimeMillis());
    }

    BonusVisitor(double prob, long seed) {
        this.probBonus = prob;
        this.seed = seed;
    }
}
