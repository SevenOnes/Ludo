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

	private Group slots;
	
	private Group[] endingSlots;

	final int TOKENINHOUSE = 4;
	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene scene = new Scene(root, 1200, 800, Color.GHOSTWHITE);
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
				circle.setLayoutX(115 + 475);
				circle.setLayoutY(205);
			}else{
				circle.setLayoutX(205 + 475);
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
	                  }
	                });
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

	}

	public void update(){

	}

}
