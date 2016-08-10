package org.com.amil.model;

public class Player implements Comparable<Player>{

	private String name;	
	private Integer killed = 0;
	private Integer dead = 0;
	
	public Player() {
	}
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addKilled(){
		if(this.killed == null){
			this.killed = 0;
		}
		this.killed++;
	}
	
	public Integer getKilled() {
		return killed;
	}
	
	public void addDead(){
		if(this.dead == null){
			this.dead = 0;
		}
		this.dead++;
	}
	
	public Integer getDead() {
		return dead;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int compareTo(Player player) {
	       if (this.killed > player.killed) {
	            return -1;
	        }
	        if (this.killed < player.killed) {
	            return 1;
	        }
	        return 0;	}

}
