package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.RotationComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

public class FlipperControl extends Component {
    private final int side;
    int maxDeg = 25;
    private PhysicsComponent physics = new PhysicsComponent();

    public FlipperControl(int newSide) {
        this.side = newSide;
    }
//    @Override
//    public void onAdded() {
//        rotation.rotateBy(side == 0 ? maxDeg : -maxDeg);
//    }



    public void hit() {

        physics.setAngularVelocity(-1);
//        double step = maxDeg/2;
//        boolean cond = side == 0 ? (rotation.getValue() >= -maxDeg+step) : (rotation.getValue() <= maxDeg-step);
//        if (cond)
//            rotation.rotateBy(side == 0 ? -step : step);
    }

    public void release() {
//        rotation.setValue(side == 0 ? maxDeg : -maxDeg);
    }

}
