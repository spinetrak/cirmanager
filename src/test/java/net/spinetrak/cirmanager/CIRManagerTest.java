package net.spinetrak.cirmanager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.core.CISystem;
import org.apache.commons.lang.RandomStringUtils;
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
    public void testCreateCIRItem() throws Exception
    {
        final Client client = new Client();
        final WebResource.Builder builder = client.resource(
                String.format("http://localhost:%d/ciids", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON);

        for (int i = 0; i < 10; i++)
        {
            final String randomCiidName = getRandomCiidName();
            final String json = toJson(randomCiidName);
            final ClientResponse response = builder.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);
            assertTrue(response.getStatus() == 200);
            final String newName = response.getEntity(CISystem.class).getName();
            assertTrue(newName.equals(randomCiidName));
        }
    }

    @Test
    public void testGetCIRequest() throws Exception
    {
        final Client client = new Client();
        final WebResource.Builder builder = client.resource(
                String.format("http://localhost:%d/cirs/ciids/210", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON);

        final ClientResponse response = builder.type(MediaType.APPLICATION_JSON).get(
                ClientResponse.class);
        final MediaType type = response.getType();
        assertTrue(response.getStatus() == 200);
        final CIRequest cir = response.getEntity(CIRequest.class);
        final int cirID = cir.getCirid();
        assertTrue(cirID > 0);

    }

    private String getRandomCiidName()
    {
        return new StringBuffer("ciid:/").append(getRandomWord()).append(
                "/").append(getRandomWord()).append(
                "/").append(getRandomWord()).toString();
    }

    private String toJson(final String name_)
    {
        final StringBuffer ciid = new StringBuffer("{").append("\"name\": \"").append(name_).append("\"}");
        return ciid.toString();
    }

    private String getRandomWord()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }
}
