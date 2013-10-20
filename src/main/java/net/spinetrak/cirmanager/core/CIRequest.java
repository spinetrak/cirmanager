package net.spinetrak.cirmanager.core;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by spinetrak on 10/7/13.
 */

public class CIRequest
{
    private int _cirid;
    private int _ciid;
    private User _createdBy;
    private Date _createdOn;
    private String _status;
    private String _summary;
    private Timestamp _submittedOn;
    private Timestamp _startDate;
    private String _risk;
    private Timestamp _endDate;
    private String _description;
    private User _submittedBy;

    public String getCIRStatus()
    {
        return _status;
    }

    public void setCIRStatus(final String status_)
    {
        _status = status_;
    }

    public User getCreatedBy()
    {
        return _createdBy;
    }

    public void setCreatedBy(final User createdBy_)
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

    public void setSubmittedOn(final Timestamp submittedOn_)
    {
        _submittedOn = submittedOn_;
    }

    public void setStartDate(final Timestamp startDate_)
    {
        _startDate = startDate_;
    }

    public void setRisk(final String risk_)
    {
        _risk = risk_;
    }

    public void setEndDate(final Timestamp endDate_)
    {
        _endDate = endDate_;
    }

    public void setSubmittedBy(final User submittedBy_)
    {
        _submittedBy = submittedBy_;
    }

    public void setDescription(final String description_)
    {
        _description = description_;
    }
}
