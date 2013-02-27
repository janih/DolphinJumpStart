package step_7;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.NamedCommand;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.comm.NamedCommandHandler;

import java.util.List;

import static step_6.TutorialConstants.ATT_FIRSTNAME;
import static step_6.TutorialConstants.PM_PERSON;

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
