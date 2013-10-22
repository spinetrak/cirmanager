package net.spinetrak.cirmanager.db;

import net.spinetrak.cirmanager.core.CISystem;

import java.util.List;

/**
 * Created by spinetrak on 10/21/13.
 */
public interface ICISystemDAO
{
    List<CISystem> findAll();

    CISystem findByCiid(final int ciid_);

    int insert(final String name_);
}
