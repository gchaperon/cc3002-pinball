package visitor;

import controller.Game;

/**
 * Visitor used to add score to a game.
 * When a hittable object is hit, and therefore a score is to be added to the game,
 * this class is used to communicate the value to be added, in order to maintain
 * class privacy.
 *
 * @author Gabriel Chaperon
 */
public class AddScoreVisitor extends Visitor {
    private int score;
    public AddScoreVisitor(int score) {
        this.score = score;
    }

    @Override
    public void visitGame(Game game) {
        game.addScore(this.score);
    }
}
