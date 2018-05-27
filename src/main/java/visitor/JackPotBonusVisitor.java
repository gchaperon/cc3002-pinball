package visitor;

import controller.Game;

public class JackPotBonusVisitor extends BonusVisitor {

    public JackPotBonusVisitor(double prob) {
        super(prob);
    }

    public JackPotBonusVisitor(double prob, long seed) {
        super(prob, seed);
    }

    @Override
    public void visitGame(Game game) {
        game.getJackPotBonus().trigger(game);
    }
}
