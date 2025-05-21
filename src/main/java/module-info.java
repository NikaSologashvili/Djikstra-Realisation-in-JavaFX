module ge.map.djikra.dijkstras_algorithm_visualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ge.map.djikra.dijkstras_algorithm_visualizer to javafx.fxml;
    exports ge.map.djikra.dijkstras_algorithm_visualizer;
}