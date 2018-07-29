package gui;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Polygon;

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

        getGameWorld().addEntityFactory(new PinballFactory());

    }

    private void initBackGround() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(PinballTypes.WALL);
        walls.addComponent(new CollidableComponent(true));
        getGameWorld().addEntity(walls);

        // adds bottom part of table, shared with all tables
        Entities.builder()
                .at(0, 400)
                .viewFromNode(new Polygon(0, 0, 150, 50, 150, 100, 0, 100))
                .bbox(new HitBox("LEFT", new Point2D(0, 0),
                        BoundingShape.polygon(0, 0, 150, 50, 150, 100, 0, 100)))
                .with(new CollidableComponent(true), new PhysicsComponent())
                .buildAndAttach(getGameWorld());
        Entities.builder()
                .at(400, 400)
                .viewFromNode(new Polygon(0, 0, -150, 50, -150, 100, 0, 100))
                .bbox(new HitBox("RIGHT", new Point2D(0, 0),
                        BoundingShape.polygon(250, 450, 400, 400, 400, 500, 250, 500)))
                .with(new CollidableComponent(true), new PhysicsComponent())
                .buildAndAttach(getGameWorld());
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
        getPhysicsWorld().setGravity(0, 1000);

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
