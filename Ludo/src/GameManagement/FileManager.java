package GameManagement;

import GameManagement.Player;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileManager {
	
	
	private static Player p = new Player();
	public static ArrayList<Player> tmpPlayers = new ArrayList<Player>();
	
	public FileManager()
	{
		
	}
	public void writeFile(ArrayList<Player> players) throws FileNotFoundException, IOException { 
		   
		   File file = new File("players.txt");
		   FileWriter fw = new FileWriter(file, true);
		   BufferedWriter bw = new BufferedWriter(fw);
		   for(Player i:players)
		   {
			   bw.write(i.toString());
			   bw.write("\n");
		   }
		   bw.close();	   	   
	}
	
	public ArrayList<Player> readFile()
	{
		File file = new File("players.txt");
		String tmpName = null;
		String newName = null;
		String name = null;
		int point;
		int id;
		ArrayList<Player> newPlayers = new ArrayList<Player>();
		
		try{
			Scanner sc = new Scanner(file);
			
			
			
			while(sc.hasNextLine())
			{
				tmpName = sc.nextLine();
					
					newName = tmpName.substring(tmpName.indexOf("/")+1 , tmpName.length()-1);
					name = newName.substring(newName.indexOf("/") +1, newName.length());
					for(int i=0; i < tmpName.length(); i++)
					{
						if(tmpName.charAt(i) == '/')
						{
							tmpName = tmpName.substring(0, i);
						}
					}
					
					for(int i=0; i < newName.length(); i++)
					{
						if(newName.charAt(i) == '/')
						{
							newName = newName.substring(0, i);
						}
					}
					id = Integer.parseInt(newName);
					point = Integer.parseInt(name);
					boolean flag = false;
					for(int i=0; i < newPlayers.size(); i++)
					{
						if(tmpName.equals(newPlayers.get(i).getName()))
						{
							flag = true;
							break;
						}
						else
							flag = false;
					}
					if(flag == false)
					{
						Player P = new Player(tmpName, id, point);
						newPlayers.add(P);
					}
			}
			sc.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			
		}	
		return newPlayers;
	
	}
	
	// For testing, it writes arrayList into the txt file
	public static void main(String[] args) throws IOException
	{
		Player P1 = new Player("Alp", 56, 21);
		Player P2 = new Player("Alp", 21, 32);
		Player P3 = new Player("Berk", 21, 210);
		Player P4 = new Player("asd", 10 , 123);
		Player P5 = new Player("Ahmet", 123, 123);
		//System.out.println(p.players.size());
		FileManager f = new FileManager();
		f.writeFile(p.players);
		ArrayList<Player> temp = f.readFile();
		for(int i=0; i< temp.size(); i++)
		{
			System.out.println(temp.get(i));
		}
		
		System.out.println(temp.size());
		
	}
}
