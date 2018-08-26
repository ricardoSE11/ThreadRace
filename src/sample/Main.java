package sample;

import Domain.MovingVehicle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application implements Runnable{

    private Thread thread;

    //My vars
    //private ArrayList<Vehicle> vehicles;
    Controller myController;
    Image bgImage;


    private MovingVehicle jc2;
    private MovingVehicle jc;



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        myController = loader.getController();
        myController.init(primaryStage);

        initComponents(); //TODO: Reposition this.

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 750, 750));
        primaryStage.show();


    }


    private void initComponents()
    {
        try
        {
            bgImage = bgImage = new Image(new FileInputStream("src/Assets/background_icon_0.png"));

            /*vehicles = new ArrayList<>();

            this.jc2 = new MovingVehicle(555, 45, 1);
            this.jc2.start();

            //this.rc = new RunningCharacter(-50, 500, 0);
            //this.rc.start();

            this.jc = new MovingVehicle(420, 370, 0);
            this.jc.start();

            vehicles.add(jc2);
            //vehicles.add(rc);
            vehicles.add(jc);*/

            this.thread = new Thread(this);
            this.thread.start();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void run()
    {
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000/fps;

        while(true){
            try {
                //System.out.println(" --- Hilo consume recursos ---");
                start=System.nanoTime();
                elapsed=System.nanoTime()-start;
                wait = time-elapsed/1000000; //1000000
                Thread.sleep(wait);

                myController.reDraw(bgImage);
            }
            catch (InterruptedException ex) {}
        }
    }
}
