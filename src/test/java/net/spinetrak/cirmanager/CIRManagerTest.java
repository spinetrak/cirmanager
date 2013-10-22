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
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
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
    public void testCreateCIRRequest()
    {
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                final WebResource.Builder cirsBuilder = new Client().resource(
                        String.format("http://localhost:%d/restapi/cirs", RULE.getLocalPort()))
                        .accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
                final CIRequest cir = makeCirRequest(i);
                final ClientResponse responseCir = cirsBuilder.post(ClientResponse.class, asJSON(cir));
                checkStatus(responseCir);
                final CIRequest newCir = responseCir.getEntity(CIRequest.class);
                assertEquals((cir.getCreatedBy().getUserId()), ((newCir.getCreatedBy().getUserId())));
            }
        }
    }

    @Test
    public void testCreateCISystem() throws Exception
    {
        for (int i = 0; i < 5; i++)
        {
            final WebResource.Builder ciidsBuilder = new Client().resource(
                    String.format("http://localhost:%d/restapi/ciids", RULE.getLocalPort()))
                    .accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
            final CISystem ciid = makeCiid();
            final ClientResponse responseCiid = ciidsBuilder.post(ClientResponse.class, asJSON(ciid));
            checkStatus(responseCiid);
            final CISystem newCiid = responseCiid.getEntity(CISystem.class);
            assertEquals(ciid.getName(), newCiid.getName());
        }
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

    @Test
    public void testUpdateCIRRequest()
    {
        final WebResource.Builder getCirsBuilder = new Client().resource(
                String.format("http://localhost:%d/restapi/cirs", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);

        final ClientResponse responseCir = getCirsBuilder.get(ClientResponse.class);
        checkStatus(responseCir);
        final List<CIRequest> cirs = responseCir.getEntity(new GenericType<List<CIRequest>>()
        {
        });

        assertTrue(!cirs.isEmpty());
        for (final CIRequest cir : cirs)
        {
            makeUpdatedCirRequest(cir);

            final WebResource.Builder putCirBuilder = new Client().resource(
                    String.format("http://localhost:%d/restapi/cirs/" + cir.getCirid(), RULE.getLocalPort()))
                    .accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE);
            final ClientResponse responseUpdatedCir = putCirBuilder.put(ClientResponse.class, cir);
            checkStatus(responseUpdatedCir);


            final CIRequest updatedCir = responseUpdatedCir.getEntity(CIRequest.class);

            assertEquals(cir.getCirid(), updatedCir.getCirid());
            assertEquals(cir.getCIRStatus(), updatedCir.getCIRStatus());
            assertEquals(cir.getRisk(), updatedCir.getRisk());
            assertEquals(cir.getCIRStatus(), updatedCir.getCIRStatus());
            assertEquals(cir.getCreatedOn(), updatedCir.getCreatedOn());
            assertEquals(cir.getCreatedBy().getUserId(), updatedCir.getCreatedBy().getUserId());
            assertTrue(updatedCir.getCreatedBy().getEmail().endsWith("@example.com"));
            assertEquals(cir.getSubmittedOn(), updatedCir.getSubmittedOn());
            //assertEquals(cir.getSubmittedBy().getUserId(),updatedCir.getSubmittedBy().getUserId());
            assertEquals(cir.getSummary(), updatedCir.getSummary());
            assertEquals(cir.getDescription(), updatedCir.getDescription());
            //assertEquals(cir.getStartDate(),updatedCir.getStartDate());
            //assertEquals(cir.getEndDate(),updatedCir.getEndDate());
        }
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

    private void checkStatus(final ClientResponse response_)
    {
        LOGGER.debug("Response: " + response_ + "(" + response_.getStatus() + ")");
        assertEquals(200, response_.getStatus());
    }

    private String getRandomCiidName()
    {
        return new StringBuilder("ciid:/").append(getRandomWord()).append(
                "/").append(getRandomWord()).append(
                "/").append(getRandomWord()).toString();
    }

    private String getRandomEmail()
    {
        return getRandomWord() + "@" + getRandomWord() + ".com";
    }

    private String getRandomSummary()
    {
        return "Summary: " + getRandomWord() + getRandomWord();
    }

    private String getRandomWord()
    {
        return RandomStringUtils.randomAlphabetic(5);
    }

    private CISystem makeCiid()
    {
        final CISystem ciid = new CISystem();
        ciid.setName(getRandomCiidName());
        return ciid;
    }

    private CIRequest makeCirRequest(final int ciid_)
    {
        final CIRequest cir = new CIRequest();
        final User user = new User();
        user.setUserId(1);
        cir.setCreatedBy(user);
        cir.setCiid(ciid_);
        return cir;
    }

    private void makeUpdatedCirRequest(final CIRequest cir_)
    {
        cir_.setSummary(getRandomSummary());
        cir_.setDescription(getRandomDescription());
        cir_.setRisk("LOW");
        cir_.setStartDate(getRandomStartDate());
        cir_.setEndDate(getRandomEndDate(cir_.getStartDate()));
    }

    private Date getRandomEndDate(final Date startDate_)
    {
        return DateUtils.addMinutes(startDate_, RandomUtils.nextInt(14 * 14 * 60));
    }

    private Date getRandomStartDate()
    {
        return DateUtils.addMinutes(new Date(), RandomUtils.nextInt(14 * 14 * 60));
    }

    private String getRandomDescription()
    {
        return RandomStringUtils.randomAscii(500);
    }
}
