package GameManagement;

import java.util.ArrayList;
import GameManagement.FileManager;
import java.io.IOException;


public class Player {
	
	private int id;
	private int point;
	private String name;
	
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	public Player()
	{
		
	}
	
	public Player(String name, int id, int point)
	{
		this.name = name;
		this.id = id;
		this.point = point;
		
		players.add(this);
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
		return "Name of the player = " + name + " Id:" + id + " Point:" + point ;
		
	}
	

	
// For testing
	
public static void main(String[] args) throws IOException
{
	Player P1 = new Player("ALp", 56, 21);
	Player P2 = new Player("Berk", 21, 210);
	Player P3 = new Player("Berk", 21, 210);
	System.out.println(players.size());
	Player p = new Player();

	for(int i=0; i<players.size();i++)
	{
		System.out.println((players.get(i)).toString());
	}
	FileManager f = new FileManager();
	f.writeFile(players);
}


	
	
	

}