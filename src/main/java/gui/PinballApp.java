package gui;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import static gui.PinballFactory.newWalls;

public class PinballApp extends GameApplication {
    private int width = 400;
    private int height = 600;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setHeight(height);
        settings.setWidth(width);
        settings.setTitle("Pinball");
        settings.setVersion("0.1");

        settings.setProfilingEnabled(true);
        settings.setApplicationMode(ApplicationMode.DEBUG);
    }

    @Override
    protected void initGame() {
        initBackGround();
        getGameWorld().addEntity(newWalls());

        getGameWorld().addEntityFactory(new PinballFactory());
    }

    private void initBackGround() {

    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Release Ball") {
            @Override
            protected void onActionBegin() {
                if (!getGameWorld().getSingleton(PinballTypes.BALL).isPresent()) {
                    getGameWorld().spawn("Ball");
                    getAudioPlayer().playSound("fium.wav");
                }
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 20);

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PinballTypes.BALL, PinballTypes.WALL) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox ballHitBox, HitBox wallHitBox) {
                if (wallHitBox.getName().equals("BOT")) {
                    ball.removeFromWorld();
                    getAudioPlayer().playSound("haha.wav");
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
