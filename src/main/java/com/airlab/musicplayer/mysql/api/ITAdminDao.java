package com.airlab.musicplayer.mysql.api;

import com.airlab.musicplayer.model.musicinfo.TAdmin;
import com.airlab.musicplayer.mysql.core.IDao;

public interface ITAdminDao extends IDao<TAdmin>{
	TAdmin queryByUsernameAndPassword(String username, String password);
}
