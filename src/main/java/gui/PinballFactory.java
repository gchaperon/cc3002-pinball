package gui;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PinballFactory implements EntityFactory {

    @Spawns("Ball")
    public Entity newBall(SpawnData data) {
        int x = 200;
        int y = 300;
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(0.9f).density(1f).friction(0f));
        physics.setOnPhysicsInitialized(()-> physics.setBodyLinearVelocity(new Vec2(10,10)));

        return Entities.builder()
                .at(x, y)
                .type(PinballTypes.BALL)
                .viewFromNodeWithBBox(new Circle(7, Color.BLACK))
                .with(physics, new CollidableComponent(true))
                .build();
    }
}
