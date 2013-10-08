package net.spinetrak.cirmanager.core;

import javax.persistence.*;

/**
 * Created by spinetrak on 10/7/13.
 */

@Entity
@Table(name = "cirs")
@NamedQueries({
                      @NamedQuery(
                              name = "net.spinetrak.cirmanager.core.CIRequest.findByCiid",
                              query = "SELECT c FROM CIRequest c where ciid=:ciid"
                      )
              })
public class CIRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cirid", nullable = false)
    private int _cirid;
    @Column(name = "ciid", nullable = false)
    private int _ciid;

    public int getCiid()
    {
        return _ciid;
    }

    public void setCiid(final int ciid_)
    {
        _ciid = ciid_;
    }

    public int getCirid()
    {
        return _cirid;
    }

    public void setCir(final int cirid_)
    {
        _cirid = cirid_;
    }
}
