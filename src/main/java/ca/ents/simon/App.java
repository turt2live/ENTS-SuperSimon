package ca.ents.simon;

import ca.ents.simon.configuration.ConfigKey;
import ca.ents.simon.configuration.SimonConfiguration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Main entry point for the application
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting application...");
        Group root = new Group();

        double minWidth = 1920;
        double minHeight = 1280;

        System.out.println("Setting up scene...");
        Scene scene = new Scene(root, minWidth, minHeight);
        primaryStage.setScene(scene);

        System.out.println("Preparing UI...");
        GameManager game = new GameManager();
        game.configureScene(scene, root);

        System.out.println("Preparing primary stage...");
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.setResizable(true);
        primaryStage.setTitle("ENTS SuperSimon");
        primaryStage.setFullScreen(SimonConfiguration.getBooleanValue(ConfigKey.UI_FULLSCREEN));
        primaryStage.setFullScreenExitHint(""); // Hides the message
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE)
                primaryStage.close();
        });
        primaryStage.setOnCloseRequest(event -> {
            primaryStage.hide();
            game.shutdown();
        });

        System.out.println("Showing primary stage...");
        primaryStage.show();

        System.out.println("Starting game...");
        game.beginOperation();
        System.out.println("Done startup loop!");
    }

    public static void main(String[] args) {
        String specVersion = App.class.getPackage().getSpecificationVersion();
        String buildVersion = App.class.getPackage().getImplementationVersion();
        String version = "v" + specVersion + "b" + buildVersion;
        System.out.println("ENTS SuperSimon " + version);
        System.out.println();
        launch(args);
    }
}
