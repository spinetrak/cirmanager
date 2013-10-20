package net.spinetrak.cirmanager.db;

import net.spinetrak.cirmanager.core.CISystem;
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
 * Created by spinetrak on 9/28/13.
 */
@RegisterMapper(CISystemDAO.Mapper.class)
public interface CISystemDAO
{
    @GetGeneratedKeys
    @SqlUpdate("insert into ciids (name) values (:name)")
    int insert(@Bind("name") final String name_);

    @SqlQuery("select * from ciids")
    List<CISystem> findAll();

    @SqlQuery("select * from ciids where ciid = :ciid")
    CISystem findByCiid(@Bind("ciid") final int ciid_);

    void close();

    public class Mapper implements ResultSetMapper<CISystem>
    {
        public CISystem map(final int index_, final ResultSet resultSet_, final StatementContext statementContext_) throws
                                                                                                                    SQLException
        {
            final CISystem cis = new CISystem();
            cis.setCiid(resultSet_.getInt("ciid"));
            cis.setName(resultSet_.getString("name"));
            return cis;
        }
    }
}
