package main.java;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Database.Database;
import main.java.Section.Admin.AdminSection;
import main.java.Section.Seat.SeatHandler;
import main.java.Section.SectionHandler;
import main.java.Utilities.Utilities;


public class Client extends Application {

    private static final String VERSION = " 3.1.0";

    private static SectionHandler sectionHandler;
    private static Database database;

    public static void startClient(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("TJ Seating v" + VERSION);

        sectionHandler = new SectionHandler();

        AdminSection adminSection = new AdminSection();
        adminSection.getGridPane().setAlignment(Pos.CENTER);

        initializeDatabase();

        VBox verticalContainer = new VBox();
        verticalContainer.setAlignment(Pos.CENTER);
        verticalContainer.getChildren().addAll(adminSection.getGridPane(), sectionHandler.getHorozontalContainer());

        Scene scene = new Scene(verticalContainer, 900, 600);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);

        primaryStage.getIcons().add(new Image(getClass().getResource("/TJLogo.png").toString()));
        primaryStage.show();


        // Loading data dialog
        Utilities.createNicePopup("Welcome to TJ Seating",
                "Thanks for using TJ Seating!\nYour client has started.\nDeveloped 2019 Reid C");

    }

    private void initializeDatabase() {
        database = new Database();
    }

    public static SectionHandler getSectionHandler() {
        return sectionHandler;
    }

    public static Database getDatabase() {
        return database;
    }
}
