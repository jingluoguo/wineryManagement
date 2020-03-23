package com.dw.domain;

public class LastBalance {
	private Integer id;
	private String user_ID;
	private float bonus_account;
	private String branch;
	private String addtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public float getBonus_account() {
		return bonus_account;
	}
	public void setBonus_account(float bonus_account) {
		this.bonus_account = bonus_account;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	@Override
	public String toString() {
		return "LastBalance [id=" + id + ", user_ID=" + user_ID + ", bonus_account=" + bonus_account + ", branch="
				+ branch + ", addtime=" + addtime + "]";
	}
	
}
