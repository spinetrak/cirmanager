package net.spinetrak.cirmanager.core;

/**
 * Created by spinetrak on 9/28/13.
 */

public class User
{
    private int _userid;
    private String _email;
    private String _password;

    public int getUserId()
    {
        return _userid;
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail(final String email_)
    {
        _email = email_;
    }

    public String getPassword()
    {
        return _password;
    }

    public void setPassword(final String password_)
    {
        _password = password_;
    }

    public void setUserId(final int userid_)
    {
        _userid = userid_;
    }

}
