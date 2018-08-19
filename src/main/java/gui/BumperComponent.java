package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.bumper.Bumper;

/**
 * TODO
 * Class that is supposed to control bumpers, manage when its upgraded, the color change, etc.
 */
public class BumperComponent extends Component {
    Bumper bumper;
    public BumperComponent(Bumper bumper) {
        this.bumper = bumper;
    }
}
