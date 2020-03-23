package com.dw.domain;

public class Level {
	
	
	private Integer id;
	private String level_ID;
	private String level_phone;
	private String level_fID;
	private String location;
	public Level(Integer id, String level_ID, String level_phone, String level_fID, String location) {
		super();
		this.id = id;
		this.level_ID = level_ID;
		this.level_phone = level_phone;
		this.level_fID = level_fID;
		this.location = location;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLevel_ID() {
		return level_ID;
	}
	public void setLevel_ID(String level_ID) {
		this.level_ID = level_ID;
	}
	public String getLevel_phone() {
		return level_phone;
	}
	public void setLevel_phone(String level_phone) {
		this.level_phone = level_phone;
	}
	public String getLevel_fID() {
		return level_fID;
	}
	public void setLevel_fID(String level_fID) {
		this.level_fID = level_fID;
	}
//	public String getLevel_nID() {
//		return level_nID;
//	}
//	public void setLevel_nID(String level_nID) {
//		this.level_nID = level_nID;
//	}
//	public String getLevel_nnID() {
//		return level_nnID;
//	}
//	public void setLevel_nnID(String level_nnID) {
//		this.level_nnID = level_nnID;
//	}
	public Level(Integer id, String level_ID, String level_phone, String level_fID, String level_nID,
			String level_nnID) {
		super();
		this.id = id;
		this.level_ID = level_ID;
		this.level_phone = level_phone;
		this.level_fID = level_fID;
//		this.level_nID = level_nID;
//		this.level_nnID = level_nnID;
	}
	public Level() {
		super();
	}
	
	@Override
	public String toString() {
		return "Level [id=" + id + ", level_ID=" + level_ID + ", level_phone=" + level_phone + ", level_fID="
				+ level_fID + "]";
	}
	
	
	

}
