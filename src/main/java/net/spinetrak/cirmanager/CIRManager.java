package net.spinetrak.cirmanager;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.spinetrak.cirmanager.db.CIRequestDAO;
import net.spinetrak.cirmanager.db.CISystemDAO;
import net.spinetrak.cirmanager.db.UserDAO;
import net.spinetrak.cirmanager.resources.CIRequestResource;
import net.spinetrak.cirmanager.resources.CISystemResource;
import net.spinetrak.cirmanager.resources.UserResource;
import org.skife.jdbi.v2.DBI;

/**
 * Created by spinetrak on 9/28/13.
 */

public class CIRManager extends Application<CIRManagerConfiguration>
{
    private static final String PROD = "PROD";
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
        bootstrap_.addBundle(new DBIExceptionsBundle());
        bootstrap_.addBundle(new AssetsBundle("/app", "/app"));
    }

    @Override
    public String getName()
    {
        return "cirmanager";
    }

    @Override
    public void run(final CIRManagerConfiguration configuration_, final Environment environment_) throws
                                                                                                  ClassNotFoundException
    {
        final DBI jdbi = configuration_.getJDBI(environment_);

        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        environment_.jersey().register(new UserResource(userDAO));

        final CISystemDAO ciSystemDAO = jdbi.onDemand(CISystemDAO.class);
        environment_.jersey().register(new CISystemResource(ciSystemDAO));

        final CIRequestDAO ciRequestDAO = jdbi.onDemand(CIRequestDAO.class);
        environment_.jersey().register(new CIRequestResource(ciRequestDAO));
    }
}
