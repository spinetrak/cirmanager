package net.spinetrak.cirmanager.db;

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
 * Created by spinetrak on 10/19/13.
 */
@RegisterMapper(UserDAO.Mapper.class)
public interface UserDAO
{
    @GetGeneratedKeys
    @SqlUpdate("insert into users (email,password) values (:email, :password)")
    int insert(@Bind("email") final String email_, @Bind("password") final String password_);

    @SqlQuery("select * from users")
    List<User> findAll();

    @SqlQuery("select * from users where userid = :userid")
    User findByUserid(@Bind("userid") final int userid_);

    @SqlQuery("select * from users where email = :email")
    User findByEmail(@Bind("email") final String email_);

    void close();

    public class Mapper implements ResultSetMapper<User>
    {
        public User map(final int index_, final ResultSet resultSet_, final StatementContext statementContext_) throws
                                                                                                                SQLException
        {
            final User user = new User();
            user.setUserid(resultSet_.getInt("userid"));
            user.setEmail(resultSet_.getString("email"));
            user.setPassword(resultSet_.getString("password"));
            return user;
        }
    }
}
