package step_6;

import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;

public class TutorialDirector extends DolphinServerAction {

    public void registerIn(ActionRegistry registry) {
        // register all your actions here.
        getServerDolphin().register(new TutorialAction());
    }
}
