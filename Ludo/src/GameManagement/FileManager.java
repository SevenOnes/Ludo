package GameManagement;

import GameManagement.Player;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.javafx.fxml.expression.LiteralExpression;

import java.awt.List;
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
		FileOutputStream fos = new FileOutputStream(file);
		PrintWriter pw = new PrintWriter(fos);
		for(Player i:players)
		{

			pw.write(i.toString());
			pw.write("\n");
		}
		pw.close();	   	   
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
			return null;
		}	
		return newPlayers;
	}
	public ArrayList<Player> update(ArrayList<Player> newList, ArrayList<Player> list)
	{


		for(int i=0; i  < newList.size(); i++)
		{
			boolean flag = false;
			for(int j=0; j< list.size(); j++)
			{
				if(list.get(j).getName().equals(newList.get(i).getName()))
				{
					list.get(j).setPoint(list.get(j).getPoint()+newList.get(i).getPoint());
					flag = true;
				}
				if( j == list.size()-1 && flag == false)
				{
					list.add(newList.get(i));
				}

			}
		}
		return list;
	}
	public void writeSettingsTxt(int res, int sound) throws FileNotFoundException
	{
		File file = new File("settings.txt");
		FileOutputStream fos = new FileOutputStream(file);
		PrintWriter pw = new PrintWriter(fos);
		pw.write(res+ "/" + sound);
		pw.write("\n");
		pw.close();	   	   
	}
	public int[] readSetting()
	{
		int[] newArray = new int[2];
		File file = new File("settings.txt");
		int res;
		int sound;
		try{
			Scanner s = new Scanner(file);
			String tmp = s.nextLine();
			res= Integer.parseInt(tmp.substring(0,1));
			sound = Integer.parseInt(tmp.substring(2,3));
			newArray[0] = res;
			newArray[1] = sound;
		}
		
		catch(Exception e)
		{
			return null;
		}
		return newArray;
	}

	// For testing, it writes arrayList into the txt file
	public static void main(String[] args) throws IOException
	{
		Player P1 = new Player("Al1", 56, 20);
		Player P2 = new Player("Alp1", 21, 32);
		Player P3 = new Player("Berk", 21, 210);
		Player P4 = new Player("asd1", 10 , 123);
		//System.out.println(p.players.size());
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(P1);
		players.add(P2);
		players.add(P3);
		players.add(P4);
		FileManager f = new FileManager();
		//f.writeFile(players);
		ArrayList<Player> temp = f.readFile();
		temp = f.update(players, temp);
		for(int i=0; i< temp.size(); i++)
		{
			System.out.println(temp.get(i));
		}

		System.out.println(temp.size());
		f.writeFile(temp);
		f.writeSettingsTxt(1, 2);
		int[] tmp = new int[2];
		tmp = f.readSetting();
		System.out.println(tmp[0]);
		System.out.println(tmp[1]);
	}
}