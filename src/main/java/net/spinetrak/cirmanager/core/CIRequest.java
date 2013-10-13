package net.spinetrak.cirmanager.core;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "createdBy", nullable = false)
    private String _createdBy;
    @Column(name = "createdOn", nullable = false)
    private Date _createdOn;
    @Column(name = "summary", nullable = false)
    private String _summary;

    public String getCreatedBy()
    {
        return _createdBy;
    }

    public void setCreatedBy(final String createdBy_)
    {
        _createdBy = createdBy_;
    }

    public Date getCreatedOn()
    {
        return _createdOn;
    }

    public void setCreatedOn(final Date createdOn_)
    {
        _createdOn = createdOn_;
    }

    public String getSummary()
    {
        return _summary;
    }

    public void setSummary(final String summary_)
    {
        _summary = summary_;
    }

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

    public void setCirid(final int cirid_)
    {
        _cirid = cirid_;
    }
}
