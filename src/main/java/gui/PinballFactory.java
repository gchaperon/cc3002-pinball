package gui;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.Random;

/**
 * Factory class used to create entities in PinballApp
 *
 * @author Gabriel Chaperon
 */
public class PinballFactory implements EntityFactory {
    Random rnd;

    public PinballFactory() {
        rnd = new Random();
    }

    /**
     * Creates a new ball entity at a specific position with a specific initial velocity.
     *
     * @param data  Data to use for the new entity, unused in this case.
     * @return  The new ball entity.
     */
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

    /**
     * DEPRECATED
     */
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

    /**
     * DEPRECATED
     */
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

    /**
     * Creates de left flipper
     * @return  The left flipper
     */
    public Entity leftFlipper() {
        int w = 340;
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.KINEMATIC);
        return Entities.builder()
                .at(-w/2, 500)
                .type(PinballTypes.FLIPPER)
                .viewFromNodeWithBBox(new Rectangle(w, 20))
                .rotate(25d)
                .with(physicsComponent)
                .build();

    }

    /**
     * Creates de right flipper
     * @return  The right flipper
     */
    public Entity rightFlipper() {
        int w = 340;
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.KINEMATIC);
        return Entities.builder()
                .at(400-w/2, 500)
                .type(PinballTypes.FLIPPER)
                .viewFromNodeWithBBox(new Rectangle(w, 20))
                .rotate(-25d)
                .with(physicsComponent)
                .build();
    }

    /**
     * Creates targets entities.
     * @param target    The target to which this entity is related.
     * @return  A new target entity.
     */
    public Entity newTargetEntity(Target target) {
        int radius = 30;
        return Entities.builder()
                .at(rnd.nextInt(400-2*radius), rnd.nextInt(300))
                .type(PinballTypes.TARGET)
                .viewFromNodeWithBBox(new Circle(radius, target.isSpotTarget() ? Color.LIGHTBLUE : Color.LIGHTGREEN))
                .with(new TargetComponent(target), new PhysicsComponent())
                .build();
    }

    /**
     *  Creates a bumper entity
     * @param bumper    The bumper to which this entity is realted.
     * @return  A new bumper entity
     */
    public Entity newBumperEntity(Bumper bumper) {
        int radius = 20;
        return Entities.builder()
                .at(rnd.nextInt(400-2*radius), rnd.nextInt(300))
                .type(PinballTypes.BUMPER)
                .viewFromNodeWithBBox(new Circle(radius, bumper.isPopBumper() ? Color.LIGHTYELLOW : Color.LIGHTPINK))
                .with(new BumperComponent(bumper), new PhysicsComponent())
                .build();
    }
}
