package net.spinetrak.cirmanager.resources;

import io.dropwizard.hibernate.UnitOfWork;
import net.spinetrak.cirmanager.core.CIRItem;
import net.spinetrak.cirmanager.db.CIRItemDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by spinetrak on 9/28/13.
 */
@Path("/cis")
@Produces(MediaType.APPLICATION_JSON)
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
}
