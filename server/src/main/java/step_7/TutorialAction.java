package step_7;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.NamedCommand;
import org.opendolphin.core.server.ServerDolphin;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;
import org.opendolphin.core.server.comm.NamedCommandHandler;
import step_5.TutorialConstants;

import java.util.List;

import static step_5.TutorialConstants.CMD_LOG;
import static step_6.TutorialConstants.ATT_FIRSTNAME;
import static step_6.TutorialConstants.PM_PERSON;

public class TutorialAction extends DolphinServerAction {

    public void registerIn(ActionRegistry actionRegistry) {
        actionRegistry.register(CMD_LOG, new CommandHandler<Command>() {
            public void handleCommand(Command command, List<Command> response) {
                System.out.println(getServerDolphin().getAt(TutorialConstants.PM_PERSON).getAt(TutorialConstants.ATT_FIRSTNAME).getValue());
            }
        });
    }
}
