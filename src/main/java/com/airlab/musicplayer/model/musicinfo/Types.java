package com.airlab.musicplayer.model.musicinfo;

import com.airlab.musicplayer.model.BaseModel;
import com.airlab.musicplayer.model.IIdAware;

/**
 * TAdmin generated by MyEclipse Persistence Tools
 */

public class Types extends BaseModel implements IIdAware {
	private static final long serialVersionUID = 3894241973412039146L;

	private Long id;
	private String names;//

	// Constructors

	/** default constructor */
	public Types() {
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

}