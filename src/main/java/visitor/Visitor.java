package visitor;

import controller.Game;
import logic.table.ConcreteTable;
import logic.table.Table;

/**
 * Visitor class that sort of works as a template to implement specific visitors
 * to perform actions in a game or a table.
 * Designed following the visitor design pattern. If you want to learn more about
 * this design pattern you can visit:
 * @see <a href="https://en.wikipedia.org/wiki/Visitor_pattern"> Visitor Pattern (Wikipedia)</a>
 *
 * @author Gabriel Chaperon
 * @see AddScoreVisitor
 * @see ResetDropTargetVisitor
 * @see BonusVisitor
 */
public class Visitor {
    /**
     * Defines the actions the visitor will do to the game it visits.
     *
     * @param game  The game where the visitor will perform its specific actions.
     */
    public void visitGame(Game game) {

    }

    /**
     * Defines the actions the visitor will do to the table it visits.
     *
     * @param table The game where the visitor will perform its specific actions.
     */
    public void visitTable(ConcreteTable table) {

    }
}
