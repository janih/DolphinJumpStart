package step_7;

import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import step_7.TutorialAction;

import static step_6.TutorialConstants.CMD_LOG;

public class TutorialDirector extends DolphinServerAction {

    public void registerIn(ActionRegistry registry) {
        // register all your actions here.
        getServerDolphin().register(new TutorialAction());
    }
}
