package Panels;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



	public class CreditsPanel extends Application {

		DropShadow shadow = new DropShadow();
		private double scale;
		Group root = new Group();

		public CreditsPanel(double scale){
			this.scale = scale;
			root = new Group();
		}

		@Override
		public void start(Stage creditsStage) {

	        Scene scene = new Scene(root, 1200*scale, 800*scale, Color.WHITE);
	        creditsStage.setScene(scene);


	        			Rectangle closeBtn = new Rectangle(30 * scale, 30 * scale, Color.RED);
	        			Rectangle background = new Rectangle(1200 * scale, 800 * scale, Color.BLACK);

	        			
	        			closeBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	        				@Override
	        				public void handle(MouseEvent e) {
	        					Platform.exit();
	        				}
	        			});

	        			closeBtn.setLayoutX(1170 * scale);
	        			
	        			Button menuBtn = new Button(" Go Back to Menu ");
	        	        menuBtn.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
	        	        menuBtn.setAlignment(Pos.CENTER);
	        	        //setPadding(new Insets(100, 0, 0, 100));

	        	        
	        	        menuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

	        				  MainMenu mMenu = new MainMenu();
	        				// mMenu.primaryStageMain.start(mMenu.primaryStageMain);
	        				//  MainMenu.primaryStageMain = MainMenu.primaryStageMain;
	        				 // (MainMenu.primaryStageMain).show();
	        				  //helpStage = MainMenu.primaryStageMain;
	        			  });

	        	        VBox vbox = new VBox();

	        	        vbox.setLayoutX(scene.getWidth()/2-120*scale);

	        	        vbox.setLayoutY(scene.getHeight()/1.5-60*scale);

	        	        vbox.setScaleX(scale);

	        	        vbox.setScaleY(scale);
	        	        
	        	        vbox.getChildren().add(menuBtn);
	        	        
	        	        
	        	        root.getChildren().add(background);
	        	        root.getChildren().add(vbox);
	        			root.getChildren().add(closeBtn);
	        	        



	        			}


	        	}