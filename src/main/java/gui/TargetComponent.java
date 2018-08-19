package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.target.Target;

public class TargetComponent extends Component {
    Target target;
    public TargetComponent(Target target) {
        this.target = target;
    }
}
