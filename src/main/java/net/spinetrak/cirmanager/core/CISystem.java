package net.spinetrak.cirmanager.core;

/**
 * Created by spinetrak on 9/28/13.
 */

public class CISystem
{
    private int _ciid;
    private String _name;

    public String getName()
    {
        return _name;
    }

    public int getCiid()
    {
        return _ciid;
    }

    public void setName(final String name_)
    {
        _name = name_;
    }

    public void setCiid(final int ciid_)
    {
        _ciid = ciid_;
    }
}
