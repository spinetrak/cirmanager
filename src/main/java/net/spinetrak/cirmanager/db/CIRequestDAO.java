package net.spinetrak.cirmanager.db;

import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.core.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by spinetrak on 10/7/13.
 */
@RegisterMapper(CIRequestDAO.Mapper.class)
public interface CIRequestDAO
{
    @SqlQuery("select * from cirs where ciid = :ciid")
    List<CIRequest> findByCiid(@Bind("ciid") final int ciid_);

    @GetGeneratedKeys
    @SqlUpdate("insert into cirs (createdBy,ciid) values (:createdBy,:ciid)")
    int insert(@Bind("createdBy") final int createdBy_, @Bind("ciid") final int ciid_);

    @SqlQuery("select * from cirs where cirid = :cirid")
    CIRequest findByCirid(@Bind("cirid") final int cirid_);

    public class Mapper implements ResultSetMapper<CIRequest>
    {
        public CIRequest map(final int index_, final ResultSet resultSet_, final StatementContext statementContext_) throws
                                                                                                                     SQLException
        {
            final CIRequest cir = new CIRequest();
            cir.setCirid(resultSet_.getInt("cirid"));
            cir.setCreatedBy(getUser(resultSet_.getInt("createdBy")));
            cir.setSubmittedBy(getUser(resultSet_.getInt("submittedBy")));
            cir.setCiid(resultSet_.getInt("ciid"));
            cir.setCreatedOn(resultSet_.getTimestamp("createdOn"));
            cir.setDescription(resultSet_.getString("description"));
            cir.setEndDate(resultSet_.getTimestamp("endDate"));
            cir.setRisk(resultSet_.getString("risk"));
            cir.setStartDate(resultSet_.getTimestamp("startDate"));
            cir.setCIRStatus(resultSet_.getString("status"));
            cir.setSubmittedOn(resultSet_.getTimestamp("submittedOn"));
            cir.setSummary(resultSet_.getString("summary"));

            return cir;
        }

        private User getUser(final int userid_)
        {
            final User user = new User();
            user.setUserid(userid_);
            return user;
        }
    }
}
