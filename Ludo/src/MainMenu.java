 
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
 
public class MainMenu extends Application {
	 DropShadow shadow = new DropShadow();
	 double scale = 1;
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 800, Color.WHITE);
        primaryStage.setScene(scene);
        
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        Rectangle closeBtn = new Rectangle(30*scale,30*scale,Color.RED);

        closeBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
               new EventHandler<MouseEvent>() {
                 @Override
                 public void handle(MouseEvent e) {
               	  	Platform.exit();
                 }
               });
        closeBtn.setLayoutX(1170*scale);

        
        Group circles = new Group();
        for (int i = 0; i < 40; i++) {
           Circle circle = new Circle(150, Color.web("white", 0.05));
           circle.setStrokeType(StrokeType.OUTSIDE);
           circle.setStroke(Color.web("white", 0.16));
           circle.setStrokeWidth(4);
           circles.getChildren().add(circle);
        }
        circles.setEffect(new BoxBlur(10, 10, 3));
        
        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
        	     new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new 
        	         Stop[]{
        	            new Stop(0, Color.web("#f8bd55")),
        	            new Stop(0.14, Color.web("#c0fe56")),
        	            new Stop(0.28, Color.web("#5dfbc1")),
        	            new Stop(0.43, Color.web("#64c2f8")),
        	            new Stop(0.57, Color.web("#be4af7")),
        	            new Stop(0.71, Color.web("#ed5fc2")),
        	            new Stop(0.85, Color.web("#ef504c")),
        	            new Stop(1, Color.web("#f2660f")),}));
        	colors.widthProperty().bind(scene.widthProperty());
        	colors.heightProperty().bind(scene.heightProperty());
        
   
        Group blendModeGroup = 
        		  new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
        		       Color.BLACK), circles)	, colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModeGroup);
        
        Timeline timeline = new Timeline();
        for (Node circle: circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                    new KeyValue(circle.translateXProperty(), Math.random() * 1200),
                    new KeyValue(circle.translateYProperty(), Math.random() * 800)
                ),
                new KeyFrame(new Duration(40000), // set end position at 40s
                    new KeyValue(circle.translateXProperty(), Math.random() * 1200),
                    new KeyValue(circle.translateYProperty(), Math.random() * 800)
                )
            );
        }
        
        Image image1 = new Image("file:Ludo.png",true);
        ImageView iv = new ImageView(image1);
        
        Reflection r = new Reflection();
        r.setFraction(0.9);
 
        iv.setEffect(r);
        iv.setX(285);
        iv.setY(250);
        iv.setScaleX(1.25);
        iv.setScaleY(1.25);
        
        Button btn = new Button("Go to Game");
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent e) {
                	  btn.setEffect(shadow);
                  }
                });


        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent e) {
                	  btn.setEffect(null);
                  }
                });
        
        
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                  @Override
                  public void handle(MouseEvent e) {
                	  GamePanel gp = new GamePanel();
                	  gp.start(primaryStage);
                  }
                });
        
        VBox vbox = new VBox();
        vbox.setLayoutX(285);
        vbox.setLayoutY(350);
        
        vbox.getChildren().add(btn);
        vbox.setSpacing(10);
        root.getChildren().add(vbox);
        
        root.getChildren().add(iv);
        timeline.play();
        root.getChildren().add(closeBtn);
        primaryStage.show();
        
       
    }
    
public static void main(String[] args) {
        launch(args);
    }

class WindowButtons extends Rectangle {
    
}
}