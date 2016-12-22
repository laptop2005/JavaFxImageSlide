package application;
	
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
public static final double D = 200;  // diameter.
	
	public static final double W = D*6; // canvas dimensions.
    public static final double H = D*6;

    DoubleProperty x  = new SimpleDoubleProperty();
    DoubleProperty y  = new SimpleDoubleProperty();

    final Canvas canvas = new Canvas(200, 200);
    
    @Override public void start(Stage stage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane,400,400);
        
        pane.getChildren().add(canvas);
        
        stage.setScene(scene);
        stage.show();
        
        setAnimation();
        
    }
    
    public void setAnimation(){
    	Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                    new KeyValue(x, 0),
                    new KeyValue(y, 0)
            ),
            new KeyFrame(Duration.seconds(15),
                    new KeyValue(x, D - W),
                    new KeyValue(y, D - H)
            )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                Image image1 = new Image("application/dice_1.jpg");
                Image image2 = new Image("application/dice_2.jpg");
                Image image3 = new Image("application/dice_3.jpg");
                Image image4 = new Image("application/dice_4.jpg");
                Image image5 = new Image("application/dice_5.jpg");
                Image image6 = new Image("application/dice_6.jpg");
                gc.drawImage(image1,x.doubleValue(),0, D, D);
                gc.drawImage(image2,x.doubleValue()+D,0, D, D);
                gc.drawImage(image3,x.doubleValue()+D*2,0, D, D);
                gc.drawImage(image4,x.doubleValue()+D*3,0, D, D);
                gc.drawImage(image5,x.doubleValue()+D*4,0, D, D);
                gc.drawImage(image6,x.doubleValue()+D*5,0, D, D);
            }
        };

        timer.start();
        timeline.play();
    }

	public static void main(String[] args) {
		launch(args);
	}
}
