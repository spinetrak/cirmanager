package net.spinetrak.cirmanager.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.dropwizard.jersey.caching.CacheControl;
import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.db.ICIRequestDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by spinetrak on 10/7/13.
 */

@Path("/cirs")
@JsonAutoDetect
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CIRequestResource
{
    private final ICIRequestDAO _ciRequestDAO;

    public CIRequestResource(final ICIRequestDAO ciRequestDAO_)
    {
        _ciRequestDAO = ciRequestDAO_;
    }

    @POST
    public CIRequest createCIRItem(final CIRequest cir_)
    {
        final int cirid = _ciRequestDAO.insert(cir_.getCiid(), cir_.getCreatedBy().getUserId(), new Date(),
                                               "CREATED");
        return _ciRequestDAO.findByCirid(cirid);
    }

    @Path("/{cirid}")
    @GET
    @CacheControl(maxAge = 10, maxAgeUnit = TimeUnit.MINUTES)
    public CIRequest getCIR(final @PathParam("cirid") int cirid_)
    {
        return _ciRequestDAO.findByCirid(cirid_);
    }

    @Path("/ciids/{ciid}")
    @GET
    @CacheControl(maxAge = 10, maxAgeUnit = TimeUnit.MINUTES)
    public List<CIRequest> listCIRs(final @PathParam("ciid") int ciid_)
    {
        return _ciRequestDAO.findByCiid(ciid_);
    }

    @GET
    @CacheControl(maxAge = 10, maxAgeUnit = TimeUnit.MINUTES)
    public List<CIRequest> listCIRs()
    {
        return _ciRequestDAO.findAll();
    }

    @Path("/{cirid}")
    @PUT
    public CIRequest updateCIRItem(final @PathParam("cirid") int cirid_, final CIRequest cir_)
    {
        _ciRequestDAO.update(cir_);
        return _ciRequestDAO.findByCirid(cirid_);
    }
}
