package net.spinetrak.cirmanager.core;

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
    private Date _submittedOn;
    private Date _startDate;
    private String _risk;
    private Date _endDate;
    private String _description;
    private User _submittedBy;

    public String getCIRStatus()
    {
        return _status;
    }

    public int getCiid()
    {
        return _ciid;
    }

    public int getCirid()
    {
        return _cirid;
    }

    public User getCreatedBy()
    {
        return _createdBy;
    }

    public Date getCreatedOn()
    {
        return _createdOn;
    }

    public String getDescription()
    {
        return _description;
    }

    public Date getEndDate()
    {
        return _endDate;
    }

    public String getRisk()
    {
        return _risk;
    }

    public Date getStartDate()
    {
        return _startDate;
    }

    public User getSubmittedBy()
    {
        return _submittedBy;
    }

    public Date getSubmittedOn()
    {
        return _submittedOn;
    }

    public String getSummary()
    {
        return _summary;
    }

    public void setCIRStatus(final String status_)
    {
        _status = status_;
    }

    public void setCiid(final int ciid_)
    {
        _ciid = ciid_;
    }

    public void setCirid(final int cirid_)
    {
        _cirid = cirid_;
    }

    public void setCreatedBy(final User createdBy_)
    {
        _createdBy = createdBy_;
    }

    public void setCreatedOn(final Date createdOn_)
    {
        _createdOn = createdOn_;
    }

    public void setDescription(final String description_)
    {
        _description = description_;
    }

    public void setEndDate(final Date endDate_)
    {
        _endDate = endDate_;
    }

    public void setRisk(final String risk_)
    {
        _risk = risk_;
    }

    public void setStartDate(final Date startDate_)
    {
        _startDate = startDate_;
    }

    public void setSubmittedBy(final User submittedBy_)
    {
        _submittedBy = submittedBy_;
    }

    public void setSubmittedOn(final Date submittedOn_)
    {
        _submittedOn = submittedOn_;
    }

    public void setSummary(final String summary_)
    {
        _summary = summary_;
    }
}
