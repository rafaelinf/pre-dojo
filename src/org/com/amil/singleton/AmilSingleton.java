package org.com.amil.singleton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.com.amil.model.Match;
import org.com.amil.model.Player;
import org.com.amil.thread.ThreadReadFile;

public class AmilSingleton {
	
    private static AmilSingleton instance;
    
    private List<Match> lsMatchs = null;
    
    public final static String FOLDER_LOG = "log";
    public final static String FOLDER_RANKING = "ranking";
    private int timeSleepFile = 10000;
    
/*    private ThreadPool threadPool;
    private int corePoolSize = 5;
    private int maximumPoolSize = 5;*/
    
    public synchronized static AmilSingleton getInstance() {
        if (instance == null) {
        	instance = new AmilSingleton();
        }
        return instance;
    }
    
    public void setup(){
    	try {
			
			this.timeSleepFile = 10000;

/*		    int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
		    System.out.println("NUMBER_OF_CORES = " + NUMBER_OF_CORES);
		    
		    threadPool = new ThreadPool(
		            NUMBER_OF_CORES * corePoolSize,
		            NUMBER_OF_CORES * maximumPoolSize,
		            60L,
		            TimeUnit.SECONDS,
		            new LinkedBlockingQueue<Runnable>()
		    );*/

		    ThreadReadFile.initialsReadFileLog();
		    
		} catch (Exception e) {
			System.err.println("Erro no metodo setup");
			e.printStackTrace();
		}
    }

/*	public ThreadPool getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(ThreadPool threadPool) {
		this.threadPool = threadPool;
	}
*/
	public int getTimeSleepFile() {
		return timeSleepFile;
	}

	public void setTimeSleepFile(int timeSleepFile) {
		this.timeSleepFile = timeSleepFile;
	}

	public List<Match> getLsMatchs() {
		if(lsMatchs == null){
			lsMatchs = new ArrayList<>();
		}
		return lsMatchs;
	}
	
	public String printMatch(){
		try {

			StringBuilder pr = new StringBuilder();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			for (Match m : AmilSingleton.getInstance().getLsMatchs()) {

				pr.append("");
				pr.append("-----------------------------------------------------------------");
				pr.append("Partida: " + m.getCode());
				pr.append("Início:  " + sdf.format(m.getDtInit()));
				pr.append("Fim:     " + sdf.format(m.getDtEnd()));
				
				pr.append("");
				pr.append("                             Ranking                             ");
				pr.append("-----------------------------------------------------------------");
				pr.append("          Jogador          Assassinatos          Mortes          ");
				pr.append("-----------------------------------------------------------------");

				Collections.sort(m.getLsPlayers());

				for (int i = 0; i < m.getLsPlayers().size(); i++) {
					Player p = m.getLsPlayers().get(i);					
					pr.append(p.getName() + "\t                        " + p.getKilled() + "\t            " + p.getDead());
				}

				pr.append("-----------------------------------------------------------------");

			}
			
			return pr.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro no metodo printMatch");
			return null;
		}
	}
	
	public String printMatch(Match m){
		try {

			StringBuilder pr = new StringBuilder();			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			pr.append("\n");
			pr.append("-----------------------------------------------------------------").append("\n");
			pr.append("Partida: " + m.getCode()).append("\n");
			pr.append("Início:  " + sdf.format(m.getDtInit())).append("\n");			
			pr.append("Fim:     " + sdf.format(m.getDtEnd())).append("\n");
			
			pr.append("").append("\n");
			pr.append("                             Ranking                             ").append("\n");
			pr.append("-----------------------------------------------------------------").append("\n");
			pr.append("          Jogador          Assassinatos          Mortes          ").append("\n");
			pr.append("-----------------------------------------------------------------").append("\n");

			Collections.sort(m.getLsPlayers());

			for (int i = 0; i < m.getLsPlayers().size(); i++) {
				Player p = m.getLsPlayers().get(i);					
				pr.append(p.getName() + "\t                        " + p.getKilled() + "\t            " + p.getDead()).append("\n");
			}

			pr.append("-----------------------------------------------------------------").append("\n");

			return pr.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro no metodo printMatch");
			return null;
		}
	}
	
	public Match getMatch(String code){
		try {
					
			for (Match m : getLsMatchs()) {
				
				if(m.getCode().equals(code)){
					return m;
				}
				
			}
							
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}
    
}
