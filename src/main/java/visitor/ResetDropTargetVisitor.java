package visitor;

import logic.table.ConcreteTable;

/**
 * When a drop target is reset it should be notified to the object that keeps
 * track of the amount of dropped drop target.
 * This class is used to perform this notification.
 *
 * @author Gabriel Chaperon
 */
public class ResetDropTargetVisitor extends Visitor {
    @Override
    public void visitTable(ConcreteTable table) {
        int current = table.getCurrentlyDroppedDropTargets();
        table.setCurrentlyDroppedDropTargets(current - 1);
    }
}
