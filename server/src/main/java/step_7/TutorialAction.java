package step_7;

import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import java.util.List;

import static step_7.TutorialConstants.*;

public class TutorialAction extends DolphinServerAction {
    final ILogService service;

    public TutorialAction(ILogService service) {
        this.service = service;
    }

    public void registerIn(ActionRegistry actionRegistry) {
        actionRegistry.register(CMD_LOG, new CommandHandler<Command>() {
            public void handleCommand(Command command, List<Command> response) {
                service.log(getServerDolphin().getAt(PM_PERSON).getAt(ATT_FIRSTNAME).getValue());
            }
        });
    }
}
