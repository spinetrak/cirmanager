package net.spinetrak.cirmanager.db.jdbi;

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
public interface UserDAO extends net.spinetrak.cirmanager.db.IUserDAO
{
    void close();

    @SqlQuery("select * from users")
    List<User> findAll();

    @SqlQuery("select * from users where email = :email")
    User findByEmail(@Bind("email") final String email_);

    @SqlQuery("select * from users where userid = :userid")
    User findByUserid(@Bind("userid") final int userid_);

    @GetGeneratedKeys
    @SqlUpdate("insert into users (email,password) values (:email, :password)")
    int insert(@Bind("email") final String email_, @Bind("password") final String password_);

    public class Mapper implements ResultSetMapper<User>
    {
        public User map(final int index_, final ResultSet resultSet_, final StatementContext statementContext_) throws
                                                                                                                SQLException
        {
            final User user = new User();
            user.setUserId(resultSet_.getInt("userid"));
            user.setEmail(resultSet_.getString("email"));
            user.setPassword(resultSet_.getString("password"));
            return user;
        }
    }
}
