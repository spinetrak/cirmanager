package net.spinetrak.cirmanager;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

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
}
