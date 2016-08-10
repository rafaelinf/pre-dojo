package org.com.amil.model;

import java.util.Date;

public class Death {

	private Date dtCreate;
	private Player playerShooter;
	private Player playerDead;
	
	public Death() {
	}
	
	public Date getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Player getPlayerShooter() {
		return playerShooter;
	}
	
	public void setPlayerShooter(Player playerShooter) {
		this.playerShooter = playerShooter;
	}
	
	public Player getPlayerDead() {
		return playerDead;
	}
	
	public void setPlayerDead(Player playerDead) {
		this.playerDead = playerDead;
	}
	
}
