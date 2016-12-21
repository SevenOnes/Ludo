package GameManagement;

import java.util.ArrayList;

import com.sun.org.glassfish.gmbal.GmbalException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameManager extends Thread{
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
	boolean isFinishedTurn ;
	int playerTurn;
	int numberOfPlayer;
	boolean isMoved;

	public GameManager(double scale, Stage primaryStage){
		super();
		this.scale = scale;
		mm = new MovementManager();
		fm = new FinishManager();
		sm = new SoundManager();
		filem = new FileManager();
		players = new ArrayList<Player>(); // empty for now we will get it from above level
		primary = primaryStage;
		/*lock1 = new ReentrantLock();
		lock2 = new ReentrantLock();*/
		gp = new GamePanel(scale,this);
		gp.start(primary);
		isFinishedTurn = true;
		playerTurn = 0;
		numberOfPlayer = 4;
		isMoved = false;
		

	}

	public boolean turn(int index,int rolled){
		boolean end = false;
		
		mm.move(0,gp.getBoard(),index,rolled,0);
		gp.updateTokens();
		return fm.endGame(gp.getBoard().getEndingSlots());

	}


	public void run() {
		Timeline rollWatier = new Timeline(new KeyFrame(Duration.seconds(0.3), new RolledWaiter()));
		rollWatier.setCycleCount(Timeline.INDEFINITE);
		rollWatier.play();
		Timeline clickWaiter = new Timeline(new KeyFrame(Duration.seconds(0.3), new ClickWaiter()));
		clickWaiter.setCycleCount(Timeline.INDEFINITE);
		clickWaiter.play();

	}
	
	private class RolledWaiter implements EventHandler{


		public RolledWaiter(){
			
		}
		@Override
		public void handle(Event arg0) {
			if(isFinishedTurn){
				if(gp.isRolled()){
					System.out.println(playerTurn);
					boolean flag = mm.isAvailable(playerTurn, gp.getBoard(), gp.getBoard().getDie().getfaceValue());
					if(flag == false){
						gp.updateTokens();
						gp.setRolled(false);
						playerTurn = (playerTurn + 1)%numberOfPlayer;
					}else{
						gp.updateTokens();
						isFinishedTurn = false;
					}
					
				}
			}
		}
		
	}
	
	private class ClickWaiter implements EventHandler{

		public ClickWaiter(){
			
		}
		@Override
		public void handle(Event arg0) {
			if(!isFinishedTurn){
				if(gp.isSelected()){
					boolean flag = false;
					int[] clicked = gp.getClicked_token();
					if(clicked[0] == 2){
						flag = mm.move(clicked[1],gp.getBoard(),-1,gp.getBoard().getDie().getfaceValue(),0);
					}
					else if(clicked[0] == 0){
						flag = mm.move(clicked[1],gp.getBoard(),clicked[2],gp.getBoard().getDie().getfaceValue(),clicked[3]);
					}
					else if(clicked[0] == 1){
						flag = mm.move(clicked[1],gp.getBoard(),clicked[2]+52,gp.getBoard().getDie().getfaceValue(),clicked[3]);
					}
					if(flag == true){
						gp.updateTokens();
						mm.makeUnAvaliable(gp.getBoard());
						gp.setRolled(false);
						isFinishedTurn = true;
						playerTurn = (playerTurn + 1)%numberOfPlayer;
					}
					else{
						gp.updateTokens();
					}
				}
			}
			
		}
		
	}

}
