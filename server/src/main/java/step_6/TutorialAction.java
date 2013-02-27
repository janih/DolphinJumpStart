package step_6;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.NamedCommand;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.comm.NamedCommandHandler;

import java.util.List;

import static step_6.TutorialConstants.*;

public class TutorialAction implements NamedCommandHandler {
    private final ServerDolphin serverDolphin;

    public TutorialAction(ServerDolphin serverDolphin) {
        this.serverDolphin = serverDolphin;
    }

    @Override
    public void handleCommand(NamedCommand command, List<Command> response) {
        System.out.println(serverDolphin.getAt(PM_PERSON).getAt(ATT_FIRSTNAME).getValue() +" has been saved.");

    }
}
