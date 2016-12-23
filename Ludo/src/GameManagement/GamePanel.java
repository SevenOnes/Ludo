package GameManagement;
import java.util.ArrayList;

import GameEntities.Board;
import GameEntities.House;
import GameEntities.Slot;
import GameEntities.Token;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePanel extends Application {
	DropShadow shadow = new DropShadow();
	private Group house_red;
	private Group house_yellow;
	private Group house_green;
	private Group house_blue;

	private Board board ;
	private Group slots;

	private double scale;
	private Group[] endingSlots;
	Group root = new Group();
	Group big_root = new Group();
	Group effect_group = new Group();
	private Group house_red_tokens;
	private Group house_yellow_tokens;
	private Group house_green_tokens;
	private Group house_blue_tokens;
	private Group slot_tokens;
	private Group endingSlot_tokens;

	private Group die_group;

	private int[] clicked_token;

	final int TOKENINHOUSE = 4;

	GameManager gm;
	Timeline playerTurnAnimation;
	Timeline rollAnime;
	Timeline animeOfMovement;
	Group turnGroup = new Group();
	boolean animeFinished;
	Image[] dieImages;
	ImageView die;

	boolean clicked;
	boolean selected;
	boolean rolled;
	boolean smallAnime;

	public GamePanel(double scale,GameManager gm){

		board = new Board(1);
		/*this.lock1 = lock1;
		this.lock2 = lock2;*/
		this.scale = scale;
		rolled = false;
		root = new Group();
		big_root = new Group();
		effect_group = new Group();
		house_red_tokens = new Group();
		house_yellow_tokens = new Group();
		house_green_tokens = new Group();
		house_blue_tokens = new Group();

		slot_tokens = new Group();
		endingSlot_tokens = new Group();

		die_group = new Group();

		clicked_token = new int[4];
		clicked = false;
		selected = false;
		this.gm = gm;
		animeFinished = true;
		dieImages = new Image[6];
		for(int i = 0; i < dieImages.length;i++){
			String str = "file:die_black_"+(i+1)+".png";
			dieImages[i] = new Image(str,116*scale,120*scale,true, true);
		}

	}
	public void start(Stage primaryStage) {

		Scene scene = new Scene(big_root, 1200 * scale, 800 * scale, Color.WHITE);
		primaryStage.setScene(scene);

		Rectangle closeBtn = new Rectangle(30 * scale, 30 * scale, Color.RED);

		Rectangle backGround = new Rectangle(1200 * scale, 800 * scale, Color.WHEAT);
		root.getChildren().add(backGround);

		// BACKGROUND OF HOUSE
		/*
		 * Group backgrounds = new Group(); Rectangle red_house_back = new
		 * Rectangle(215,225,Color.ALICEBLUE);
		 * red_house_back.setStroke(Color.RED);
		 * red_house_back.setStrokeWidth(value); red_house_back.setX(50);
		 * red_house_back.setY(50);
		 * backgrounds.getChildren().add(red_house_back);
		 * root.getChildren().add(backgrounds);
		 */

		// HOUSE DRAWING INITIAL
		house_red = new Group();
		house_yellow = new Group();
		house_green = new Group();
		house_blue = new Group();
		for (int i = 0; i < TOKENINHOUSE; i++) {
			Circle circle = new Circle(30 * scale, Color.RED);
			circle.setOpacity(0.5);
			if (i == 0) {
				circle.setLayoutX(110 * scale);
				circle.setLayoutY(115 * scale);
			} else if (i == 1) {
				circle.setLayoutX(205 * scale);
				circle.setLayoutY(115 * scale);
			} else if (i == 2) {
				circle.setLayoutX(110 * scale);
				circle.setLayoutY(210 * scale);
			} else {
				circle.setLayoutX(205 * scale);
				circle.setLayoutY(210 * scale);
			}
			circle.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(shadow);
				}
			});

			circle.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(null);
				}
			});
			house_red.getChildren().add(circle);

		}

		for (int i = 0; i < TOKENINHOUSE; i++) {
			Circle circle = new Circle(30 * scale, Color.YELLOW);
			circle.setOpacity(0.5);
			if (i == 0) {
				circle.setLayoutX((110 + 475) * scale);
				circle.setLayoutY(115 * scale);
			} else if (i == 1) {
				circle.setLayoutX((205 + 475) * scale);
				circle.setLayoutY(115 * scale);
			} else if (i == 2) {
				circle.setLayoutX((110 + 475) * scale);
				circle.setLayoutY(210 * scale);
			} else {
				circle.setLayoutX((205 + 475) * scale);
				circle.setLayoutY(210 * scale);
			}
			circle.setEffect(shadow);
			house_yellow.getChildren().add(circle);
		}

		for (int i = 0; i < TOKENINHOUSE; i++) {
			Circle circle = new Circle(30 * scale, Color.BLUE);
			circle.setOpacity(0.5);
			if (i == 0) {
				circle.setLayoutY((115 + 475) * scale);
				circle.setLayoutX(110 * scale);
			} else if (i == 1) {
				circle.setLayoutY((210 + 475) * scale);
				circle.setLayoutX(110 * scale);
			} else if (i == 2) {
				circle.setLayoutY((115 + 475) * scale);
				circle.setLayoutX(205 * scale);
			} else {
				circle.setLayoutY((210 + 475) * scale);
				circle.setLayoutX(205 * scale);
			}
			circle.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(shadow);
				}
			});

			circle.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(null);
				}
			});
			house_blue.getChildren().add(circle);
		}

		for (int i = 0; i < TOKENINHOUSE; i++) {
			Circle circle = new Circle(30 * scale, Color.GREEN);
			circle.setOpacity(0.5);
			if (i == 0) {
				circle.setLayoutX((110 + 475) * scale);
				circle.setLayoutY((115 + 475) * scale);
			} else if (i == 1) {
				circle.setLayoutX((205 + 475) * scale);
				circle.setLayoutY((115 + 475) * scale);
			} else if (i == 2) {
				circle.setLayoutX((110 + 475) * scale);
				circle.setLayoutY((210 + 475) * scale);
			} else {
				circle.setLayoutX((205 + 475) * scale);
				circle.setLayoutY((210 + 475) * scale);
			}
			circle.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(shadow);
				}
			});

			circle.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					circle.setEffect(null);
				}
			});
			house_green.getChildren().add(circle);
		}

		// SLOTS INITIAL DRAWING
		slots = new Group();
		double x = 15 * scale;
		double y = 325 * scale;
		for (int i = 0; i < 56; i++) {
			final Rectangle rect;

			// initial point
			if (i == 1) {
				rect = new Rectangle(50 * scale, 50 * scale, Color.RED);
			} else if (i == 15) {
				rect = new Rectangle(50 * scale, 50 * scale, Color.YELLOW);
			} else if (i == 29) {
				rect = new Rectangle(50 * scale, 50 * scale, Color.GREEN);
			} else if (i == 43) {
				rect = new Rectangle(50 * scale, 50 * scale, Color.BLUE);
			} else {
				rect = new Rectangle(50 * scale, 50 * scale, Color.GREY);
			}

			rect.setOpacity(0.3);
			rect.setArcHeight(15);
			rect.setX(x);
			rect.setY(y);
			rect.setArcWidth(15 * scale);
			rect.setStroke(Color.BLACK);
			rect.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					rect.setEffect(shadow);
				}
			});

			rect.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					rect.setEffect(null);
				}
			});
			// for mapping
			if (i < 6) {
				x += 50 * scale;
			} else if (i < 12) {
				y -= 50 * scale;
			} else if (i < 14) {
				x += 50 * scale;
			} else if (i < 20) {
				y += 50 * scale;
			} else if (i < 26) {
				x += 50 * scale;
			} else if (i < 28) {
				y += 50 * scale;
			} else if (i < 34) {
				x -= 50 * scale;
			} else if (i < 40) {
				y += 50 * scale;
			} else if (i < 42) {
				x -= 50 * scale;
			} else if (i < 48) {
				y -= 50 * scale;
			} else if (i < 54) {
				x -= 50 * scale;
			} else if (i < 56) {
				y -= 50 * scale;
			}

			if (!(i == 6 || i == 20 || i == 34 || i == 48)) {

				slots.getChildren().add(rect);
			}
		}

		// ENDING SLOT DRAWING
		endingSlots = new Group[4];
		x = 65 * scale;
		y = 375 * scale;
		for (int i = 0; i < 4; i++) {
			endingSlots[i] = new Group();
			for (int k = 0; k < 5; k++) {
				final Rectangle rect;
				if (i == 0) {
					rect = new Rectangle(50 * scale, 50 * scale, Color.RED);
				} else if (i == 1) {
					rect = new Rectangle(50 * scale, 50 * scale, Color.YELLOW);
				} else if (i == 2) {
					rect = new Rectangle(50 * scale, 50 * scale, Color.GREEN);
				} else {
					rect = new Rectangle(50 * scale, 50 * scale, Color.BLUE);
				}
				rect.setOpacity(0.3);
				rect.setArcHeight(15 * scale);
				rect.setX(x);
				rect.setY(y);
				rect.setStroke(Color.BLACK);
				if (i == 0) {
					x += 50 * scale;
				} else if (i == 1) {
					y += 50 * scale;
				} else if (i == 2) {
					x -= 50 * scale;
				} else if (i == 3) {
					y -= 50 * scale;
				}
				rect.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						rect.setEffect(shadow);
					}
				});

				rect.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						rect.setEffect(null);
					}
				});
				endingSlots[i].getChildren().add(rect);
			}
			if (i == 0) {
				y = 75 * scale;
				x += 50 * scale;
			} else if (i == 1) {
				x = 665 * scale;
				y += 50 * scale;
			} else if (i == 2) {
				y = 675 * scale;
				x -= 50 * scale;
			}
		}

		// adding into root group
		for (int i = 0; i < endingSlots.length; i++) {
			root.getChildren().add(endingSlots[i]);
		}

		root.getChildren().add(slots);
		root.getChildren().add(house_green);
		root.getChildren().add(house_blue);
		root.getChildren().add(house_red);
		root.getChildren().add(house_yellow);
		/*Token tmp = board.getHouses().get(1).retriveToken();
		Slot[] tmp2 = board.getSlots();
		tmp2[0].addInsideToken(tmp);
		tmp = board.getHouses().get(0).retriveToken();
		tmp2[0].addInsideToken(tmp);
		tmp = board.getHouses().get(1).retriveToken();
		tmp2[0].addInsideToken(tmp);
		tmp = board.getHouses().get(2).retriveToken();
		tmp2[0].addInsideToken(tmp);
		tmp = board.getHouses().get(3).retriveToken();
		tmp2[0].addInsideToken(tmp);
		tmp = board.getHouses().get(0).retriveToken();
		tmp2[2].addInsideToken(tmp);
		tmp = board.getHouses().get(0).retriveToken();
		tmp2[5].addInsideToken(tmp);
		tmp = board.getHouses().get(1).retriveToken();
		board.getEndingSlots()[1][2].addInsideToken(tmp);
		tmp = board.getHouses().get(1).retriveToken();
		board.getEndingSlots()[1][2].addInsideToken(tmp);*/
		updateTokens();
		root.getChildren().add(die_group);
		big_root.getChildren().add(root);
		big_root.getChildren().add(effect_group);

		closeBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Platform.exit();
			}
		});
		closeBtn.setLayoutX(1170 * scale);

		big_root.getChildren().add(closeBtn);

		Rectangle roll_die = new Rectangle(825*scale,350 *scale,300*scale,75*scale);
		roll_die.setArcWidth(20);
		roll_die.setArcHeight(20);
		die_group.getChildren().add(roll_die);
		Text t = new Text();
		t.setText("Roll a die");
		t.setFont(Font.font(20*scale));
		t.setFill(Color.WHITE);
		die_group.getChildren().add(t);
		t.setLayoutX(925*scale);
		t.setLayoutY(393*scale);

		die = new ImageView();
		die.setSmooth(true);
		die.setCache(true);
		root.getChildren().add(die);

		die_group.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(animeFinished){
					if(!isRolled()){

						rollAnime =  new Timeline(new KeyFrame(Duration.seconds(0.05), new rollAnimation()));
						rollAnime.setCycleCount(10);
						rollAnime.play();
					}
				}
			}
		});
		Platform.runLater(gm);

		big_root.getChildren().add(turnGroup);

	}
	public void updateTokens() {
		updateHouses();
		updateSlots();
		updateEndingSlots();
		for(int i = 0; i < clicked_token.length; i++){
			clicked_token[i] = -1;
		}

	}
	public void updateHouses() {
		double x = 110 * scale;
		double y = 115 * scale;

		ArrayList<House> houses = board.getHouses();
		for (int i = 0; i < houses.size(); i++) {//
			if (i == 0) {
				if (root.getChildren().contains(house_red_tokens)) {
					int index = root.getChildren().indexOf(house_red_tokens);
					Group group = (Group)root.getChildren().get(index);
					group.getChildren().clear();
					root.getChildren().remove(index);
				}
			} else if (i == 1) {
				if (root.getChildren().contains(house_yellow_tokens)) {
					int index = root.getChildren().indexOf(house_yellow_tokens);
					Group group = (Group)root.getChildren().get(index);
					group.getChildren().clear();
					root.getChildren().remove(index);
				}
			} else if (i == 2) {
				if (root.getChildren().contains(house_green_tokens)) {
					int index = root.getChildren().indexOf(house_green_tokens);
					Group group = (Group)root.getChildren().get(index);
					group.getChildren().clear();
					root.getChildren().remove(index);
				}
			} else if (i == 3) {
				if (root.getChildren().contains(house_blue_tokens)) {
					int index = root.getChildren().indexOf(house_blue_tokens);
					Group group = (Group)root.getChildren().get(index);
					group.getChildren().clear();
					root.getChildren().remove(index);
				}
			}
			int numberOfToken = houses.get(i).getTokens().size();
			for (int k = 0; k < numberOfToken; k++) {

				// Token Drawing
				Circle circle;

				// Color
				if (i == 0) {
					circle = new Circle(15 * scale, Color.RED);
				} else if (i == 1) {
					circle = new Circle(15 * scale, Color.YELLOW);
				} else if (i == 2) {
					circle = new Circle(15 * scale, Color.GREEN);
				} else {
					circle = new Circle(15 * scale, Color.BLUE);
				}
				if(houses.get(i).getTokens().get(k).isAvailable()){
					circle.setEffect(shadow);
				}
				// initial
				circle.setLayoutX(x);
				circle.setLayoutY(y);
				// Post ++
				if (k == 0) {
					x += 95 * scale;
				} else if (k == 1) {
					x -= 95 * scale;
					y += 95 * scale;
				} else if (k == 2) {
					x += 95 * scale;
				} else if (k == 3) {
					x -= 95 * scale;
					y -= 95 * scale;
				}
				if (i == 0) {
					house_red_tokens.getChildren().add(circle);
					circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							clicked_token[0] = 2;
							clicked_token[1] = 0;
							clicked_token[2] = 0;
							clicked_token[3] = 0;
						}
					});
				} else if (i == 1) {
					house_yellow_tokens.getChildren().add(circle);
					circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							clicked_token[0] = 2;
							clicked_token[1] = 1;
							clicked_token[2] = 0;
							clicked_token[3] = 0;
						}
					});
				} else if (i == 2) {
					house_green_tokens.getChildren().add(circle);
					circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							clicked_token[0] = 2;
							clicked_token[1] = 2;
							clicked_token[2] = 0;
							clicked_token[3] = 0;
						}
					});
				} else if (i == 3) {
					house_blue_tokens.getChildren().add(circle);
					circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							clicked_token[0] = 2;
							clicked_token[1] = 3;
							clicked_token[2] = 0;
							clicked_token[3] = 0;
						}
					});
				}

			}
			if (i == 0) {
				root.getChildren().add(house_red_tokens);
				x = 585 * scale;
				y = 115 * scale;
			} else if (i == 1) {
				root.getChildren().add(house_yellow_tokens);
				x = 585 * scale;
				y = 590 * scale;
			} else if (i == 2) {
				root.getChildren().add(house_green_tokens);
				x = 110 * scale;
				y = 590 * scale;
			} else if (i == 3) {
				root.getChildren().add(house_blue_tokens);
			}
		}

	}
	public void updateSlots() {
		if (root.getChildren().contains(slot_tokens)) {
			int index = root.getChildren().indexOf(slot_tokens);
			root.getChildren().remove(index);
		}

		// SLOTS INITIAL DRAWING
		slot_tokens = new Group();
		double x = 90 * scale;
		double y = 350 * scale;
		int index = 0;
		for (int i = 0; i < 56; i++) {
			Circle circle = null;
			Slot[] arr = board.getSlots();
			if (arr[index].isOccupied()) {
				for (int k = 0; k < arr[index].getInsideToken().size(); k++) {
					int player = -1;
					if (arr[index].getInsideToken().get(k).getColor().equals("Red")){
						circle = new Circle(15 * scale, Color.RED);
						player = 0;				
					}

					else if (arr[index].getInsideToken().get(k).getColor().equals("Yellow")){
						circle = new Circle(15 * scale, Color.YELLOW);
						player = 1;
					}

					if (arr[index].getInsideToken().get(k).getColor().equals("Green")){
						circle = new Circle(15 * scale, Color.GREEN);
						player = 2;
					}

					if (arr[index].getInsideToken().get(k).getColor().equals("Blue")){
						circle = new Circle(15 * scale, Color.BLUE);
						player = 3;
					}
					if(arr[index].getInsideToken().get(k).isAvailable()){
						circle.setEffect(shadow);
					}
					circle.setId(index+","+ arr[index].getInsideToken().get(k).getColor());
					circle.setLayoutX(x);
					circle.setLayoutY(y);
					circle.addEventHandler(MouseEvent.MOUSE_CLICKED,
							new popUpTokens( arr[index].getInsideToken(), 0, player, index, x, y, (Color)circle.getFill()));
					if (circle != null)
						slot_tokens.getChildren().add(circle);

				}
				
			}
			if (!(i == 5 || i == 19 || i == 33 || i == 47)) {
				index++;	
			}
			// for mapping
			if (i < 5) {
				x += 50 * scale;
			} else if (i < 11) {
				y -= 50 * scale;
			} else if (i < 13) {
				x += 50 * scale;
			} else if (i < 19) {
				y += 50 * scale;
			} else if (i < 25) {
				x += 50 * scale;
			} else if (i < 27) {
				y += 50 * scale;
			} else if (i < 33) {
				x -= 50 * scale;
			} else if (i < 39) {
				y += 50 * scale;
			} else if (i < 41) {
				x -= 50 * scale;
			} else if (i < 47) {
				y -= 50 * scale;
			} else if (i < 53) {
				x -= 50 * scale;
			} else if (i < 56) {
				y -= 50 * scale;
			}
		}
		root.getChildren().add(slot_tokens);
	}
	public void updateEndingSlots() {
		if (root.getChildren().contains(endingSlot_tokens)) {
			int index = root.getChildren().indexOf(endingSlot_tokens);
			Group group = (Group)root.getChildren().get(index);
			group.getChildren().clear();
			root.getChildren().remove(index);
		}
		double x = 90 * scale;
		double y = 400 * scale;
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 5; k++) {
				Circle circle = null;
				if (board.getEndingSlots()[i][k].isOccupied()) {
					for (int l = 0; l < board.getEndingSlots()[i][k].getInsideToken().size(); l++) {
						if (board.getEndingSlots()[i][k].getInsideToken().get(l).getColor().equals("Red"))
							circle = new Circle(15 * scale, Color.RED);
						else if (board.getEndingSlots()[i][k].getInsideToken().get(l).getColor().equals("Yellow"))
							circle = new Circle(15 * scale, Color.YELLOW);					
						else if (board.getEndingSlots()[i][k].getInsideToken().get(l).getColor().equals("Green"))
							circle = new Circle(15 * scale, Color.GREEN);
						else if (board.getEndingSlots()[i][k].getInsideToken().get(l).getColor().equals("Blue"))
							circle = new Circle(15 * scale, Color.BLUE);
						circle.setLayoutX(x);
						circle.setLayoutY(y);
						circle.setId((k+52) + "," +board.getEndingSlots()[i][k].getInsideToken().get(l).getColor());
						if (l == board.getEndingSlots()[i][k].getInsideToken().size() - 1){
							circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new popUpTokens(
									board.getEndingSlots()[i][k].getInsideToken(),1 ,i,k, x, y, (Color) circle.getFill()));

						}
						if(board.getEndingSlots()[i][k].getInsideToken().get(l).isAvailable()){
							circle.setEffect(shadow);
						}
						if (circle != null)
							endingSlot_tokens.getChildren().add(circle);
					}
				}
				if (i == 0) {
					x += 50 * scale;
				} else if (i == 1) {
					y += 50 * scale;
				} else if (i == 2) {
					x -= 50 * scale;
				} else if (i == 3) {
					y -= 50 * scale;
				}

			}
			if (i == 0) {
				x += 50 * scale;
				y = 100 * scale;
			} else if (i == 1) {
				x += 300 * scale;
				y += 50 * scale;
			} else if (i == 2) {
				x -= 50 * scale;
				y += 300 * scale;
			}

		}
		root.getChildren().add(endingSlot_tokens);
	}
	private class popUpTokens implements EventHandler {

		ArrayList<Token> a;
		double x, y;
		Color c;
		double shift;
		int ending;
		int player;
		int pos;

		public popUpTokens(ArrayList<Token> a,int ending ,int player ,int pos , double x2, double y2, Color c) {
			this.a = a;
			this.x = x2;
			this.y = y2;
			this.c = c;
			this.player = player;
			this.pos = pos;
			this.ending = ending;

		}

		@Override
		public void handle(Event event) {
			for (int i = 0; i < a.size(); i++) {
				clicked_token[0] = ending;
				clicked_token[2] = pos;
				clicked = true;
				Circle circle_back = new Circle(25 * scale, Color.GRAY);
				circle_back.setOpacity(0.7);
				Circle circle = null;
				if (a.get(i).getColor().equals("Red"))
					circle = new Circle(15 * scale, Color.RED);
				else if (a.get(i).getColor().equals("Yellow"))
					circle = new Circle(15 * scale, Color.YELLOW);
				else if (a.get(i).getColor().equals("Green"))
					circle = new Circle(15 * scale, Color.GREEN);
				else if (a.get(i).getColor().equals("Blue"))
					circle = new Circle(15 * scale, Color.BLUE);
				circle.setLayoutX(x);
				circle.setId(""+i);
				circle.setLayoutY(y);
				circle_back.setLayoutX(x);
				circle_back.setLayoutY(y);
				Timeline timeline = new Timeline();
				timeline.getKeyFrames()
				.addAll(new KeyFrame(new Duration(150 * a.size()), // set
						new KeyValue(circle.translateXProperty(), shift),
						new KeyValue(circle_back.translateXProperty(), shift)));
				shift += 50 * scale;
				circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						Circle a = (Circle)(e.getSource());
						if(a.getFill() == Color.RED){
							clicked_token[1] = 0;
						}else if(a.getFill() == Color.YELLOW){
							clicked_token[1] = 1;
						}else if(a.getFill() == Color.GREEN){
							clicked_token[1] = 2;
						}else if(a.getFill() == Color.BLUE){
							clicked_token[1] = 3;
						}
						clicked_token[3] = Integer.parseInt(a.getId());
						selected = true;
						root.setEffect(null);
						effect_group.getChildren().clear();
						clicked = false;
					}
				});
				timeline.play();
				root.setEffect(new BoxBlur(10, 10, 3));
				effect_group.getChildren().add(circle_back);
				effect_group.getChildren().add(circle);

				root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {

						if (clicked == true) {
							root.setEffect(null);
							effect_group.getChildren().clear();
							clicked = false;
						}
					}
				});

			}
			shift = 0;

		}
	}
	public boolean isRolled() {
		return rolled;
	}
	public void setRolled(boolean rolled) {
		this.rolled = rolled;
	}
	public Board getBoard() {
		return board;
	}
	public boolean isSelected() {
		if(clicked_token[3] == -1)
			return false;
		return true;
	}
	public int[] getClicked_token() {
		return clicked_token;
	}
	public void showPlayerTurn(String player, int i){
		root.setEffect(new BoxBlur(10,10,3));
		Text t = new Text();
		t.setText(player+"'s TURN...");
		t.setVisible(true);
		t.setFont(Font.font("Verdana", FontWeight.BOLD, 70*scale));
		if(i == 0){
			t.setFill(Color.RED);
		}else if(i == 1){
			t.setFill(Color.YELLOW);
		}else if(i == 2){
			t.setFill(Color.GREEN);
		}else if(i == 3){
			t.setFill(Color.BLUE);
		}
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		t.setLayoutX(300*scale);
		t.setLayoutY(375*scale);
		t.setEffect(ds);
		t.setCache(true);
		animeFinished = false;
		turnGroup.getChildren().add(t);
		playerTurnAnimation =  new Timeline(new KeyFrame(Duration.seconds(1), new turnAnimation(t)));
		playerTurnAnimation.setCycleCount(1);
		playerTurnAnimation.play();

	}
	public void playTokenAnimation(int type){
		animeFinished = false;
		int index = -1;
		if(type == 0){
			String str = "" +clicked_token[2];
			if(clicked_token[1] == 0){
				str += "," + "Red";
			}else if(clicked_token[1] == 1){
				str += "," + "Yellow";
			}else if(clicked_token[1] == 2){
				str += "," + "Green";
			}else if(clicked_token[1] == 3){
				str += "," + "Blue";
			}
			
			for(int i = 0; i < slot_tokens.getChildren().size();i++){
				if(slot_tokens.getChildren().get(i).getId().equals(str)){
					index = i;
					break;
				}
			}
		}else{
			String str = "" +(clicked_token[2]+52);
			if(clicked_token[1] == 0){
				str += "," + "Red";
			}else if(clicked_token[1] == 1){
				str += "," + "Yellow";
			}else if(clicked_token[1] == 2){
				str += "," + "Green";
			}else if(clicked_token[1] == 3){
				str += "," + "Blue";
			}
			for(int i = 0; i < endingSlot_tokens.getChildren().size();i++){
				if(endingSlot_tokens.getChildren().get(i).getId().equals(str)){
					index = i;
					break;
				}
			}
		}

		if(index != -1){

			animeOfMovement = new Timeline(new KeyFrame(Duration.seconds(0.3), new movementAnimation(index)));
			animeOfMovement.setCycleCount(Timeline.INDEFINITE);
			animeOfMovement.play();
		}
	}

	private class movementAnimation implements EventHandler{

		int index;
		int count;
		public movementAnimation(int i) {
			this.index = i;
			smallAnime = true;
			count = 0;
		}
		@Override
		public void handle(Event event) {
			if(smallAnime){
				smallAnime = false;
				if(count == board.getDie().getfaceValue()){
					animeOfMovement.stop();
					animeFinished = true;
				}else{
					int arrow = 2;
					int type = 0;
					if(clicked_token[0] == 0){
						if((clicked_token[2]>=0 && clicked_token[2] <4) 
								|| (clicked_token[2] >17 && clicked_token[2] <23)
								|| (clicked_token[2] >=10 && clicked_token[2] <12)
								|| clicked_token[2] == 51){
							if(slot_tokens.getChildren().get(index).getId().contains("Yellow") && clicked_token[2] == 11){
								System.out.println();
								arrow = 3;
							}else{
								arrow = 0; //right
							}
						}else if(clicked_token[2] == 4){
							arrow = 4;
						}else if(clicked_token[2] == 17){
							arrow = 5;
						}else if(clicked_token[2] == 30){
							arrow = 6;
						}else if(clicked_token[2] == 43){
							arrow = 7;
						}else if((clicked_token[2] >24 && clicked_token[2] < 30) 
								|| (clicked_token[2] >43 && clicked_token[2] <49)
								|| (clicked_token[2] >=36 && clicked_token[2] <38)){
							if(slot_tokens.getChildren().get(index).getId().contains("Blue") && clicked_token[2] == 37){
									arrow = 2;
								}else{
									arrow = 1; //left
								}
						}else if((clicked_token[2] > 4 && clicked_token[2] < 10) 
								|| (clicked_token[2] >=49 && clicked_token[2] <51)
								|| (clicked_token[2] >=38 && clicked_token[2] <43)){
							if(slot_tokens.getChildren().get(index).getId().contains("Red") && clicked_token[2] == 50){
								arrow = 0;
							}else{
								arrow = 2; //top
							}
						}else if((clicked_token[2] >= 12 && clicked_token[2] < 17) 
								|| (clicked_token[2] >=23 && clicked_token[2] <25)
								|| (clicked_token[2] >=31 && clicked_token[2] <36)){
							if(slot_tokens.getChildren().get(index).getId().contains("Green") && clicked_token[2] == 24){
								arrow = 1;
							}
							else{
								arrow = 3;	 //bottom
							}
						}
						System.out.println(clicked_token[2]);
						clicked_token[2] = (clicked_token[2]+1)%52;
					}else{
						if(clicked_token[1] == 0){
							arrow = 0;
						}else if(clicked_token[1] == 1){
							arrow = 3;
						}else if(clicked_token[1] == 2){
							arrow = 1;
						}else  if(clicked_token[1] == 3){
							arrow = 2;
						}
						clicked_token[2]++;
						type = 1;
					}
					count++;
					Timeline smallanimeOfMovement = new Timeline(new KeyFrame(Duration.seconds(0.01), new oneMovementAnimation(type,index,arrow)));
					smallanimeOfMovement.setCycleCount(50);
					smallanimeOfMovement.play();
				}

			}

		}

	}
	private class oneMovementAnimation implements EventHandler{
		int count;
		int index;
		int arrow;
		int x = 0;
		int y = 0;
		int type;
		boolean flag = true;
		public oneMovementAnimation(int type,int index,int arrow) {
			this.index = index;
			this.arrow = arrow;
			this.type = type;
		}

		@Override
		public void handle(Event event) {
			smallAnime = false;
			if(arrow == 0){
				x++;
				if(flag){
					y--;
				}else {
					y++;
				}
				if(y == -25){
					flag = false;
				}
			}else if(arrow == 1){
				x--;
				if(flag){
					y++;
				}else {
					y--;
				}
				if(y == 25){
					flag = false;
				}
			}else if(arrow == 2){
				y--;
				if(flag){
					x--;
				}else {
					x++;
				}
				if(x == -25){
					flag = false;
				}
			}else if(arrow == 3){
				y++;
				if(flag){
					x++;
				}else {
					x--;
				}
				if(x == +25){
					flag = false;
				}
			}else if(arrow == 4){
				y--;
				x++;
			}else if(arrow == 5){
				x++;
				y++;
			}else if(arrow == 6){
				x--;
				y++;
			}else if(arrow == 7){
				y--;
				x--;
			}
			if(type == 0){
				slot_tokens.getChildren().get(index).setTranslateX(x);
				slot_tokens.getChildren().get(index).setTranslateY(y);
				count++;
				if(count == 50){
					slot_tokens.getChildren().get(index).setLayoutX(slot_tokens.getChildren().get(index).getLayoutX()+slot_tokens.getChildren().get(index).getTranslateX());
					slot_tokens.getChildren().get(index).setTranslateX(0);
					slot_tokens.getChildren().get(index).setLayoutY(slot_tokens.getChildren().get(index).getLayoutY()+slot_tokens.getChildren().get(index).getTranslateY());
					slot_tokens.getChildren().get(index).setTranslateY(0);
					smallAnime = true;
				}
			}else{
				endingSlot_tokens.getChildren().get(index).setTranslateX(x);
				endingSlot_tokens.getChildren().get(index).setTranslateY(y);
				count++;
				if(count == 50){
					endingSlot_tokens.getChildren().get(index).setLayoutX(
							endingSlot_tokens.getChildren().get(index).getLayoutX()+endingSlot_tokens.getChildren().get(index).getTranslateX());
					endingSlot_tokens.getChildren().get(index).setTranslateX(0);
					endingSlot_tokens.getChildren().get(index).setLayoutY(
							endingSlot_tokens.getChildren().get(index).getLayoutY()+endingSlot_tokens.getChildren().get(index).getTranslateY());
					endingSlot_tokens.getChildren().get(index).setTranslateY(0);
					smallAnime = true;
				}
			}

		}


	}
	private class turnAnimation implements EventHandler{

		Text text;
		public turnAnimation(Text text){
			this.text = text;
		}
		@Override
		public void handle(Event arg0) {

			animeFinished = true;
			root.setEffect(null);
			turnGroup.getChildren().clear();				
		}

	}
	private class rollAnimation implements EventHandler{


		int i = 0;
		@Override
		public void handle(Event arg0) {
			die.setImage(dieImages[(int) (Math.random()*6)]);
			die.setX(910*scale);
			die.setY(180*scale);
			die.setScaleX(scale);
			die.setScaleY(scale);
			i++;
			if(i == 6){
				board.getDie().roll();
				die.setImage(dieImages[board.getDie().getfaceValue()-1]);
				die.setX(910*scale);
				die.setY(180*scale);
				die.setScaleX(scale);
				die.setScaleY(scale);
				rollAnime.stop();

				setRolled(true);
				i = 0;
			}
		}

	}
	public boolean isFinishedTurn() {
		return animeFinished;
	}


}