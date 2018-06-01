package visitor;

import logic.table.ConcreteTable;

public class ResetDropTargetVisitor extends Visitor {
    @Override
    public void visitTable(ConcreteTable table) {
        int current = table.getCurrentlyDroppedDropTargets();
        table.setCurrentlyDroppedDropTargets(current - 1);
    }
}
