package net.spinetrak.cirmanager.db;

import io.dropwizard.hibernate.AbstractDAO;
import net.spinetrak.cirmanager.core.CIRItem;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by spinetrak on 9/28/13.
 */
public class CIRItemDAO extends AbstractDAO<CIRItem>
{
    public CIRItemDAO(final SessionFactory sessionFactory_)
    {
        super(sessionFactory_);
    }

    public List<CIRItem> findAll()
    {
        return list(namedQuery("net.spinetrak.cirmanager.core.CIRItem.findAll"));
    }

    public CIRItem create(final CIRItem cirItem_)
    {
        return persist(cirItem_);
    }
}
