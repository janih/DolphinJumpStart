package step_5;

import com.canoo.dolphin.core.comm.Command;
import com.canoo.dolphin.core.comm.NamedCommand;
import com.canoo.dolphin.core.server.ServerDolphin;
import com.canoo.dolphin.core.server.comm.NamedCommandHandler;

import java.util.List;

import static step_5.TutorialConstants.*;

public class TutorialAction implements NamedCommandHandler {
    private final ServerDolphin config;

    public TutorialAction(ServerDolphin config) {
        this.config = config;
    }

    @Override
    public void handleCommand(NamedCommand command, List<Command> response) {
        System.out.println(config.getAt(PERSON_MODEL_ID).getAt(FIRSTNAME_ID).getValue());
    }
}
