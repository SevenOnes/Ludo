package GameManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import GameEntities.Token;
import Panels.MainMenu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
	Stage primaryStage;
	Timeline rollWaiter;
	Timeline clickWaiter;
	boolean showFlag;
	boolean isMoved;
	
	ArrayList<Integer> finishedPlayer ;
	public GameManager(double scale, Stage primaryStage,ArrayList<Player> players){
		super();
		this.scale = scale;
		mm = new MovementManager();
		fm = new FinishManager();
		sm = new SoundManager();
		filem = new FileManager();
		
		finishedPlayer = new ArrayList<Integer>();
		this.players = players;
		primary = primaryStage;
		/*lock1 = new ReentrantLock();
		lock2 = new ReentrantLock();*/
		gp = new GamePanel(scale,this);
		gp.start(primary);
		isFinishedTurn = true;
		playerTurn = 0;
		numberOfPlayer = players.size();
		isMoved = false;
		showFlag = false;

	}

	public void test(){
		for(int i = 0;i < gp.getBoard().getHouses().get(0).getTokens().size();){
			Token tmp = gp.getBoard().getHouses().get(0).retriveToken();
			gp.getBoard().getSlots()[9].addInsideToken(tmp);
		}
		Token tmp = gp.getBoard().getHouses().get(1).retriveToken();
		gp.getBoard().getSlots()[10].addInsideToken(tmp);
	}
	public void run() {
		rollWaiter = new Timeline(new KeyFrame(Duration.seconds(0.3), new RolledWaiter()));
		rollWaiter.setCycleCount(Timeline.INDEFINITE);
		rollWaiter.play();
		clickWaiter = new Timeline(new KeyFrame(Duration.seconds(0.3), new ClickWaiter()));
		clickWaiter.setCycleCount(Timeline.INDEFINITE);
		clickWaiter.play();

	}
	
	private class RolledWaiter implements EventHandler{


		public RolledWaiter(){
			
		}
		@Override
		public void handle(Event arg0) {
			if(isFinishedTurn && gp.isFinishedTurn()){
				for(int i = 0; i< finishedPlayer.size();i++){
					if(playerTurn == finishedPlayer.get(i)){
						playerTurn = (playerTurn + 1)%numberOfPlayer;
						i = 0;
					}
				}
				gp.updateTokens();
				if(!showFlag){
					if(playerTurn == 0){
						gp.showPlayerTurn(players.get(0).getName(),0);
						showFlag = true;
					}else if(playerTurn == 1){
						gp.showPlayerTurn(players.get(1).getName(),1);
						showFlag = true;
					}else if(playerTurn == 2){
						gp.showPlayerTurn(players.get(2).getName(),2);
						showFlag = true;
					}else if(playerTurn == 3){
						gp.showPlayerTurn(players.get(3).getName(),3);
						showFlag = true;
					}
				}
				if(gp.isRolled()){
					boolean flag = mm.isAvailable(playerTurn, gp.getBoard(), gp.getBoard().getDie().getfaceValue());
					if(flag == false){
						gp.updateTokens();
						showFlag = false;
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
			if(!isFinishedTurn && gp.isFinishedTurn()){
				if(gp.isSelected()){
					boolean flag = false;
					int[] clicked = gp.getClicked_token();
					if(clicked[0] == 2){
						flag = mm.move(clicked[1],gp.getBoard(),-1,gp.getBoard().getDie().getfaceValue(),0);
					}
					else if(clicked[0] == 0){
						flag = mm.move(clicked[1],gp.getBoard(),clicked[2],gp.getBoard().getDie().getfaceValue(),clicked[3]);
						gp.playTokenAnimation(0);
					}
					else if(clicked[0] == 1){
						flag = mm.move(clicked[1],gp.getBoard(),clicked[2]+52,gp.getBoard().getDie().getfaceValue(),clicked[3]);
						gp.playTokenAnimation(1);
					}
					if(flag == true){
						boolean check = fm.endPlayer(gp.getBoard().getEndingSlots()[playerTurn]);
						if(check){
							finishedPlayer.add(playerTurn);
						}
						mm.makeUnAvaliable(gp.getBoard());
						showFlag = false;
						gp.setRolled(false);
						if(gp.getBoard().getDie().getfaceValue() != 6){
							playerTurn = (playerTurn + 1)%numberOfPlayer;
						}
						if(finishedPlayer.size() == numberOfPlayer-1){
							for(int i = 0; i < finishedPlayer.size();i++){
								players.get(finishedPlayer.get(i)).setPoint(numberOfPlayer-i-1);
								System.out.println(players.get(0).getPoint());
							}
							rollWaiter.stop();
							clickWaiter.stop();
							try {
								filem.writeFile(filem.update(players, filem.readFile()));
							} catch (FileNotFoundException e) {
								
							} catch (IOException e) {
								
								
							}
							finally{
								MainMenu mm = new MainMenu();
								mm.start(primary);
							}
						}
						isFinishedTurn = true;
					}
					else{
						gp.updateTokens();
					}
				}
			}
			
		}
		
	}

}
