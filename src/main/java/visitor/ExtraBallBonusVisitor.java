package visitor;

import controller.Game;

import java.util.Random;

public class ExtraBallBonusVisitor extends BonusVisitor {

    public ExtraBallBonusVisitor(double prob) {
        super(prob);
    }

    public ExtraBallBonusVisitor(double prob, long seed) {
        super(prob, seed);
    }

    @Override
    public void visitGame(Game game) {
        Random rnd = new Random(this.seed);
        if (rnd.nextFloat() < this.probBonus) {
            game.getExtraBallBonus().trigger(game);
        }
    }
}
