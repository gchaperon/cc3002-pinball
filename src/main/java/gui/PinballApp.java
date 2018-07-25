package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.settings.GameSettings;

import static gui.PinballFactory.newBall;

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
        getGameWorld().addEntity(screenBounds);

        getGameWorld().addEntity(newBall(200, 300));
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
