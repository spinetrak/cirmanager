package net.spinetrak.cirmanager.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.dropwizard.jersey.caching.CacheControl;
import net.spinetrak.cirmanager.core.CISystem;
import net.spinetrak.cirmanager.db.ICISystemDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by spinetrak on 9/28/13.
 */
@Path("/ciids")
@JsonAutoDetect
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CISystemResource
{
    private final ICISystemDAO _ciSystemDAO;

    public CISystemResource(final ICISystemDAO ciSystemDAO_)
    {
        _ciSystemDAO = ciSystemDAO_;
    }

    @GET
    @CacheControl(maxAge = 60, maxAgeUnit = TimeUnit.MINUTES)
    public List<CISystem> listCISystems()
    {
        return _ciSystemDAO.findAll();
    }

    @POST
    public CISystem createCISystem(final CISystem ciSystem_)
    {
        final int ciid = _ciSystemDAO.insert(ciSystem_.getName());
        return _ciSystemDAO.findByCiid(ciid);
    }
}
