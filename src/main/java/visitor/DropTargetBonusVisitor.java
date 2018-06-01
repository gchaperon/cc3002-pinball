package visitor;

import controller.Game;
import logic.table.ConcreteTable;
import logic.table.Table;

public class DropTargetBonusVisitor extends BonusVisitor {

    @Override
    public void visitGame(Game game) {
        Table currentTable = game.getCurrentTable();
        if (currentTable.getNumberOfDropTargets() == currentTable.getCurrentlyDroppedDropTargets())
            game.getDropTargetBonus().trigger(game);
    }

    @Override
    public void visitTable(ConcreteTable table) {
        int current = table.getCurrentlyDroppedDropTargets();
        table.setCurrentlyDroppedDropTargets(current + 1);
    }
}
