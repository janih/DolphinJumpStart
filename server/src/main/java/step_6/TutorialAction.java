package step_6;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import java.util.List;

import static step_6.TutorialConstants.*;

public class TutorialAction extends DolphinServerAction {

    public void registerIn(ActionRegistry actionRegistry) {
        actionRegistry.register(CMD_LOG, new CommandHandler<Command>() {
            public void handleCommand(Command command, List<Command> response) {
                System.out.println(getServerDolphin().getAt(PM_PERSON).getAt(ATT_FIRSTNAME).getValue());
            }
        });
    }
}
