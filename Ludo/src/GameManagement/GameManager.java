package GameManagement;

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
	private TxtAdapter filem;
	private double scale;
	private ArrayList<Player> players;
	private Stage primary;
	boolean isFinishedTurn ;
	int playerTurn;
	int numberOfPlayer;
	Stage primaryStage;
	Timeline rollWaiter;
	Timeline clickWaiter;
	boolean showFlag;
	boolean isMoved;

	ArrayList<Integer> finishedPlayers ;
	public GameManager(double scale, Stage primaryStage,ArrayList<Player> players, SoundManager smanager){
		super();
		smanager.getMediaPlayer().stop();
		this.scale = scale;
		mm = new MovementManager();
		fm = new FinishManager();
		sm = new SoundManager(2);
		filem = new TxtAdapter();

		finishedPlayers = new ArrayList<Integer>();
		this.players = players;
		primary = primaryStage;
		gp = new GamePanel(scale,this);
		gp.start(primary);
		isFinishedTurn = true;
		playerTurn = 0;
		numberOfPlayer = players.size();
		isMoved = false;
		showFlag = false;
		rollWaiter = new Timeline(new KeyFrame(Duration.seconds(0.3), new RolledWaiter()));
		rollWaiter.setCycleCount(Timeline.INDEFINITE);
		rollWaiter.play();
		clickWaiter = new Timeline(new KeyFrame(Duration.seconds(0.3), new ClickWaiter()));
		clickWaiter.setCycleCount(Timeline.INDEFINITE);
		clickWaiter.play();

	}

	public void test(){
		/*for(int i = 0;i < gp.getBoard().getHouses().get(1).getTokens().size();){

		}*/
		Token tmp = gp.getBoard().getHouses().get(1).retriveToken();
		gp.getBoard().getEndingSlots()[1][4].addInsideToken(tmp);
		tmp = gp.getBoard().getHouses().get(1).retriveToken();
		gp.getBoard().getEndingSlots()[1][5].addInsideToken(tmp);
	}

	public void run() {
	//	test();


	}

	private class RolledWaiter implements EventHandler{


		public RolledWaiter(){

		}
		@Override
		public void handle(Event arg0) {
			if(isFinishedTurn && gp.isAnimeFinished()){
				for(int i = 0; i< finishedPlayers.size();i++){
					if(playerTurn == finishedPlayers.get(i)){
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
			if(!isFinishedTurn && gp.isAnimeFinished()){
				if(gp.isSelected()){
					boolean flag = false;
					int[] clicked = gp.getClicked_token();
					if(clicked[0] == 2){
						flag = mm.move(clicked[1],gp.getBoard(),-1,gp.getBoard().getDie().getfaceValue(),0);
					}
					else if(clicked[0] == 0){
						flag = mm.move(clicked[1],gp.getBoard(),clicked[2],gp.getBoard().getDie().getfaceValue(),clicked[3]);
						if(flag == true)
							gp.playTokenAnimation(0);
					}
					else if(clicked[0] == 1){
						flag = mm.move(clicked[1],gp.getBoard(),clicked[2]+52,gp.getBoard().getDie().getfaceValue(),clicked[3]);
						if(flag == true)
							gp.playTokenAnimation(1);
					}
					if(flag == true){
						boolean check = fm.endPlayer(gp.getBoard().getEndingSlots()[playerTurn]);
						if(check){
							finishedPlayers.add(playerTurn);
						}
						mm.makeUnAvaliable(gp.getBoard());
						showFlag = false;
						gp.setRolled(false);
						if(gp.getBoard().getDie().getfaceValue() != 6){
							playerTurn = (playerTurn + 1)%numberOfPlayer;
						}
						if(finishedPlayers.size() == numberOfPlayer-1){
							for(int i = 0; i < finishedPlayers.size();i++){
								players.get(finishedPlayers.get(i)).setPoint(numberOfPlayer-i-1);
								//System.out.println(players.get(0).getPoint());
							}
							rollWaiter.stop();
							clickWaiter.stop();
							try {
								filem.writeFile(filem.update(players, filem.readFile()));
							}catch(Exception e){

							}
							finally{
								sm.getMediaPlayer().stop();
								gp.playEndAnimation(players,finishedPlayers);
								SoundManager.endGame();
								if(gp.isEndingAnimation()){
									MainMenu mm = new MainMenu();
									mm.start(primary);
								}
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
