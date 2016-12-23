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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
import java.util.concurrent.atomic.AtomicInteger;

	public class HelpPanel extends Application {

		DropShadow shadow = new DropShadow();
		private double scale;
		Group root = new Group();

		public HelpPanel(double scale){
			this.scale = scale;
			root = new Group();
		}

		Path helpPatch = Paths.get("help.txt");
        
    	Text helpText;
    	TextArea helpTextArea;
    	
		@Override
		public void start(Stage helpStage){
			
			BorderPane bpane = new BorderPane();
	        Scene scene = new Scene(root, 1200*scale, 800*scale, Color.WHITE);
	        helpStage.setScene(scene);
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
				  mMenu.start(helpStage);
	        });

	        helpText = new Text("How To Play");
	        helpText.setFill(Color.ORANGE);
	        helpText.setEffect(shadow);
	        Font arial = Font.font("Arial", 40);
	        helpText.setFont(arial);

	        helpTextArea = new TextArea();
	        
	        helpTextArea.setText("Help");
	        helpTextArea.setDisable(true);
	        helpTextArea.setMouseTransparent(false);
	        helpTextArea.setFocusTraversable(false);
	        Paint value0 = Paint.valueOf("FFFFFF");
	        BackgroundFill bfill = new BackgroundFill(value0, null, null);
	        Background bHelp = new Background(bfill);
	        helpTextArea.setBackground(bHelp);
	        helpTextArea.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
	        
	        VBox vbox = new VBox();

	        vbox.setLayoutX(scene.getWidth()/2-120*scale);

	        vbox.setLayoutY(scene.getHeight()/1.5-60*scale);

	        vbox.setScaleX(scale);

	        vbox.setScaleY(scale);
	        
	        //vbox.getChildren().add(helpText);
	        vbox.getChildren().add(menuBtn);
	        //bpane.getChildren().add(helpText);
	        bpane.setTop(helpText);
	        bpane.setLeft(helpTextArea);
	        bpane.setLayoutX(scene.getWidth()-1100*scale);
	        bpane.setLayoutY(scene.getHeight()-700*scale);
	        //bpane.setBottom(vbox);
	        root.getChildren().add(background);
	        root.getChildren().add(bpane);
	        root.getChildren().add(vbox);
			root.getChildren().add(closeBtn);
	        

			helpStage.show();

			}


	}