package visitor;

import controller.Game;

public class DropTargetBonusVisitor extends BonusVisitor {
    public DropTargetBonusVisitor() {
        super(1.0);
    }

    @Override
    public void visitGame(Game game) {

        game.getDropTargetBonus().trigger(game);
    }
}
