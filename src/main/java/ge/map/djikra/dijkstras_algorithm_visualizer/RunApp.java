package ge.map.djikra.dijkstras_algorithm_visualizer;
import ge.map.djikra.Drawables.GridCanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RunApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridCanvas gridCanvas = new GridCanvas(30, 30);
        StackPane root = new StackPane();
        root.getChildren().add(gridCanvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dijkstra Grid Visualizer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}