package net.spinetrak.cirmanager;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.core.CISystem;
import net.spinetrak.cirmanager.db.CIRequestDAO;
import net.spinetrak.cirmanager.db.CISystemDAO;
import net.spinetrak.cirmanager.resources.CIRequestResource;
import net.spinetrak.cirmanager.resources.CISystemResource;

/**
 * Created by spinetrak on 9/28/13.
 */

public class CIRManager extends Application<CIRManagerConfiguration>
{
    private static final String PROD = "PROD";

    private final HibernateBundle<CIRManagerConfiguration> _hbCISystem =
            new HibernateBundle<CIRManagerConfiguration>(CISystem.class)
            {
                @Override
                public DataSourceFactory getDataSourceFactory(final CIRManagerConfiguration configuration_)
                {
                    return configuration_.getDataSourceFactory();
                }
            };
    private final HibernateBundle<CIRManagerConfiguration> _hbCIRequest =
            new HibernateBundle<CIRManagerConfiguration>(CIRequest.class)
            {
                @Override
                public DataSourceFactory getDataSourceFactory(final CIRManagerConfiguration configuration_)
                {
                    return configuration_.getDataSourceFactory();
                }
            };
    private final MigrationsBundle<CIRManagerConfiguration> _migrationsBundle =
            new MigrationsBundle<CIRManagerConfiguration>()
            {
                @Override
                public DataSourceFactory getDataSourceFactory(final CIRManagerConfiguration configuration_)
                {
                    return configuration_.getDataSourceFactory();
                }
            };

    public static void main(String[] args) throws Exception
    {
        new CIRManager().run(args);
    }

    @Override
    public void initialize(final Bootstrap<CIRManagerConfiguration> bootstrap_)
    {
        bootstrap_.addBundle(_migrationsBundle);
        bootstrap_.addBundle(_hbCISystem);
        bootstrap_.addBundle(_hbCIRequest);
        bootstrap_.addBundle(new AssetsBundle("/app", "/app"));
    }

    @Override
    public String getName()
    {
        return "cirmanager";
    }

    @Override
    public void run(final CIRManagerConfiguration configuration_, final Environment environment_)
    {
        final CISystemDAO ciSystemDAO = new CISystemDAO(_hbCISystem.getSessionFactory());
        environment_.jersey().register(new CISystemResource(ciSystemDAO));
        final CIRequestDAO ciRequestDAO = new CIRequestDAO(_hbCIRequest.getSessionFactory());
        environment_.jersey().register(new CIRequestResource(ciRequestDAO));
    }
}
