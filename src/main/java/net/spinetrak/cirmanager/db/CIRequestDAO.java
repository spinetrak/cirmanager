package net.spinetrak.cirmanager.db;

import io.dropwizard.hibernate.AbstractDAO;
import net.spinetrak.cirmanager.core.CIRequest;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by spinetrak on 10/7/13.
 */
public class CIRequestDAO extends AbstractDAO<CIRequest>
{
    public CIRequestDAO(final SessionFactory sessionFactory_)
    {
        super(sessionFactory_);
    }

    public List<CIRequest> findByCiid(final int ciid_)
    {
        final Query query = namedQuery("net.spinetrak.cirmanager.core.CIRequest.findByCiid");
        query.setInteger("ciid", ciid_);
        return list(query);
    }


    public CIRequest create(final CIRequest ciRequest_)
    {
        final CIRequest req = persist(ciRequest_);
        return req;
    }
}
