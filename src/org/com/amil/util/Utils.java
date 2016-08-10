package org.com.amil.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.com.amil.model.Match;
import org.com.amil.singleton.AmilSingleton;

public class Utils {

	public static String getPathFileLog(String folder) {
		try {
			
			String pathFull;
			
			String so = System.getProperty("os.name").substring(0, 3);
			String separator = System.getProperty("file.separator");

			if (so.equals("Win")) {
				pathFull = "C:" + separator + "devamil" + separator + folder + separator;
			} else {
				pathFull = separator + "devamil" + separator + folder + separator;
			}
						
			return pathFull;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erro no método getPathFileLog");
			return null;
		}
		
	}
	
	public static boolean writeFile(Match match, String content){
		try {

			String dirTarget = getPathFileLog(AmilSingleton.FOLDER_RANKING);
			new File(dirTarget).mkdir();
			
			File file = new File(dirTarget + System.getProperty("file.separator") + "Partida " + match.getCode() +".txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
