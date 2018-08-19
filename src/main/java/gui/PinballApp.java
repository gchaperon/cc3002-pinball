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
import facade.HomeworkTwoFacade;
import javafx.scene.input.KeyCode;
import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Pinball game, the logic for the game is in the logic package, in this package there is only the gui implementation.
 * This is the gui's main controllers
 *
 * @author Gabriel Chaperon
 */
public class PinballApp extends GameApplication {
    private int width = 400;
    private int height = 600;
    PinballFactory factory = new PinballFactory();
    Entity leftFlipper;
    Entity rightFlipper;
    double flipperSpeed = 15;
    List<Entity> bumpers, targets;
    List<List<Entity>> masterList;

    HomeworkTwoFacade gameFacade;

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

        bumpers = new ArrayList<>();
        targets = new ArrayList<>();

        masterList = new ArrayList<List<Entity>>() {{
            add(bumpers);
            add(targets);
        }};

        gameFacade = new HomeworkTwoFacade();
    }

    /**
     * Method to initialize background
     */
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
                if (!getGameWorld().getSingleton(PinballTypes.BALL).isPresent() && gameFacade.isPlayableTable()) {
                    getGameWorld().spawn("Ball");
                    getAudioPlayer().playSound("fium.wav");
                }
            }
        }, KeyCode.SPACE);

        input.addAction(new UserAction("LeftFlipper Movement") {
            @Override
            protected void onAction() {
                if (leftFlipper.angleProperty().getValue() > -25+flipperSpeed)
                    leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(-flipperSpeed);
                else
                    leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(0d);
            }
        }, KeyCode.LEFT);

        input.addAction(new UserAction("RightFlipper Movement") {
            @Override
            protected void onAction() {
                if (rightFlipper.angleProperty().getValue() < 25-flipperSpeed)
                    rightFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(flipperSpeed);
                else
                    rightFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(0d);
            }
        }, KeyCode.RIGHT);

        input.addAction(new UserAction("New Table") {
            @Override
            protected void onActionBegin() {
                if (!getGameWorld().getSingleton(PinballTypes.BALL).isPresent())
                    setNewTable();
            }
        }, KeyCode.N);
    }

    /**
     * Method to set a new table. Ir erases the last table, sets a new table in the game object , creates entities
     * according to the hittables of the new table created.
     */
    private void setNewTable() {

        for (List<Entity> list:
                masterList) {
            for (Entity entity :
                    list) {
                entity.removeFromWorld();
            }
            list.clear();
        }

        gameFacade.setGameTable(gameFacade.newFullPlayableTable("asd",
                6, 0.5, 2, 2));
        for (Bumper bumper :
                gameFacade.getBumpers()) {
            Entity bumperEntity = factory.newBumperEntity(bumper);
            targets.add(bumperEntity);
            getGameWorld().addEntity(bumperEntity);
        }

        for (Target target :
                gameFacade.getTargets()) {
            Entity targetEntity = factory.newTargetEntity(target);
            targets.add(targetEntity);
            getGameWorld().addEntity(targetEntity);
        }
    }

    @Override
    protected void onUpdate(double tpf) {
        // control de leftFlipper
        if (!getInput().isHeld(KeyCode.LEFT) && leftFlipper.angleProperty().getValue() < 25 - flipperSpeed)
            leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(flipperSpeed);
        if (leftFlipper.angleProperty().getValue() > 25 - flipperSpeed)
            leftFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(0d);
        //control de rightFlipper
        if (!getInput().isHeld(KeyCode.RIGHT) && rightFlipper.angleProperty().getValue() > -25 + flipperSpeed)
            rightFlipper.getComponent(PhysicsComponent.class).setAngularVelocity(-flipperSpeed);
        if (rightFlipper.angleProperty().getValue() < -25 + flipperSpeed)
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
