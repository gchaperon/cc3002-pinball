package sandbox;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SandBox extends GameApplication {
    Entity flipper;
    PhysicsComponent physics;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setHeight(600);
        settings.setProfilingEnabled(true);
        settings.setApplicationMode(ApplicationMode.DEBUG);
    }

    @Override
    protected void initGame() {
        physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        flipper = Entities.builder()
                .at(200, 200)
                .type(SandBoxTypes.FLIPPER)
                .viewFromNodeWithBBox(new Rectangle(200, 20, Color.BLACK))
                .rotate(20)
                .with(physics)
                .build();

        getGameWorld().addEntity(flipper);
    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Rotate") {
            @Override
            protected void onAction() {
                physics.setAngularVelocity(1f);
            }
        }, KeyCode.LEFT);
    }
}
