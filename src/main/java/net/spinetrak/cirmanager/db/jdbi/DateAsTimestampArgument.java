package net.spinetrak.cirmanager.db.jdbi;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by spinetrak on 10/21/13.
 */
public class DateAsTimestampArgument implements ArgumentFactory<Date>
{
    @Override
    public boolean accepts(final Class<?> expectedType_, final Object value_, final StatementContext context_)
    {
        return value_ != null && Date.class.isAssignableFrom(value_.getClass());
    }

    @Override
    public Argument build(final Class<?> expectedType_, final Date value_, final StatementContext context_)
    {
        return new Argument()
        {
            @Override
            public void apply(final int position_, final PreparedStatement statement_,
                              final StatementContext context_) throws
                                                               SQLException
            {
                statement_.setTimestamp(position_, new Timestamp(value_.getTime()));
            }
        };
    }
}

