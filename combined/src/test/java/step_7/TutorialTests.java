package step_7;

import static org.junit.Assert.assertEquals;
import static step_7.TutorialConstants.ATT_FIRSTNAME;
import static step_7.TutorialConstants.CMD_LOG;
import static step_7.TutorialConstants.PM_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.opendolphin.LogConfig;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.comm.InMemoryClientConnector;
import org.opendolphin.core.client.comm.UiThreadHandler;
import org.opendolphin.core.server.ServerDolphin;

public class TutorialTests {

	ClientDolphin clientDolphin = new ClientDolphin();
	{
		clientDolphin.setClientModelStore(new ClientModelStore(clientDolphin));
		InMemoryClientConnector connector = new InMemoryClientConnector(clientDolphin);
		clientDolphin.setClientConnector(connector);
	}
	ServerDolphin serverDolphin = new ServerDolphin();

	@Before
	public void setup() {
		clientDolphin.getClientConnector().setUiThreadHandler(new UiThreadHandler() {
			@Override
			public void executeInsideUiThread(Runnable runnable) {
				runnable.run();
			}
		});
		LogConfig.noLogs(); // Do not log any dolphin messages.
		clientDolphin.presentationModel(PM_PERSON, Arrays.asList(ATT_FIRSTNAME));
	}

	/**
	 * with 0.8 and above
	 */
	//    private void setupConfig() {
	//        config = new DefaultInMemoryConfig(false);
	//    }
	@Test
	public void testCallAction() throws Exception {
		setFirstName("Dolphin");
		final List<Boolean> serverSideCalled = new ArrayList<>();
		serverDolphin.register(new TutorialDirector(new ILogService() {
			@Override
			public void log(Object message) {
				serverSideCalled.add(Boolean.TRUE);
				assertEquals("Dolphin", message);
			}
		}));
		clientDolphin.send(CMD_LOG);
		assertEquals(1, serverSideCalled.size());
	}

	private void setFirstName(String firstName) {
		clientDolphin.getAt(PM_PERSON).getAt(ATT_FIRSTNAME).setValue(firstName);
	}
}
