package org.com.amil.main;

import org.com.amil.singleton.AmilSingleton;

public class Main {

	public static void main(String[] args) {

		AmilSingleton.getInstance().setup();
		
	}

}
