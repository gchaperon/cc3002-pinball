package gui;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class BallComponent extends Component {
    PhysicsComponent physics;
    public void release() {
        physics.setBodyLinearVelocity(new Vec2(1, -1));
    }
}
