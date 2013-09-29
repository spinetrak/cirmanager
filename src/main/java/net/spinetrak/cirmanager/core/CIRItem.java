package net.spinetrak.cirmanager.core;

import javax.persistence.*;

/**
 * Created by spinetrak on 9/28/13.
 */

@Entity
@Table(name = "ciids")
@NamedQueries({
                      @NamedQuery(
                              name = "net.spinetrak.cirmanager.core.CIRItem.findAll",
                              query = "SELECT c FROM CIRItem c"
                      )
              })
public class CIRItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ciid", nullable = false)
    private String _ciid;
    @Column(name = "name", nullable = false)
    private String _name;

    public String getName()
    {
        return _name;
    }

    public String getCiid()
    {
        return _ciid;
    }

    private void setName(final String name_)
    {
        _name = name_;
    }

    private void setCiid(final String ciid_)
    {
        _ciid = ciid_;
    }
}
