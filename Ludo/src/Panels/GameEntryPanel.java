import java.awt.Insets;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.util.List;
import java.util.Scanner;

	public class GameEntryPanel extends Application {

		DropShadow shadow = new DropShadow();
		private double scale;
		Group root = new Group();

		public GameEntryPanel(double scale){
			this.scale = scale;
			root = new Group();
		}

    	TextArea TxtAr1, TxtAr2, TxtAr3, TxtAr4;
    	
    	
		@Override
		public void start(Stage entryStage){
			
	        Scene scene = new Scene(root, 1200*scale, 800*scale, Color.BEIGE);
	        entryStage.setScene(scene);
			Rectangle closeBtn = new Rectangle(30 * scale, 30 * scale, Color.RED);
			Rectangle background = new Rectangle(1200 * scale, 800 * scale, Color.BLACK);
			Button contBtn = new Button(" Continue ");
			contBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 30*scale));
	        contBtn.setStyle("-fx-base: #F5F5DC;");
	        contBtn.setAlignment(Pos.CENTER);
	        contBtn.setLayoutX(scene.getWidth()/2-100*scale);
	        contBtn.setLayoutY(scene.getHeight()/2-30*scale);
			
			Group g1 = new Group();
			Group g2 = new Group();
			Group g3 = new Group();
			Group g4 = new Group();
			
			Rectangle rta1 =  new Rectangle(600 * scale, 400 * scale, Color.RED);
			Rectangle rta2 =  new Rectangle(600 * scale, 400 * scale, Color.YELLOW);
			Rectangle rta3 =  new Rectangle(600 * scale, 400 * scale, Color.GREEN);
			Rectangle rta4 =  new Rectangle(600 * scale, 400 * scale, Color.BLUE);
			
			TxtAr1 = new TextArea("");
			TxtAr1.setFont(Font.font("Verdana", FontWeight.NORMAL, 30*scale));
			TxtAr2 = new TextArea("");
			TxtAr2.setFont(Font.font("Verdana", FontWeight.NORMAL, 30*scale));
			TxtAr3 = new TextArea("");
			TxtAr3.setFont(Font.font("Verdana", FontWeight.NORMAL, 30*scale));
			TxtAr4 = new TextArea("");
			TxtAr4.setFont(Font.font("Verdana", FontWeight.NORMAL, 30*scale));
			
			TxtAr1.setPromptText("1st player's name");
			TxtAr1.setTranslateX(150*scale);
			TxtAr1.setTranslateY(150*scale);
			TxtAr1.setMaxSize(300*scale, 100*scale);
			rta1.setArcHeight(100*scale);
		    rta1.setArcWidth(100*scale);
		    rta1.setOpacity(0.8);
			TxtAr2.setPromptText("2nd player's name");
			TxtAr2.setTranslateX(150*scale);
			TxtAr2.setTranslateY(150*scale);
			TxtAr2.setMaxSize(300*scale, 100*scale);
			rta2.setArcHeight(100);
		    rta2.setArcWidth(100);
		    rta2.setOpacity(0.8);
			TxtAr3.setPromptText("3rd player's name");
			TxtAr3.setTranslateX(150*scale);
			TxtAr3.setTranslateY(150*scale);
			TxtAr3.setMaxSize(300*scale, 100*scale);
			rta3.setArcHeight(100);
		    rta3.setArcWidth(100);
		    rta3.setOpacity(0.8);
			TxtAr4.setPromptText("4th player's name");
			TxtAr4.setTranslateX(150*scale);
			TxtAr4.setTranslateY(150*scale);
			TxtAr4.setMaxSize(300*scale, 100*scale);
			rta4.setArcHeight(100);
		    rta4.setArcWidth(100);
		    rta4.setOpacity(0.8);
			//TxtAr4.getText();
		
		    g1.getChildren().add(rta1);
			g1.getChildren().add(TxtAr1);
			g2.getChildren().add(rta2);
			g2.getChildren().add(TxtAr2);
			g3.getChildren().add(rta3);
			g3.getChildren().add(TxtAr3);
			g4.getChildren().add(rta4);
			g4.getChildren().add(TxtAr4);
			
			closeBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					Platform.exit();
				}
			});

			closeBtn.setLayoutX(1170 * scale);
			
			Button menuBtn = new Button(" Go Back to Menu ");
	        menuBtn.setFont(Font.font("Verdana", FontWeight.NORMAL, 30*scale));
	        menuBtn.setStyle("-fx-base: #F5F5DC;");
	        menuBtn.setAlignment(Pos.CENTER);
	        menuBtn.setLayoutX(scene.getWidth()/2-150*scale);
	        menuBtn.setLayoutY(scene.getHeight()-59*scale);

	        
	        menuBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

				  MainMenu mMenu = new MainMenu();
				  mMenu.start(entryStage);
	        });
	        
	        contBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

				  GamePanel gp = new GamePanel(1);
				  gp.start(entryStage);
	        });

	        
	        ///
	        
	        GridPane gridPane = new GridPane();
	        gridPane.add(g1, 0, 0, 1, 1);
	        gridPane.add(g2, 1, 0, 1, 1);
	        gridPane.add(g4, 0, 1, 1, 1);
	        gridPane.add(g3, 1, 1, 1, 1);
	        gridPane.setAlignment(Pos.BOTTOM_CENTER);
	
	        ///
	        /*
	        GridPane gridPane = new GridPane();
	        gridPane.add(TxtAr1, 0, 0, 1, 1);
	        gridPane.add(TxtAr2, 1, 0, 1, 1);
	        gridPane.add(TxtAr3, 0, 1, 1, 1);
	        gridPane.add(TxtAr4, 1, 1, 1, 1);
	        gridPane.setAlignment(Pos.BOTTOM_CENTER);
	        */
	        ///
	        root.getChildren().add(gridPane);
	        root.getChildren().add(contBtn);
	        root.getChildren().add(menuBtn);
			entryStage.show();

			}


	}