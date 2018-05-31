package visitor;

import controller.Game;

public class JackPotBonusVisitor extends BonusVisitor {
    @Override
    public void visitGame(Game game) {
        game.getJackPotBonus().trigger(game);
    }
}
