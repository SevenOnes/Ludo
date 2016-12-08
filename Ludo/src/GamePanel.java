import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class GamePanel extends Application {
	DropShadow shadow = new DropShadow();
	@Override
	public void start(Stage primaryStage) {
        Group root = new Group();
		Scene scene = new Scene(root, 800, 600, Color.WHITE);
        primaryStage.setScene(scene);
        VBox vbox = new VBox();
        ArrayList<VBox> Vboxarray = new ArrayList<VBox>();    	
        Group circles = new Group();
        for(int k = 1; k < 5 ; k++){
        	for (int i = 0; i < 5; i++) {
                Circle circle = new Circle(50, Color.web("black", 0.05));
                circle.setStrokeType(StrokeType.OUTSIDE);
                circle.setStroke(Color.web("black", 0.16));
                circle.setStrokeWidth(4);
                circle.setLayoutX(k*105 + 100);
                circle.setLayoutY(i*105 + 100);
                
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
                
                circles.getChildren().add(circle);
             
             }
        }
        
        root.getChildren().add(circles);
        
            

	}

}
