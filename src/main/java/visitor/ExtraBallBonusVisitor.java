package visitor;

import controller.Game;

public class ExtraBallBonusVisitor extends BonusVisitor {

    public ExtraBallBonusVisitor(double prob) {
        super(prob);
    }

    public ExtraBallBonusVisitor(double prob, long seed) {
        super(prob, seed);
    }

    @Override
    public void visitGame(Game game) {
        game.getExtraBallBonus().trigger(game);
    }
}
