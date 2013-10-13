package net.spinetrak.cirmanager.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.db.CIRequestDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by spinetrak on 10/7/13.
 */

@Path("/cirs")
@JsonAutoDetect
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

    @POST
    @UnitOfWork
    public CIRequest createCIRItem(final CIRequest ciRequest_)
    {
        return _ciRequestDAO.create(ciRequest_);
    }
}
