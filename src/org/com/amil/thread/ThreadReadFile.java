package org.com.amil.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.com.amil.controller.Processing;
import org.com.amil.singleton.AmilSingleton;
import org.com.amil.util.Utils;

public class ThreadReadFile {
	
    public static void initialsReadFileLog(){
    	try {
			
        	new Thread(new Runnable() {
    			
    			public void run() {

    				String dir = Utils.getPathFileLog(AmilSingleton.FOLDER_LOG);   				
    				File srcDir =  new File(dir);
    				
    				while(true){
    					
    					//System.out.println("Total de arquivos: " + srcDir.listFiles().length);
    					
    					for(File srcFile : srcDir.listFiles()) {
    						//System.out.println("Arquivo: " + srcFile.getPath());				    
    					    
    						String contentFile = readFile(srcFile);
    					    
    					    Processing processing = new Processing();
    					    processing.processingFileLog(contentFile);
    					    
    					}
    					
    					try {
    						Thread.sleep(AmilSingleton.getInstance().getTimeSleepFile());
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					} catch (Exception e) {
    						e.printStackTrace();
						}
    					
    				}
    								
    			}
    		}).start();
    		
		} catch (Exception e) {
			System.err.println("Erro no metodo execSend");
			e.printStackTrace();
		}
    }
    
    private static String readFile(File file){
    	BufferedReader bReader = null;
    	try {
			
    		StringBuilder content = new StringBuilder();
    		
    		FileReader arq = new FileReader(file);
    		bReader = new BufferedReader(arq);

			while(bReader.ready()){
				content.append(bReader.readLine()).append("#n#");
			}
		    
			return content.toString();

    	} catch (IOException e) {
    		e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Erro no metodo readFile");
			e.printStackTrace();
		}finally {
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	return null;
    }
    
}
