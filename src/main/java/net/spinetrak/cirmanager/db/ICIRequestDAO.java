package net.spinetrak.cirmanager.db;

import net.spinetrak.cirmanager.core.CIRequest;

import java.util.Date;
import java.util.List;

/**
 * Created by spinetrak on 10/21/13.
 */
public interface ICIRequestDAO
{
    List<CIRequest> findAll();

    List<CIRequest> findByCiid(final int ciid_);

    CIRequest findByCirid(final int cirid_);

    int insert(final int ciid_, final int createdBy_, final Date createdOn_, final String status_);

    void update(final CIRequest cir_);
}
