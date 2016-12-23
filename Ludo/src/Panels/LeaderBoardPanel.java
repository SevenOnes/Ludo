package Panels;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



	public class LeaderBoardPanel extends Application {

		DropShadow shadow = new DropShadow();
		private double scale;
		Group root = new Group();

		public LeaderBoardPanel(double scale){
			this.scale = scale;
			root = new Group();
		}
		
		Path leaderBPatch = Paths.get("leaderBoard.txt");
        
    	Text leaderBText;
    	TextArea leaderBTextArea;
		@Override
		public void start(Stage leaderBStage) {

			BorderPane bpane = new BorderPane();
	        Scene scene = new Scene(root, 1200*scale, 800*scale, Color.WHITE);
	        leaderBStage.setScene(scene);
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
				  mMenu.start(leaderBStage);
	        });

	        leaderBText = new Text("LeaderBoard");
	        leaderBText.setFill(Color.ORANGE);
	        leaderBText.setEffect(shadow);
	        Font arial = Font.font("Arial", 40);
	        leaderBText.setFont(arial);

	        leaderBTextArea = new TextArea();
	        
	        leaderBTextArea.setText("LeaderBoard");
	        leaderBTextArea.setDisable(true);
	        leaderBTextArea.setMouseTransparent(false);
	        leaderBTextArea.setFocusTraversable(false);
	        Paint value0 = Paint.valueOf("FFFFFF");
	        BackgroundFill bfill = new BackgroundFill(value0, null, null);
	        Background bleaderB = new Background(bfill);
	        leaderBTextArea.setBackground(bleaderB);
	        leaderBTextArea.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
	        
	        VBox vbox = new VBox();

	        vbox.setLayoutX(scene.getWidth()/2-120*scale);

	        vbox.setLayoutY(scene.getHeight()/1.5-60*scale);

	        vbox.setScaleX(scale);

	        vbox.setScaleY(scale);
	        
	        //vbox.getChildren().add(leaderBText);
	        vbox.getChildren().add(menuBtn);
	        //bpane.getChildren().add(leaderBText);
	        bpane.setTop(leaderBText);
	        bpane.setLeft(leaderBTextArea);
	        bpane.setLayoutX(scene.getWidth()-1100*scale);
	        bpane.setLayoutY(scene.getHeight()-700*scale);
	        //bpane.setBottom(vbox);
	        root.getChildren().add(background);
	        root.getChildren().add(bpane);
	        root.getChildren().add(vbox);
			root.getChildren().add(closeBtn);
	        

			leaderBStage.show();



	        }


}