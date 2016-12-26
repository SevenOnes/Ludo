package GameManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public interface FileManager {
	public void writeFile(ArrayList<Player> players);
	public ArrayList<Player> readFile();
	public ArrayList<Player> update(ArrayList<Player> newList, ArrayList<Player> list);
	public void writeSettingsTxt(int res, int sound) throws FileNotFoundException;
	public int[] readSettings();
 

}
