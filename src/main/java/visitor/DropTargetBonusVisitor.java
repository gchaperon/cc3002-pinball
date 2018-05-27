package visitor;

import controller.Game;

public class DropTargetBonusVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        game.getDropTargetBonus().trigger(game);
    }
}
