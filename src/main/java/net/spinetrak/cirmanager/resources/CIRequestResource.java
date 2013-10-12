package net.spinetrak.cirmanager.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.db.CIRequestDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by spinetrak on 10/7/13.
 */

@Path("/cirs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CIRequestResource
{
    private final CIRequestDAO _ciRequestDAO;

    public CIRequestResource(final CIRequestDAO ciRequestDAO_)
    {
        _ciRequestDAO = ciRequestDAO_;
    }

    @Path("/ciids/{ciid}")
    @GET
    @UnitOfWork
    @CacheControl(maxAge = 10, maxAgeUnit = TimeUnit.MINUTES)
    public List<CIRequest> listCIRs(final @PathParam("ciid") int ciid_)
    {
        return _ciRequestDAO.findByCiid(ciid_);
    }
}
