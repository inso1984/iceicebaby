package application;

import java.util.*;

public class Player {
	
	
	public String getName() {
		
		Scanner Eingabe = new Scanner(System.in);
		
		System.out.println("Bitte gebe deinen Name ein:");
		
		String Name = Eingabe.next();
		
		return Name;
		
	}

}
