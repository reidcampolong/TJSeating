package me.reid;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.reid.Network.Connection.NetworkConnection;
import me.reid.Network.ServerInputListener;
import me.reid.Section.Admin.AdminSection;
import me.reid.Section.Seat.SeatHandler;
import me.reid.Section.Section;
import me.reid.Section.SectionHandler;

import java.io.IOException;
import java.net.Socket;

public class Client extends Application {

    private String ip = "localhost";
    private int port = 1069;
    private ServerInputListener serverInputListener;
    private static final String VERSION = ".5";

    private static NetworkConnection networkConnection;
    private static SectionHandler sectionHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Connect to the server
        initializeConnection();

        primaryStage.setTitle("TJ Seating by Reid Campolong v" + VERSION);

        sectionHandler = new SectionHandler();
        Section leftSection = new Section(0, 26, 7);
        Section middleSection = new Section(1,26, 7);
        Section rightSection = new Section(2,26, 7);

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

        Scene scene = new Scene(verticalContainer, 1300, 850);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initializeConnection() {
        try {
            networkConnection = new NetworkConnection(new Socket(ip, port));
            serverInputListener = new ServerInputListener(networkConnection);
            serverInputListener.start();
        } catch (IOException e) {
            SeatHandler.createBadPopup("TJSeating - Network", "Could not connect to the server. Please launch the server jar and then try again!");
            System.exit(0);
        }

    }

    public static NetworkConnection getConnection() {
        return networkConnection;
    }
    public static SectionHandler getSectionHandler() { return sectionHandler; }
    public static void main(String[] args) {
        launch(args);
    }
}
