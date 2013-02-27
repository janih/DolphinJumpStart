package step_6;

import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;

import static step_6.TutorialConstants.CMD_LOG;

public class TutorialDirector extends DolphinServerAction {

    @Override
    public void registerIn(ActionRegistry registry) {
        // register all your actions here.
        registry.register(CMD_LOG, new TutorialAction(getServerDolphin()));
    }
}
