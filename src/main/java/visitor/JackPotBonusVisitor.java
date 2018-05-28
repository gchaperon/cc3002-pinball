package visitor;

import controller.Game;

public class JackPotBonusVisitor extends BonusVisitor {

    public JackPotBonusVisitor() {
        super(1.0);
    }

    @Override
    public void visitGame(Game game) {
        game.getJackPotBonus().trigger(game);
    }
}
