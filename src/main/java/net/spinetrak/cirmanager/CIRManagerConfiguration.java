package net.spinetrak.cirmanager;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import net.spinetrak.cirmanager.db.jdbi.DateAsTimestampArgument;
import org.skife.jdbi.v2.DBI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by spinetrak on 9/28/13.
 */
public class CIRManagerConfiguration extends Configuration
{
    @Valid
    @NotNull
    private DataSourceFactory _dataSourceFactory = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory()
    {
        return _dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(final DataSourceFactory dataSourceFactory_)
    {
        _dataSourceFactory = dataSourceFactory_;
    }

    public DBI getJDBI(final Environment environment_) throws ClassNotFoundException
    {
        final DBIFactory factory = new DBIFactory();
        final DBI dbi = factory.build(environment_, getDataSourceFactory(), "postgresql");
        dbi.registerArgumentFactory(new DateAsTimestampArgument());
        return dbi;
    }
}
