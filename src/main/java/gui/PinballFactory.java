package gui;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.RotationComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.Body;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class PinballFactory implements EntityFactory {

    @Spawns("Ball")
    public Entity newBall(SpawnData data) {
        int x = 200;
        int y = 300;
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(0.6f).density(0f).friction(0f));
        physics.setOnPhysicsInitialized(()-> physics.setBodyLinearVelocity(new Vec2(10,10)));

        return Entities.builder()
                .at(x, y)
                .type(PinballTypes.BALL)
//                .viewFromNodeWithBBox(new Circle(7, Color.BLACK))
                .viewFromTextureWithBBox("wheel.png")
                .with(physics, new CollidableComponent(true))
                .build();
    }

    public Entity bottomLeft() {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0f));
        return Entities.builder()
                .at(0, 400)
                .viewFromNode(new Polygon(0, 0, 100, 50, 100, 75, 0, 75))
                .bbox(new HitBox("LEFT", new Point2D(0, 0),
                        BoundingShape.polygon(0, 0, 100, 50, 100, 75, 0, 75)))
                .with(new CollidableComponent(true), physics)
                .build();
    }

    public Entity bottomRight() {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0f));
        return Entities.builder()
                .at(400, 400)
                .viewFromNode(new Polygon(0, 0, -100, 50, -100, 75, 0, 75))
                .bbox(new HitBox("RIGHT", new Point2D(0, 0),
                        BoundingShape.polygon(0, 0, -100, 50, -100, 75, 0, 75)))
                .with(new CollidableComponent(true), physics)
                .build();
    }

    public Entity leftFlipper() {
        int w = 340;
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.KINEMATIC);
        return Entities.builder()
                .at(-w/2, 500)
                .viewFromNodeWithBBox(new Rectangle(w, 20))
                .rotate(25d)
                .with(physicsComponent)
                .build();

    }

    public Entity rightFlipper() {
        int w = 340;
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.KINEMATIC);
        return Entities.builder()
                .at(400-w/2, 500)
                .viewFromNodeWithBBox(new Rectangle(w, 20))
                .rotate(-25d)
                .with(physicsComponent)
                .build();
    }

}
