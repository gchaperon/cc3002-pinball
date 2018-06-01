package visitor;

import controller.Game;

public class ExtraBallBonusVisitor extends BonusVisitor {

    public ExtraBallBonusVisitor(double prob, long seed) {
        super(prob, seed);
    }

    @Override
    public void visitGame(Game game) {
        float next = rnd.nextFloat();
        if (next < this.probBonus) {
            game.getExtraBallBonus().trigger(game);
        }
    }
}
