package main.java;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Database.Database;
import main.java.Section.Admin.AdminSection;
import main.java.Section.Section;
import main.java.Section.SectionHandler;

import java.awt.*;


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
        Section leftSection = new Section(0, 31, 7);
        Section middleSection = new Section(1,29, 14);
        Section rightSection = new Section(2,31, 7);

        sectionHandler.addSection(leftSection.getSectionNumber(), leftSection);
        sectionHandler.addSection(middleSection.getSectionNumber(), middleSection);
        sectionHandler.addSection(rightSection.getSectionNumber(), rightSection);

        leftSection.getGridPane().setAlignment(Pos.CENTER);
        middleSection.getGridPane().setAlignment(Pos.CENTER);
        rightSection.getGridPane().setAlignment(Pos.CENTER);

        HBox horozontalContainer = new HBox();
        horozontalContainer.getChildren().addAll(leftSection.getGridPane(), middleSection.getGridPane(), rightSection.getGridPane());
        horozontalContainer.setAlignment(Pos.CENTER);

        AdminSection adminSection = new AdminSection();
        adminSection.getGridPane().setAlignment(Pos.CENTER);

        VBox verticalContainer = new VBox();
        verticalContainer.setAlignment(Pos.CENTER);
        verticalContainer.getChildren().addAll(adminSection.getGridPane(), horozontalContainer);

        Scene scene = new Scene(verticalContainer, 900, 600);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);

        initializeDatabase();
        primaryStage.show();

    }

    private void initializeDatabase() {
        database = new Database(this);
    }

    public static SectionHandler getSectionHandler() { return sectionHandler; }
    public static Database getDatabase() {
        return database;
    }
}
