package gui;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

public class BallComponent extends Component {
    PhysicsComponent physics;

    @Override
    public void onAdded() {
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().friction(0f).density(0f).restitution(0f));
    }

    public void release() {
        physics.setBodyLinearVelocity(new Vec2(2, 2));
    }

}
