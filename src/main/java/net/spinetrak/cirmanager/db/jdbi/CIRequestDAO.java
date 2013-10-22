package net.spinetrak.cirmanager.db.jdbi;

import net.spinetrak.cirmanager.core.CIRequest;
import net.spinetrak.cirmanager.core.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by spinetrak on 10/7/13.
 */
@RegisterMapper(CIRequestDAO.Mapper.class)
public interface CIRequestDAO extends net.spinetrak.cirmanager.db.ICIRequestDAO
{
    @SqlQuery(
            "select c.*,u1.email as cEmail,u2.email as sEmail from cirs c left outer join users u1 on c.createdBy=u1.userid left outer join users u2 on c.submittedBy=u2.userid")
    List<CIRequest> findAll();

    @SqlQuery(
            "select c.*,u1.email as cEmail,u2.email as sEmail from cirs c left outer join users u1 on c.createdBy=u1.userid left outer join users u2 on c.submittedBy=u2.userid where ciid = :ciid")
    List<CIRequest> findByCiid(@Bind("ciid") final int ciid_);

    @SqlQuery(
            "select c.*,u1.email as cEmail,u2.email as sEmail from cirs c left outer join users u1 on c.createdBy=u1.userid left outer join users u2 on c.submittedBy=u2.userid where cirid = :cirid")
    CIRequest findByCirid(@Bind("cirid") final int cirid_);

    @GetGeneratedKeys
    @SqlUpdate("insert into cirs (ciid,createdBy,createdOn,status) values (:ciid,:createdBy,:createdOn,:status)")
    int insert(@Bind("ciid") final int ciid_, @Bind("createdBy") final int createdBy_, @Bind(
            "createdOn") final Date createdOn_, @Bind("status") final String status_);

    @SqlUpdate(
            "update cirs set summary=:cir.summary,description=:cir.description,risk=:cir.risk,startDate=:cir.startDate,endDate=:cir.endDate where cirid=:cir.cirid")
    void update(@BindBean("cir") final CIRequest cir_);

    public class Mapper implements ResultSetMapper<CIRequest>
    {
        public CIRequest map(final int index_, final ResultSet resultSet_,
                             final StatementContext statementContext_) throws
                                                                       SQLException
        {
            final CIRequest cir = new CIRequest();
            cir.setCirid(resultSet_.getInt("cirid"));

            final User user = new User();
            cir.setCreatedBy(getUser(resultSet_.getInt("createdBy"), resultSet_.getString("cEmail")));
            cir.setSubmittedBy(getUser(resultSet_.getInt("submittedBy"), resultSet_.getString("sEmail")));
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

        private User getUser(final int userid_, final String email_)
        {
            final User user = new User();
            user.setUserId(userid_);
            user.setEmail(email_);
            return user;
        }
    }
}
