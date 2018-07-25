package gui;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PinballFactory implements EntityFactory {
    public static Entity newBall (double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(1f).friction(0f));
        physics.setOnPhysicsInitialized(()-> physics.setBodyLinearVelocity(new Vec2(1,-1)));

        return Entities.builder()
                .at(x, y)
                .type(PinballTypes.BALL)
                .viewFromNodeWithBBox(new Circle(10, Color.BLACK))
                .with(physics, new CollidableComponent(true))
                .build();
    }
}
