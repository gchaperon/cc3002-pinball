package visitor;

import controller.Game;
import logic.table.Table;

public class DropTargetBonusVisitor extends BonusVisitor {
    public DropTargetBonusVisitor() {
        super(1.0);
    }

    @Override
    public void visitGame(Game game) {
        Table currentTable = game.getCurrentTable();
        currentTable.setCurrentlyDroppedDropTargets(currentTable.getCurrentlyDroppedDropTargets() + 1);
        if (currentTable.getNumberOfDropTargets() == currentTable.getCurrentlyDroppedDropTargets())
            game.getDropTargetBonus().trigger(game);
    }
}
