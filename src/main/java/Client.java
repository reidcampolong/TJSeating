package main.java;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Database.Database;
import main.java.Section.Admin.AdminSection;
import main.java.Section.Seat.SeatHandler;
import main.java.Section.Section;
import main.java.Section.SectionHandler;


public class Client extends Application {

    private static final String VERSION = ".5";

    private static SectionHandler sectionHandler;
    private static Database database;

    public static void startClient(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("TJ Seating by Reid Campolong v" + VERSION);

        sectionHandler = new SectionHandler();
        /*Section leftSection = new Section(SectionHandler.sectionAValues[0], SectionHandler.sectionAValues[1], SectionHandler.sectionAValues[2]);
        Section middleSection = new Section(SectionHandler.sectionBValues[0], SectionHandler.sectionBValues[1], SectionHandler.sectionBValues[2]);
        Section rightSection = new Section(SectionHandler.sectionCValues[0], SectionHandler.sectionCValues[1], SectionHandler.sectionCValues[2]);

        sectionHandler.addSection(leftSection.getSectionNumber(), leftSection);
        sectionHandler.addSection(middleSection.getSectionNumber(), middleSection);
        sectionHandler.addSection(rightSection.getSectionNumber(), rightSection);

        leftSection.getGridPane().setAlignment(Pos.CENTER);
        middleSection.getGridPane().setAlignment(Pos.CENTER);
        rightSection.getGridPane().setAlignment(Pos.CENTER);*/

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Welcome to TJ Seating");
        alert.setGraphic(SeatHandler.dataLogo);
        alert.setHeaderText(null);
        alert.setContentText("Thanks for using TJ Seating!\nYour client has started.\nDeveloped 2019 Reid C");

        alert.showAndWait();

    }

    private void initializeDatabase() {
        database = new Database(this);
    }

    public static SectionHandler getSectionHandler() {
        return sectionHandler;
    }

    public static Database getDatabase() {
        return database;
    }
}
