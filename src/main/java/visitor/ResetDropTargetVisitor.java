package visitor;

import controller.Game;
import logic.table.Table;

public class ResetDropTargetVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        Table table = game.getCurrentTable();
        int current = table.getCurrentlyDroppedDropTargets();
        table.setCurrentlyDroppedDropTargets(current - 1);
    }
}
