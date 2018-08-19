package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.target.Target;

/**
 * TODO
 * Class that is supposed to control target, manage when its upgraded, the color change, etc.
 */
public class TargetComponent extends Component {
    Target target;
    public TargetComponent(Target target) {
        this.target = target;
    }
}
