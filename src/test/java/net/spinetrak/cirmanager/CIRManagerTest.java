package net.spinetrak.cirmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.core.CISystem;
import net.spinetrak.cirmanager.core.User;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by spinetrak on 9/29/13.
 */
public class CIRManagerTest
{
    @ClassRule
    public static final DropwizardAppRule<CIRManagerConfiguration> RULE =
            new DropwizardAppRule<>(CIRManager.class, "cirmanager.yml");
    final static Logger LOGGER = Logger.getLogger(CIRManagerTest.class);
    private ObjectMapper _mapper;

    @Before
    public void setUp()
    {
        _mapper = new ObjectMapper();
    }

    @Test
    public void testCreateCISystem() throws Exception
    {
        for (int i = 0; i < 5; i++)
        {
            final WebResource.Builder _ciidsBuilder = new Client().resource(
                    String.format("http://localhost:%d/restapi/ciids", RULE.getLocalPort()))
                    .accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
            final CISystem ciid = makeCiid();
            final ClientResponse responseCiid = _ciidsBuilder.post(ClientResponse.class, asJSON(ciid));
            checkStatus(responseCiid);
            final CISystem newCiid = responseCiid.getEntity(CISystem.class);
            assertTrue(ciid.getName().equals(newCiid.getName()));
        }
    }

    @Test
    public void testCreateCIRRequest()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                final WebResource.Builder _cirsBuilder = new Client().resource(
                        String.format("http://localhost:%d/restapi/cirs", RULE.getLocalPort()))
                        .accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
                final CIRequest cir = makeCirRequest(i);
                final ClientResponse responseCir = _cirsBuilder.post(ClientResponse.class, asJSON(cir));
                checkStatus(responseCir);
                final CIRequest newCir = responseCir.getEntity(CIRequest.class);
                assertEquals((cir.getCreatedBy().getUserid()), ((newCir.getCreatedBy().getUserid())));
                //assertTrue(cir.getCreatedOn().equals(newCir.getCreatedOn()));
                //assertTrue(cir.getSummary().equals(newCir.getSummary()));
            }
        }
    }

    private void checkStatus(final ClientResponse response_)
    {
        LOGGER.debug("Response: " + response_ + "(" + response_.getStatus() + ")");
        assertEquals(200, response_.getStatus());
    }

    private CIRequest makeCirRequest(final int ciid_)
    {
        final String randomCreatedBy = getRandomEmail();
        final String randomSummary = getRandomSummary();
        final Date date = new Date();

        final CIRequest cir = new CIRequest();
        final User user = new User();
        user.setUserid(1);
        cir.setCreatedBy(user);
        cir.setSummary(randomSummary);
        cir.setCreatedOn(date);
        cir.setCiid(ciid_);
        cir.setCIRStatus("created");
        return cir;
    }

    private CISystem makeCiid()
    {
        final CISystem ciid = new CISystem();
        ciid.setName(getRandomCiidName());
        return ciid;
    }

    private String asJSON(final Object object_)
    {
        try
        {
            final String json = _mapper.writeValueAsString(object_);
            LOGGER.debug(json);
            return json;
        }
        catch (final JsonProcessingException ex_)
        {
            fail("Unable to convert " + object_ + " to JSON: " + ex_.getMessage());
            ex_.printStackTrace();
        }
        return null;
    }

    private String getRandomEmail()
    {
        return getRandomWord() + "@" + getRandomWord() + ".com";
    }

    private String getRandomSummary()
    {
        return "Summary: " + getRandomWord() + getRandomWord();
    }

    @Test
    public void testGetCIRequest() throws Exception
    {
        final WebResource.Builder _cirBuilder = new Client().resource(
                String.format("http://localhost:%d/restapi/cirs/ciids/1", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
        final ClientResponse response = _cirBuilder.get(
                ClientResponse.class);
        checkStatus(response);
        final List<CIRequest> cirs = response.getEntity(new GenericType<List<CIRequest>>()
        {
        });
        if (cirs.size() > 0)
        {
            LOGGER.debug("Got cirs: " + cirs);
            final int cirID = cirs.get(0).getCirid();
            assertTrue(cirID > 0);
        }
        else
        {
            LOGGER.debug("Didn't get any cirs");
        }
    }

    private String getRandomCiidName()
    {
        return new StringBuilder("ciid:/").append(getRandomWord()).append(
                "/").append(getRandomWord()).append(
                "/").append(getRandomWord()).toString();
    }

    private String getRandomWord()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }
}
