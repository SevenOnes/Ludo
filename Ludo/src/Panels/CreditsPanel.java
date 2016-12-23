import java.awt.Insets;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
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



	public class CreditsPanel extends Application {

		DropShadow shadow = new DropShadow();
		private double scale;
		Group root = new Group();

		public CreditsPanel(double scale){
			this.scale = scale;
			root = new Group();
		}

		Path creditsPatch = Paths.get("credits.txt");
        
    	Text creditsText;
    	TextArea creditsTextArea;
    	
		@Override
		public void start(Stage creditsStage){
			
			BorderPane bpane = new BorderPane();
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
				  mMenu.start(creditsStage);
	        });

	        creditsText = new Text("Credits");
	        creditsText.setFill(Color.ORANGE);
	        creditsText.setEffect(shadow);
	        Font arial = Font.font("Arial", 40);
	        creditsText.setFont(arial);

	        creditsTextArea = new TextArea();
	        
	        creditsTextArea.setText("credits");
	        creditsTextArea.setDisable(true);
	        creditsTextArea.setMouseTransparent(false);
	        creditsTextArea.setFocusTraversable(false);
	        Paint value0 = Paint.valueOf("FFFFFF");
	        BackgroundFill bfill = new BackgroundFill(value0, null, null);
	        Background bcredits = new Background(bfill);
	        creditsTextArea.setBackground(bcredits);
	        creditsTextArea.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
	        
	        VBox vbox = new VBox();

	        vbox.setLayoutX(scene.getWidth()/2-120*scale);

	        vbox.setLayoutY(scene.getHeight()/1.5-60*scale);

	        vbox.setScaleX(scale);

	        vbox.setScaleY(scale);
	        
	        //vbox.getChildren().add(creditsText);
	        vbox.getChildren().add(menuBtn);
	        //bpane.getChildren().add(creditsText);
	        bpane.setTop(creditsText);
	        bpane.setLeft(creditsTextArea);
	        bpane.setLayoutX(scene.getWidth()-1100*scale);
	        bpane.setLayoutY(scene.getHeight()-700*scale);
	        //bpane.setBottom(vbox);
	        root.getChildren().add(background);
	        root.getChildren().add(bpane);
	        root.getChildren().add(vbox);
			root.getChildren().add(closeBtn);
	        

			creditsStage.show();

			}


	        	}