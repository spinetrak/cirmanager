package net.spinetrak.cirmanager.resources;

import io.dropwizard.hibernate.UnitOfWork;
import net.spinetrak.cirmanager.core.CIRItem;
import net.spinetrak.cirmanager.db.CIRItemDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by spinetrak on 9/28/13.
 */
@Path("/cis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CIRItemResource
{
    private final CIRItemDAO _cirItemDAO;

    public CIRItemResource(final CIRItemDAO cirItemDAO_)
    {
        _cirItemDAO = cirItemDAO_;
    }

    @GET
    @UnitOfWork
    public List<CIRItem> listCIRItems()
    {
        return _cirItemDAO.findAll();
    }

    @POST
    @UnitOfWork
    public CIRItem createCIRItem(final CIRItem cirItem_)
    {
        return _cirItemDAO.create(cirItem_);
    }
}
