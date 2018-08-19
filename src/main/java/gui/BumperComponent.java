package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.gameelements.bumper.Bumper;

public class BumperComponent extends Component {
    Bumper bumper;
    public BumperComponent(Bumper bumper) {
        this.bumper = bumper;
    }
}
