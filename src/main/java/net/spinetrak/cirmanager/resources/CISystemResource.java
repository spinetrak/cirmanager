package net.spinetrak.cirmanager.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import net.spinetrak.cirmanager.core.CISystem;
import net.spinetrak.cirmanager.db.CISystemDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by spinetrak on 9/28/13.
 */
@Path("/ciids")
@JsonAutoDetect
public class CISystemResource
{
    private final CISystemDAO _ciSystemDAO;

    public CISystemResource(final CISystemDAO ciSystemDAO_)
    {
        _ciSystemDAO = ciSystemDAO_;
    }

    @GET
    @UnitOfWork
    @CacheControl(maxAge = 60, maxAgeUnit = TimeUnit.MINUTES)
    public List<CISystem> listCIRItems()
    {
        return _ciSystemDAO.findAll();
    }

    @POST
    @UnitOfWork
    public CISystem createCIRItem(final CISystem ciSystem_)
    {
        return _ciSystemDAO.create(ciSystem_);
    }
}
