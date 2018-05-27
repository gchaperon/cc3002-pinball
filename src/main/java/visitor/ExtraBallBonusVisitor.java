package visitor;

import controller.Game;

public class ExtraBallBonusVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        game.getExtraBallBonus().trigger(game);
    }
}
