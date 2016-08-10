package org.com.amil.controller;

import java.text.SimpleDateFormat;
import org.com.amil.model.Death;
import org.com.amil.model.Match;
import org.com.amil.model.Player;
import org.com.amil.singleton.AmilSingleton;
import org.com.amil.util.Utils;

public class Processing {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Match match = null;
	
	public void processingFileLog(String content){
		try {
			
			String[] contentFile = content.split("#n#");
			identifyTypeContent(contentFile);
			
		} catch (Exception e) {
			System.err.println("Erro no metodo processingFileLog");
			e.printStackTrace();
		}
	}
	
	public void identifyTypeContent(String[] contentFile){
		try {
						
			for (String cF : contentFile) {
								
				String[] contentLine = cF.split(" - ");		
				
				if(contentLine[1].contains("New match ")){
					match = typeContentNewMatch(contentLine[0], contentLine[1]);
					
				}else if(contentLine[1].contains("killed ")){			
					typeContentDeath(contentLine[0], contentLine[1]);
				
				}else if(contentLine[1].contains("Match ")){			
					typeContentEndMatch(contentLine[0]);
				}
				
			}

			if(match != null){
				AmilSingleton.getInstance().getLsMatchs().add(match);
				Utils.writeFile(match, AmilSingleton.getInstance().printMatch(match));
			}
			
			AmilSingleton.getInstance().printMatch();

		} catch (Exception e) {
			System.err.println("Erro no metodo identifyTypeContent");
			e.printStackTrace();
		}
	}
	
	public Match typeContentNewMatch(String dtCreate, String contentMatch){
		try {
			
			String[] content = contentMatch.replace("New match ", "").split(" ");
			
			Match matchExist = AmilSingleton.getInstance().getMatch(content[0]);
			if(matchExist == null){
				
				match = new Match();
				match.setCode(content[0]);
				match.setDtInit(sdf.parse(dtCreate));
				return match;
				
			}
			
			return null;
			
		} catch (Exception e) {
			System.err.println("Erro no metodo typeContentNewMatch");
			e.printStackTrace();
			return null;
		}
	}
	
	public void typeContentDeath(String dtCreate, String contentDeath){
		try {
			
			if(match != null){
				
				String[] content = contentDeath.split(" ");
				
				Player playerShooter = new Player(content[0]);
				Player playerDead = new Player(content[2]);
				
				Death death = new Death();
				death.setDtCreate(sdf.parse(dtCreate));
				death.setPlayerShooter(match.addPlayerShooter(playerShooter));
				death.setPlayerDead(match.addPlayerDead(playerDead));
				
				match.getLsDeaths().add(death);

			}
			
		} catch (Exception e) {
			System.err.println("Erro no metodo typeContentDeath");
			e.printStackTrace();
		}
	}
	
	public void typeContentEndMatch(String dtEnd){
		try {
			
			if(match != null){
				
				match.setDtEnd(sdf.parse(dtEnd));
				
				
				
			}
			
		} catch (Exception e) {
			System.err.println("Erro no metodo typeContentEndMatch");
			e.printStackTrace();
		}
	}

	
}
