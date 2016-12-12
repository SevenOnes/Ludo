import java.util.ArrayList;

import GameEntities.Board;
import GameEntities.House;
import GameEntities.Slot;
import GameEntities.Token;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GamePanel extends Application {
	DropShadow shadow = new DropShadow();
	private Group house_red;
	private Group house_yellow;
	private Group house_green;
	private Group house_blue;

	private Board board = new Board(1);
	private Group slots;

	private Group[] endingSlots;
	Group root = new Group();
	private Group house_red_tokens = new Group();
	private Group house_yellow_tokens = new Group();
	private Group house_green_tokens = new Group();
	private Group house_blue_tokens = new Group();

	private Group slot_tokens = new Group();
	private Group endingSlot_tokens = new Group();
	
	final int TOKENINHOUSE = 4;
	@Override
	public void start(Stage primaryStage) {

		Scene scene = new Scene(root, 1200, 800, Color.WHITE);
		primaryStage.setScene(scene);	

		//BACKGROUND OF HOUSE
		/*	Group backgrounds = new Group();
		Rectangle red_house_back = new Rectangle(215,225,Color.ALICEBLUE);
		red_house_back.setStroke(Color.RED);
		red_house_back.setStrokeWidth(value);
		red_house_back.setX(50);
		red_house_back.setY(50);
		backgrounds.getChildren().add(red_house_back);
		root.getChildren().add(backgrounds);*/


		// HOUSE DRAWING INITIAL
		house_red = new Group();
		house_yellow = new Group();
		house_green = new Group();
		house_blue = new Group();
		for(int i = 0; i < TOKENINHOUSE; i++){
			Circle circle = new Circle(30,Color.RED);
			circle.setOpacity(0.5);
			if(i == 0){
				circle.setLayoutX(110);
				circle.setLayoutY(115);
			}else if(i == 1){
				circle.setLayoutX(205);
				circle.setLayoutY(115);
			}else if(i == 2){
				circle.setLayoutX(110);
				circle.setLayoutY(210);
			}else{
				circle.setLayoutX(205);
				circle.setLayoutY(210);
			}
			circle.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(shadow);
				}
			});


			circle.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(null);
				}});
			house_red.getChildren().add(circle);

		}

		for(int i = 0; i < TOKENINHOUSE; i++){
			Circle circle = new Circle(30,Color.YELLOW);
			circle.setOpacity(0.5);
			if(i == 0){
				circle.setLayoutX(110 + 475);
				circle.setLayoutY(115);
			}else if(i == 1){
				circle.setLayoutX(205 + 475);
				circle.setLayoutY(115);
			}else if(i == 2){
				circle.setLayoutX(110 + 475);
				circle.setLayoutY(210);
			}else{
				circle.setLayoutX(205 + 475);
				circle.setLayoutY(210);
			}
			circle.setEffect(shadow);
			house_yellow.getChildren().add(circle);
		}

		for(int i = 0; i < TOKENINHOUSE; i++){
			Circle circle = new Circle(30,Color.BLUE);
			circle.setOpacity(0.5);
			if(i == 0){
				circle.setLayoutY(115 + 475);
				circle.setLayoutX(110);
			}else if(i == 1){
				circle.setLayoutY(210 + 475);
				circle.setLayoutX(110);
			}else if(i == 2){
				circle.setLayoutY(115 + 475);
				circle.setLayoutX(205);
			}else{
				circle.setLayoutY(210 + 475);
				circle.setLayoutX(205);
			}
			circle.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(shadow);
				}
			});


			circle.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(null);
				}
			});
			house_blue.getChildren().add(circle);
		}

		for(int i = 0; i < TOKENINHOUSE; i++){
			Circle circle = new Circle(30,Color.GREEN);
			circle.setOpacity(0.5);
			if(i == 0){
				circle.setLayoutX(110 + 475);
				circle.setLayoutY(115 + 475);
			}else if(i == 1){
				circle.setLayoutX(205 + 475);
				circle.setLayoutY(115 + 475);
			}else if(i == 2){
				circle.setLayoutX(110 + 475);
				circle.setLayoutY(210 + 475);
			}else{
				circle.setLayoutX(205 + 475);
				circle.setLayoutY(210 + 475);
			}
			circle.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(shadow);
				}
			});


			circle.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(null);
				}
			});
			house_green.getChildren().add(circle);
		}

		// SLOTS INITIAL DRAWING
		slots = new Group();
		int x = 15;
		int y = 325;
		for(int i = 0; i < 56 ; i++){
			final Rectangle rect;

			// initial point
			if(i == 1){
				rect = new Rectangle(50,50,Color.RED);
			}else if(i == 15){
				rect = new Rectangle(50,50,Color.YELLOW);
			}else if(i == 29){
				rect = new Rectangle(50,50,Color.GREEN);
			}else if(i == 43){
				rect = new Rectangle(50,50,Color.BLUE);
			}else{
				rect = new Rectangle(50,50,Color.GREY);
			}

			rect.setOpacity(0.3);
			rect.setArcHeight(15);
			rect.setX(x);
			rect.setY(y);
			rect.setArcWidth(15);
			rect.setStroke(Color.BLACK);
			rect.addEventHandler(MouseEvent.MOUSE_ENTERED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					rect.setEffect(shadow);
				}
			});


			rect.addEventHandler(MouseEvent.MOUSE_EXITED,
					new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					rect.setEffect(null);
				}});
			// for mapping
			if(i < 6){
				x += 50;
			}else if(i < 12){
				y -= 50;
			}else if(i < 14){
				x += 50;
			}else if(i < 20){
				y += 50;
			}else if(i < 26){
				x += 50;
			}else if(i < 28){
				y += 50;
			}else if(i < 34){
				x -= 50;
			}else if(i < 40){
				y += 50;
			}else if(i < 42){
				x -= 50;
			}else if(i < 48){
				y -= 50;
			}else if(i < 54){
				x -= 50;
			}else if(i < 56){
				y -= 50;
			}

			if(!(i == 6 || i == 20 	|| i == 34 || i == 48)){

				slots.getChildren().add(rect);
			}
		}

		// ENDING SLOT DRAWING
		endingSlots = new Group[4];
		x = 65;
		y = 375;
		for(int i = 0; i < 4 ; i++){
			endingSlots[i] = new Group();
			for(int k = 0; k < 5 ; k++){
				final Rectangle rect;
				if(i == 0){
					rect = new Rectangle(50,50,Color.RED);
				}else if(i == 1){
					rect = new Rectangle(50,50,Color.YELLOW);
				}else if(i == 2){
					rect = new Rectangle(50,50,Color.GREEN);
				}else {
					rect = new Rectangle(50,50,Color.BLUE);
				}
				rect.setOpacity(0.3);
				rect.setArcHeight(15);
				rect.setX(x);
				rect.setY(y);
				rect.setArcWidth(15);
				rect.setStroke(Color.BLACK);
				if(i == 0){
					x += 50;
				}else if(i == 1){
					y += 50;
				}else if(i == 2){
					x -= 50;
				}else if(i == 3){
					y -= 50;
				}
				rect.addEventHandler(MouseEvent.MOUSE_ENTERED,
						new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						rect.setEffect(shadow);
					}
				});


				rect.addEventHandler(MouseEvent.MOUSE_EXITED,
						new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						rect.setEffect(null);
					}});
				endingSlots[i].getChildren().add(rect);
			}
			if(i == 0){
				y = 75;
				x += 50;
			}else if(i == 1){
				x = 665;
				y += 50;
			}else if(i == 2){
				y = 675;
				x -= 50;
			}
		}


		// adding into root group
		for(int i = 0; i < endingSlots.length; i++){
			root.getChildren().add(endingSlots[i]);
		}

		root.getChildren().add(slots);
		root.getChildren().add(house_green);
		root.getChildren().add(house_blue);
		root.getChildren().add(house_red);
		root.getChildren().add(house_yellow);
		Token tmp = board.getHouses().get(1).retriveToken();
		Slot[] tmp2 = board.getSlots();
		tmp2[1].setInsideToken(tmp);
		tmp = board.getHouses().get(0).retriveToken();
		tmp2[2].setInsideToken(tmp);
		tmp = board.getHouses().get(1).retriveToken();
		tmp2[3].setInsideToken(tmp);
		tmp = board.getHouses().get(2).retriveToken();
		tmp2[4].setInsideToken(tmp);
		tmp = board.getHouses().get(3).retriveToken();
		tmp2[5].setInsideToken(tmp);
		tmp = board.getHouses().get(1).retriveToken();
		board.getEndingSlots()[1][2].setInsideToken(tmp);
		updateTokens();
		
	}
	public void updateTokens(){
		updateHouses();
		updateSlots();
		updateEndingSlots();
	}

	public void updateHouses(){
		int x = 110;
		int y = 115;

		ArrayList<House> houses = board.getHouses();
		for(int i = 0; i < houses.size() ;i++){//
			if(i == 0){
				if(root.getChildren().contains(house_red_tokens)){
					int index = root.getChildren().indexOf(house_red_tokens);
					root.getChildren().remove(index);
				}
			}else if(i == 1){
				if(root.getChildren().contains(house_yellow_tokens)){
					int index = root.getChildren().indexOf(house_red_tokens);
					root.getChildren().remove(index);
				}
			}else if(i == 2){
				if(root.getChildren().contains(house_green_tokens)){
					int index = root.getChildren().indexOf(house_red_tokens);
					root.getChildren().remove(index);
				}
			}else if(i == 3){
				if(root.getChildren().contains(house_blue_tokens)){
					int index = root.getChildren().indexOf(house_red_tokens);
					root.getChildren().remove(index);
				}
			}
			int numberOfToken = houses.get(i).getTokens().size();
			for(int k = 0; k < numberOfToken ; k++){

				//Token Drawing
				Circle circle;

				//Color
				if(i == 0){
					circle = new Circle(15,Color.RED);
				}else if(i == 1){
					circle = new Circle(15,Color.YELLOW);
				}else if(i == 2){
					circle = new Circle(15,Color.GREEN);
				}else {
					circle = new Circle(15,Color.BLUE);
				}
				//initial 
				circle.setLayoutX(x);
				circle.setLayoutY(y);
				//Post ++
				if(k == 0){
					x += 95;
				}else if(k == 1){
					x -= 95;
					y += 95;
				}else if(k == 2){
					x += 95;
				}else if(k == 3){
					x -= 95;
					y -= 95;
				}
				if(i == 0){
					house_red_tokens.getChildren().add(circle);
				}else if(i == 1){
					house_yellow_tokens.getChildren().add(circle);
				}else if(i == 2){
					house_green_tokens.getChildren().add(circle);
				}else if(i == 3){
					house_blue_tokens.getChildren().add(circle);
				}


			}
			if(i == 0){
				root.getChildren().add(house_red_tokens);
				x = 585;
				y = 115;
			}
			else if(i == 1){
				root.getChildren().add(house_yellow_tokens);
				x = 585;
				y = 590;
			}
			else if(i == 2){
				root.getChildren().add(house_green_tokens);
				x = 110;
				y = 590;
			}
			else if(i == 3){
				root.getChildren().add(house_blue_tokens);
			}
		}


	}

	public void updateSlots(){
		if(root.getChildren().contains(slot_tokens)){
			int index = root.getChildren().indexOf(slot_tokens);
			root.getChildren().remove(index);
		}
		
		// SLOTS INITIAL DRAWING
		slot_tokens = new Group();
		int x = 90;
		int y = 350;
		int index = 0;
		for(int i = 0; i < 56 ; i++){
			Circle circle = null;
			Slot[] arr = board.getSlots();
			if(arr[index].isOccupied()){
				if(arr[index].getInsideToken().getColor().equals("Red"))
					circle = new Circle(15,Color.RED);
				else if(arr[index].getInsideToken().getColor().equals("Yellow"))
					circle = new Circle(15,Color.YELLOW);
				if(arr[index].getInsideToken().getColor().equals("Green"))
					circle = new Circle(15,Color.GREEN);
				if(arr[index].getInsideToken().getColor().equals("Blue"))
					circle = new Circle(15,Color.BLUE);
				circle.setLayoutX(x);
				circle.setLayoutY(y);
			}
			// for mapping
			if(i < 5){
				x += 50;
			}else if(i < 11){
				y -= 50;
			}else if(i < 13){
				x += 50;
			}else if(i < 19){
				y += 50;
			}else if(i < 25){
				x += 50;
			}else if(i < 27){
				y += 50;
			}else if(i < 33){
				x -= 50;
			}else if(i < 39){
				y += 50;
			}else if(i < 41){
				x -= 50;
			}else if(i < 47){
				y -= 50;
			}else if(i < 53){
				x -= 50;
			}else if(i < 56){
				y -= 50;
			}

			if(!(i == 5 || i == 19 	|| i == 33 || i == 47)){
				index++;
				if(circle != null)
					slot_tokens.getChildren().add(circle);
			}
		}
		root.getChildren().add(slot_tokens);
	}

	public void updateEndingSlots(){
		if(root.getChildren().contains(endingSlot_tokens)){
			int index = root.getChildren().indexOf(endingSlot_tokens);
			root.getChildren().remove(index);
		}
		int x = 90;
		int y = 400;
		for(int i = 0; i < 4 ; i++){
			for(int k = 0 ; k < 5; k++){
				Circle circle = null ;
				if(board.getEndingSlots()[i][k].isOccupied()){
					if(board.getEndingSlots()[i][k].getInsideToken().getColor().equals("Red"))
						circle = new Circle(15,Color.RED);
					else if(board.getEndingSlots()[i][k].getInsideToken().getColor().equals("Yellow"))
						circle = new Circle(15,Color.YELLOW);
					else if(board.getEndingSlots()[i][k].getInsideToken().getColor().equals("Green"))
						circle = new Circle(15,Color.GREEN);
					else if(board.getEndingSlots()[i][k].getInsideToken().getColor().equals("Blue"))
						circle = new Circle(15,Color.BLUE);
					circle.setLayoutX(x);
					circle.setLayoutY(y);
				}
				if(i == 0){
					x += 50;
				}else if(i == 1){
					y += 50;
				}else if(i == 2){
					x -= 50;
				}else if(i == 3){
					y -= 50;
				}
				if(circle != null)
					endingSlot_tokens.getChildren().add(circle);
			}
			if(i == 0){
				x += 50;
				y = 100;
			}else if(i == 1){
				x += 300;
				y += 50;
			}else if(i == 2){
				x -= 50;
				y += 300;
			}
			
		}
		root.getChildren().add(endingSlot_tokens);
	}
}