package visitor;

import controller.Game;

public class AddScoreVisitor extends Visitor {
    int score;
    public AddScoreVisitor(int score) {
        this.score = score;
    }

    @Override
    public void visitGame(Game game) {
        game.addScore(this.score);
    }
}
