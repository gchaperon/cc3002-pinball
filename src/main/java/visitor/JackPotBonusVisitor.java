package visitor;

import controller.Game;

public class JackPotBonusVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        game.getJackPotBonus().trigger(game);
    }
}
