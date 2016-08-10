package org.com.amil.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.com.amil.singleton.AmilSingleton;

public class Match {

	private String code;
	private Date dtInit;
	private Date dtEnd;
	
	private List<Player> lsPlayers;
	private List<Death> lsDeaths;
	
	public Match() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDtInit() {
		return dtInit;
	}

	public void setDtInit(Date dtInit) {
		this.dtInit = dtInit;
	}

	public Date getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}

	public List<Player> getLsPlayers() {
		if(lsPlayers == null){
			lsPlayers = new ArrayList<Player>();
		}
		return lsPlayers;
	}

	public List<Death> getLsDeaths() {
		if(lsDeaths == null){
			lsDeaths = new ArrayList<>();
		}
		return lsDeaths;
	}
	
	public Player addPlayerShooter(Player player){
		try {
			
			Player p = getPlayer(player);
			
			if(!player.getName().equals("<WORLD>")){
				p.addKilled();				
			}
			
			return p;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Player addPlayerDead(Player player){
		try {
			
			Player p = getPlayer(player);
			p.addDead();
			return p;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Player getPlayer(Player player){
		try {
			
			boolean exist = false;
			
			for (Player p : getLsPlayers()) {
				
				if(p.getName().equals(player.getName())){
					exist = true;
					player = p;
					break;
				}
				
			}
			
			if (!exist) {
				lsPlayers.add(player);
			}
				
			return player;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
