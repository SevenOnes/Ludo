package Panels;

import GameManagement.FileManager;
import GameManagement.GameManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	 private static double scale;
	 public MainMenu(){
	 }

	 public static void init(double sc){
		 scale = sc;
	 }
    @Override   
    public void start(Stage primaryStage){

        Group root = new Group();

        Scene scene = new Scene(root, 1200*scale, 800*scale, Color.WHITE);


        primaryStage.setScene(scene);

        
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

           Circle circle = new Circle(150*scale, Color.web("white", 0.05));

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

        

   

        Group blendModeGroup = new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK), circles), colors);

        colors.setBlendMode(BlendMode.OVERLAY);

        root.getChildren().add(blendModeGroup);

        

        Timeline timeline = new Timeline();

        for (Node circle: circles.getChildren()) {

            timeline.getKeyFrames().addAll(

                new KeyFrame(Duration.ZERO, // set start position at 0

                    new KeyValue(circle.translateXProperty(), Math.random() * 1200*scale),

                    new KeyValue(circle.translateYProperty(), Math.random() * 800*scale)

                ),

                new KeyFrame(new Duration(40000), // set end position at 40s

                    new KeyValue(circle.translateXProperty(), Math.random() * 1200*scale),

                    new KeyValue(circle.translateYProperty(), Math.random() * 800*scale)

                )

            );

        }

        

        Image image1 = new Image("file:Ludo4.png",true);

        ImageView iv = new ImageView(image1);



        iv.setX(scene.getWidth()/2-220*scale);

        iv.setY(200*scale);

        iv.setScaleX(1*scale);

        iv.setScaleY(1*scale);
        

        

        Button btn1 = new Button(" Play ");
        btn1.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
        btn1.setAlignment(Pos.CENTER);
        Button btn2 = new Button(" Help ");
        btn2.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
        btn2.setAlignment(Pos.CENTER);
        Button btn3 = new Button(" LeaderBoard ");
        btn3.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
        btn3.setAlignment(Pos.CENTER);
        Button btn4 = new Button(" Credits ");
        btn4.setStyle("-fx-font: 30 arial; -fx-base: #000000;");
        btn4.setAlignment(Pos.CENTER);
     
        
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn4.setMaxWidth(Double.MAX_VALUE);



        btn1.addEventHandler(MouseEvent.MOUSE_CLICKED,

                new EventHandler<MouseEvent>() {

                  @Override

                  public void handle(MouseEvent e) {

                	  GameEntryPanel gm = new GameEntryPanel(scale);
                	  gm.start(primaryStage);

                  }

                });
 

        btn2.addEventHandler(MouseEvent.MOUSE_CLICKED,

                new EventHandler<MouseEvent>() {

                  @Override

                  public void handle(MouseEvent e) {
                	  HelpPanel hp = new HelpPanel(scale);
                	  hp.start(primaryStage);


                  }

                });
        
        btn3.addEventHandler(MouseEvent.MOUSE_CLICKED,

                new EventHandler<MouseEvent>() {

                  @Override

                  public void handle(MouseEvent e) {
                	  LeaderBoardPanel lbp = new LeaderBoardPanel(scale);
                	  lbp.start(primaryStage);


                  }

                });
        
        btn4.addEventHandler(MouseEvent.MOUSE_CLICKED,

                new EventHandler<MouseEvent>() {

                  @Override

                  public void handle(MouseEvent e) {
                	  CreditsPanel cp = new CreditsPanel(scale);
                	  cp.start(primaryStage);


                  }

                });
        
        ////
        

        VBox vbox = new VBox();

        vbox.setLayoutX(scene.getWidth()/2-120*scale);

        vbox.setLayoutY(scene.getHeight()/2-60*scale);

        vbox.setScaleX(scale);

        vbox.setScaleY(scale);
        
        //vbox.getChildren().add(iv);
        
       // vbox.setSpacing(100);

        vbox.getChildren().add(btn1);

        vbox.setSpacing(10);
        
        vbox.getChildren().add(btn2);

        vbox.getChildren().add(btn3);
        
        vbox.getChildren().add(btn4);
        
        root.getChildren().add(vbox);

        

        root.getChildren().add(iv);
       // root.setA

        timeline.play();

        root.getChildren().add(closeBtn);

        primaryStage.show();

        

       

    }

    

public static void main(String[] args) {
		FileManager fm = new FileManager();
		init((double)(fm.readSetting()[0])/3);
		launch(args);

    }


}