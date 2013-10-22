package net.spinetrak.cirmanager.db;

import net.spinetrak.cirmanager.core.User;

import java.util.List;

/**
 * Created by spinetrak on 10/21/13.
 */
public interface IUserDAO
{
    List<User> findAll();

    User findByEmail(final String email_);

    User findByUserid(final int userid_);

    int insert(final String email_, final String password_);
}
