package GameManagement;

import java.util.ArrayList;
import GameManagement.FileManager;
import java.io.IOException;


public class Player {
	
	private int id;
	private int point;
	private String name;

	//public static ArrayList<Player> players = new ArrayList<Player>();
	
	public Player()
	{
		
	}
	
	public Player(String name, int id, int point)
	{
		this.name = name;
		this.id = id;
		this.point = point;
		
		
	}
	//Not required
	/*public ArrayList<Player> getPlayerList()
	{
		return players;
	}
	*/
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setPoint(int point)
	{
		this.point = point;
	}
	public int getPoint()
	{
		return point;
	}
	public String toString()
	{
		return  name + "/" + id + "/" + point + "/";
		
	}
	

	
// For testing
	
public static void main(String[] args) throws IOException
{

	//Player P1 = new Player("asd", 1221, 12210);
	//Player P2 = new Player("Basd", 2441, 210);
	Player P3 = new Player("Ahmet", 23, 213);
	//System.out.println(players.size());
	Player p = new Player();

}


	
	
	

}