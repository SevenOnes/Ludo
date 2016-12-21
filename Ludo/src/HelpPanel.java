import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.util.Scanner;



	public class HelpPanel extends Application {

		DropShadow shadow = new DropShadow();
		private double scale;
		Group root = new Group();

		public HelpPanel(double scale){
			this.scale = scale;
			root = new Group();
		}

		Path helpPatch = Paths.get("help.txt");
        
    	TextArea helpArea;
    	
		@Override
		public void start(Stage helpStage) {
			
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
				// mMenu.primaryStageMain.start(mMenu.primaryStageMain);
				//  MainMenu.primaryStageMain = MainMenu.primaryStageMain;
				 // (MainMenu.primaryStageMain).show();
				  //helpStage = MainMenu.primaryStageMain;
				 // helpStage.show(MainMenu.primaryStageMain);
		//mMenu.start(helpStage);
				  //mMenu.start(mMenu.primaryStageMain);
		    			//MenuManager.swapPanels(new MainMenu());//Returns back to menu
				 // start(mMenu.primaryStageMain);
				  //helpStage.
			  });

	        
	     /*   Node label;
	    	File text = new File("Data\\HowToPlay.txt");
	    	try
	    	{
	    		Scanner fileScan = new Scanner(text);
	    		String line;

	    		while(fileScan.hasNextLine())
	    		{
	    			line = fileScan.nextLine();
	    			label = new Node(line);
	   		 	}
	    	}
	    	catch (FileNotFoundException exception)
	    	{
	    		String line = "File Not Found.";
	    		label = new Label(line);
	    	}
*/

	        helpArea = new TextArea();
	 /*       
	     // load file initally
	        if (Files.exists(watchPath)) {
	            loadFile();
	        }

	        // watch file
	        WatchThread watchThread = new WatchThread(watchPath);
	        watchThread.setDaemon( true);
	        watchThread.start();

	    }

	    private void loadFile() {

	        try {

	            String stringFromFile = Files.lines(watchPath).collect(Collectors.joining("\n"));
	            textArea.setText(stringFromFile);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    private class WatchThread extends Thread {

	        Path watchPath;

	        public WatchThread(Path watchPath) {
	            this.watchPath = watchPath;
	        }

	        public void run() {

	            try {

	                WatchService watcher = FileSystems.getDefault().newWatchService();
	                WatchKey key = watchPath.getParent().register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

	                while (true) {

	                    // wait for key to be signaled
	                    try {
	                        key = watcher.take();
	                    } catch (InterruptedException x) {
	                        return;
	                    }

	                    for (WatchEvent<?> event : key.pollEvents()) {

	                        WatchEvent.Kind<?> kind = event.kind();

	                        if (kind == StandardWatchEventKinds.OVERFLOW) {
	                            continue;
	                        }

	                        WatchEvent<Path> ev = (WatchEvent<Path>) event;

	                        Path path = ev.context();

	                        if (!path.getFileName().equals(watchPath.getFileName())) {
	                            continue;
	                        }

	                        // process file
	                        Platform.runLater(() -> {
	                            loadFile();
	                        });

	                    }

	                    boolean valid = key.reset();
	                    if (!valid) {
	                        break;
	                    }

	                }
	            } catch (IOException x) {
	                System.err.println(x);
	            }
	        }
	    }

	        */
	        VBox vbox = new VBox();

	        vbox.setLayoutX(scene.getWidth()/2-120*scale);

	        vbox.setLayoutY(scene.getHeight()/1.5-60*scale);

	        vbox.setScaleX(scale);

	        vbox.setScaleY(scale);
	        
	        vbox.getChildren().add(menuBtn);
	        
	      //  bpane.setCenter(helpArea);
	        
	        root.getChildren().add(background);
	        root.getChildren().add(bpane);
	        root.getChildren().add(vbox);
			root.getChildren().add(closeBtn);
	        

			helpStage.show();

			}


	}