package GameManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Timer;

import javafx.stage.Stage;

public class GameManager {
	private GamePanel gp;
	private MovementManager mm;
	private FinishManager fm;
	private SoundManager sm;
	private FileManager filem;
	private double scale;
	private ArrayList<Player> players;
	private Stage primary;
	/*private ReentrantLock lock1; // For Rolling a die
	private ReentrantLock lock2; // For checking if the token is selected*/
	
	
	public GameManager(double scale, Stage primaryStage){
		this.scale = scale;
		mm = new MovementManager();
		fm = new FinishManager();
		sm = new SoundManager();
		filem = new FileManager();
		players = new ArrayList<Player>(); // empty for now we will get it from above level
		primary = primaryStage;
		/*lock1 = new ReentrantLock();
		lock2 = new ReentrantLock();*/
		gp = new GamePanel(scale);
		
	}
	
	public boolean gameLoop(){
		boolean end = false;
		gp.start(primary);
		mm.isAvailable(0, gp.getBoard(), 6);
		mm.isAvailable(1, gp.getBoard(), 6);
		mm.move(0, gp.getBoard(), -1, 6,0);
		
		mm.move(1, gp.getBoard(), -1, 6,0);
		mm.move(1,gp.getBoard(),13,50,0);
		mm.move(1,gp.getBoard(),11,2,0);
		gp.updateTokens();
		System.out.println(gp.getBoard().getHouses().get(0).toString());
		
		
		/*while(!end){
			Timer timer = new Timer(1000, new MyActionListener());
			end = fm.endGame(gp.getBoard().getEndingSlots());
			System.out.println(end);
		}*/
		return end;
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(gp.isRolled()){
				gp.setRolled(false);
			}
		}
		
	}
}
