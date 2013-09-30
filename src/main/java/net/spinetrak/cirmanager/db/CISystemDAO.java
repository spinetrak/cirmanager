package net.spinetrak.cirmanager.db;

import io.dropwizard.hibernate.AbstractDAO;
import net.spinetrak.cirmanager.core.CISystem;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by spinetrak on 9/28/13.
 */
public class CISystemDAO extends AbstractDAO<CISystem>
{
    public CISystemDAO(final SessionFactory sessionFactory_)
    {
        super(sessionFactory_);
    }

    public List<CISystem> findAll()
    {
        return list(namedQuery("net.spinetrak.cirmanager.core.CISystem.findAll"));
    }

    public CISystem create(final CISystem ciSystem_)
    {
        return persist(ciSystem_);
    }
}
