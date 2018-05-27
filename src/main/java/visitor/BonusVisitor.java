package visitor;

import controller.Game;

public class BonusVisitor {
    double probBonus;
    long seed;

    BonusVisitor(double prob) {
        this(prob, System.currentTimeMillis());
    }

    BonusVisitor(double prob, long seed) {
        this.probBonus = prob;
        this.seed = seed;
    }

    public void visitGame(Game game) {

    }
}
