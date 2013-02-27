package step_5;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.NamedCommand;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.comm.NamedCommandHandler;

import java.util.List;

import static step_5.TutorialConstants.*;

public class TutorialAction implements NamedCommandHandler {
    private final ServerDolphin config;

    public TutorialAction(ServerDolphin config) {
        this.config = config;
    }

    @Override
    public void handleCommand(NamedCommand command, List<Command> response) {
        System.out.println(config.getAt(PM_PERSON).getAt(ATT_FIRSTNAME).getValue());
    }
}
