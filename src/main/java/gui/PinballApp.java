package gui;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.input.KeyCode;

public class PinballApp extends GameApplication {
    private int width = 400;
    private int height = 600;
    PinballFactory factory = new PinballFactory();
    Entity leftFlipper;
    Entity rightFlipper;
    double flipperSpeed = 10;

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

        leftFlipper = factory.leftFlipper();
        rightFlipper = factory.rightFlipper();
        getGameWorld().addEntityFactory(factory);
        getGameWorld().addEntity(leftFlipper);
        getGameWorld().addEntity(rightFlipper);
    }

    private void initBackGround() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(PinballTypes.WALL);
        walls.addComponent(new CollidableComponent(true));
        getGameWorld().addEntity(walls);

        Entities.builder()
                .viewFromTexture("piñi.jpg")
                .buildAndAttach();

//         adds bottom part of table, shared with all tables
//        getGameWorld().addEntity(factory.bottomLeft());
//        getGameWorld().addEntity(factory.bottomRight());
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

        input.addAction(new UserAction("LeftFlipper movement") {
            @Override
            protected void onAction() {
                System.out.println(leftFlipper.angleProperty().getValue());
                if (leftFlipper.angleProperty().getValue() > -25+flipperSpeed)
                    leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(-flipperSpeed);
                else
                    leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(0d);
            }
        }, KeyCode.LEFT);

        input.addAction(new UserAction("RightFlipper movement") {
            @Override
            protected void onAction() {
                System.out.println(rightFlipper.angleProperty().getValue());
                if (rightFlipper.angleProperty().getValue() < 25-flipperSpeed)
                    rightFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(flipperSpeed);
                else
                    rightFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(0d);
            }
        }, KeyCode.RIGHT);
    }

    @Override
    protected void onUpdate(double tpf) {
        // control de leftFlipper
        if (!getInput().isHeld(KeyCode.LEFT) && leftFlipper.angleProperty().getValue() < 25-flipperSpeed)
            leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(flipperSpeed);
        if (leftFlipper.angleProperty().getValue() > 25-flipperSpeed)
            leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(0d);
        //control de rightFlipper
        if (!getInput().isHeld(KeyCode.RIGHT) && rightFlipper.angleProperty().getValue() > -25+flipperSpeed)
            rightFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(-flipperSpeed);
        if (rightFlipper.angleProperty().getValue() < -25+flipperSpeed)
            rightFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(0d);



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
