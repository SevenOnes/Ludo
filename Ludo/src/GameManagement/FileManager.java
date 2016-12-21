package GameManagement;

import GameManagement.Player;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class FileManager {
	
	
	private static Player p = new Player();
	
	public FileManager()
	{
		
	}
	public void writeFile(ArrayList<Player> players) throws FileNotFoundException, IOException { 
	   File file = new File("players.txt");
	   FileOutputStream fos = new FileOutputStream(file);
	   PrintWriter pw = new PrintWriter(fos);

	   for (Player i : players){
	       pw.println(i);
	   }
	   pw.close();
	   fos.close();
	}

	// For testing, it writes arrayList into the txt file
	public static void main(String[] args) throws IOException
	{
		Player P1 = new Player("Alp", 56, 21);
		Player P2 = new Player("Berk", 21, 210);
		Player P3 = new Player("asd", 10 , 123);
		System.out.println(p.players.size());
		FileManager f = new FileManager();
		f.writeFile(p.players);	
	}
}
