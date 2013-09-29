package net.spinetrak.cirmanager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.FixtureHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.spinetrak.cirmanager.core.CIRItem;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertTrue;


/**
 * Created by spinetrak on 9/29/13.
 */
public class CIRManagerTest
{
    @ClassRule
    public static final DropwizardAppRule<CIRManagerConfiguration> RULE =
            new DropwizardAppRule<CIRManagerConfiguration>(CIRManager.class, "cirmanager.yml");

    @Test

    public void testRun() throws Exception
    {
        final Client client = new Client();

        final ClientResponse response = client.resource(
                String.format("http://localhost:%d/cis", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class,
                                                                                          FixtureHelpers.fixture(
                                                                                                  "fixtures/ciritem.json"));
        assertTrue(response != null);
        assertTrue(response.getEntity(CIRItem.class).getName().equals("ciid:/foo/bar/baz"));
    }
}
