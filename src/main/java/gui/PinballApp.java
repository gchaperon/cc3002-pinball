package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import static gui.PinballFactory.newBall;
import static gui.PinballTypes.BALL;
import static gui.PinballTypes.WALL;

public class PinballApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setHeight(600);
        settings.setWidth(400);
        settings.setTitle("Pinball");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        Entity screenBounds = Entities.makeScreenBounds(50);
        screenBounds.addComponent(new IrremovableComponent());
        screenBounds.addComponent(new CollidableComponent(true));
        screenBounds.setType(WALL);
        getGameWorld().addEntity(screenBounds);

        getGameWorld().addEntity(newBall(200, 300));
    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Release Ball  ") {
            @Override
            protected void onActionBegin() {
                getGameWorld().getEntitiesByType(BALL).get(0).getComponent(BallComponent.class).release();
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
