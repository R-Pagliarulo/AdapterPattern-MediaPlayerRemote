import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import devices.*;
import interfaces.CDPlayer;
import adapters.*;

/**
 * The GUI driver for the program. It uses JavaFX to generate a virtual CD Player remote.
 * This remote can control a CD player in addition to a DVD Player and a Radio (through the use of adapters).
 * A user can freely choose when to change what the remote is pointing to, and can end the program at will.
 * Dialog Boxes tell what is happening as selections are made.
 *
 * @author Robert Pagliarulo
 */
public class AdapterGUIDriver extends Application {

    // The main GUI components of the app
    Label label;
    ChoiceBox<String> devices;
    Button quitButton, powerButton, rewindButton, playPauseButton, forwardButton, volDownButton, muteButton, volUpButton;

    // The device being pointed to (controlled)
    String currentDevice;

    // The CD and DVD discs
    ArrayList<String> theRunawaysCD;
    String baltoDVD;

    // The Players and adapters for the non-CD Players
    BoseCDPlayer myCDPlayer;
    BoseDVDPlayer myDVDPlayer;
    BoseRadio myRadio;
    CDPlayer dvdToCDAdapter;
    CDPlayer radioToCDAdapter;


    public void start(Stage primaryStage) {

        // Initialize discs
        theRunawaysCD = new ArrayList<String>(Arrays.asList(
                "Cherry Bomb",
                "You Drive Me Wild",
                "Is It Day or Night?",
                "Thunder",
                "Rock and Roll",
                "Lovers",
                "American Nights",
                "Blackmail",
                "Secrets",
                "Dead End Justice"));
        baltoDVD = "Balto";

        // Initialize Players
        myCDPlayer = new BoseCDPlayer("The Runaways", theRunawaysCD);
        myDVDPlayer = new BoseDVDPlayer(baltoDVD);
        myRadio = new BoseRadio();

        // Initialize Adapters
        dvdToCDAdapter = new DVDToCDAdapter(myDVDPlayer);
        radioToCDAdapter = new RadioToCDAdapter(myRadio);


        // Images and associated ImageViews for the button icons
        Image powerIcon = new Image("/assets/power.png");
        ImageView powerIconView = new ImageView(powerIcon);
        powerIconView.setFitHeight(20);
        powerIconView.setFitWidth(20);
        Image rewindIcon = new Image("/assets/rewind.png");
        ImageView rewindIconView = new ImageView(rewindIcon);
        rewindIconView.setFitHeight(30);
        rewindIconView.setFitWidth(30);
        Image playPauseIcon = new Image("/assets/playPause.png");
        ImageView playPauseIconView = new ImageView(playPauseIcon);
        playPauseIconView.setFitHeight(45);
        playPauseIconView.setFitWidth(45);
        Image forwardIcon = new Image("/assets/fastForward.png");
        ImageView forwardIconView = new ImageView(forwardIcon);
        forwardIconView.setFitHeight(30);
        forwardIconView.setFitWidth(30);
        Image volDownIcon = new Image("/assets/volDown.png");
        ImageView volDownIconView = new ImageView(volDownIcon);
        volDownIconView.setFitHeight(30);
        volDownIconView.setFitWidth(30);
        Image muteIcon = new Image("/assets/mute.png");
        ImageView muteIconView = new ImageView(muteIcon);
        muteIconView.setFitHeight(40);
        muteIconView.setFitWidth(40);
        Image volUpIcon = new Image("/assets/volUp.png");
        ImageView volUpIconView = new ImageView(volUpIcon);
        volUpIconView.setFitHeight(30);
        volUpIconView.setFitWidth(30);

        // Rectangle that makes up the remote visually
        Rectangle remote = new Rectangle(0, 0, 250, 320);
        remote.setStrokeWidth(3);
        remote.setStroke(Color.BLACK);
        remote.setFill(Color.GREY);
        remote.setArcHeight(30);
        remote.setArcWidth(30);

        // Used to customize buttons
        String orangeStyle = "-fx-background-color: orange;\n-fx-border-color: gray;";
        String tealStyle = "-fx-background-color: teal;\n-fx-border-color: gray;";

        // Next 2 sections are the components shown outside (above) the remote
        label = new Label("Select a device:");
        String[] deviceNames = {"Bose CD Player", "Bose DVD Player", "Bose Radio"};
        devices = new ChoiceBox<String>();
        devices.getItems().addAll(deviceNames);
        devices.getSelectionModel().selectFirst();
        devices.setOnAction(this::processSelection);
        currentDevice = devices.getValue();
        VBox deviceSelection = new VBox(label, devices);
        deviceSelection.setSpacing(5);

        quitButton = new Button("Quit");
        quitButton.setOnAction(this::processQuitButtonPush);
        HBox outsideRemote = new HBox(deviceSelection, quitButton);
        outsideRemote.setAlignment(Pos.CENTER);
        outsideRemote.setSpacing(200);

        // Power button
        powerButton = new Button();
        powerButton.setGraphic(powerIconView);
        powerButton.setStyle("-fx-background-color: maroon;\n-fx-border-color: gray;");
        powerButton.setOnAction(this::processPowerButtonPush);
        HBox remoteRowA = new HBox(powerButton);
        remoteRowA.setMargin(powerButton, new Insets(0,110,0,0));
        remoteRowA.setAlignment(Pos.CENTER_RIGHT);

        // First group of 3 buttons
        rewindButton = new Button("", rewindIconView);
        rewindButton.setStyle(orangeStyle);
        rewindButton.setOnAction(this::processRewindButtonPush);
        playPauseButton = new Button("", playPauseIconView);
        playPauseButton.setStyle(tealStyle);
        playPauseButton.setOnAction(this::processPlayPauseButtonPush);
        forwardButton = new Button("", forwardIconView);
        forwardButton.setStyle(orangeStyle);
        forwardButton.setOnAction(this::processForwardButtonPush);
        HBox remoteRowB = new HBox(rewindButton, playPauseButton, forwardButton);
        remoteRowB.setSpacing(20);
        remoteRowB.setAlignment(Pos.CENTER);

        // Second group of 3 buttons
        volDownButton = new Button("", volDownIconView);
        volDownButton.setStyle(orangeStyle);
        volDownButton.setOnAction(this::processVolDownButtonPush);
        muteButton = new Button("", muteIconView);
        muteButton.setStyle(tealStyle);
        muteButton.setOnAction(this::processMuteButtonPush);
        volUpButton = new Button("", volUpIconView);
        volUpButton.setStyle(orangeStyle);
        volUpButton.setOnAction(this::processVolUpButtonPush);
        HBox remoteRowC = new HBox(volDownButton, muteButton, volUpButton);
        remoteRowC.setSpacing(20);
        remoteRowC.setAlignment(Pos.CENTER);

        // Puts all the interactive components together vertically
        VBox interactive = new VBox(outsideRemote, remoteRowA, remoteRowB, remoteRowC);
        interactive.setSpacing(30);
        interactive.setAlignment(Pos.BASELINE_CENTER);
        interactive.setPadding(new Insets(25,25,25,25));

        // Lays interactive components over the remote visual
        StackPane root = new StackPane(remote, interactive);
        root.setStyle("-fx-background-color: lavender");

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Media Player Remote");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Initial alert telling user what remote is pointed at
        Alert firstDevice = new Alert (Alert.AlertType.INFORMATION);
        firstDevice.setHeaderText(null);
        firstDevice.setContentText("Remote is pointed at " + currentDevice);
        firstDevice.showAndWait();
    }

    // Event Handling for the GUI components occurs below here. Each creates an Alert for the user.
    public void processSelection(ActionEvent event) {
        currentDevice = devices.getValue();
        Alert deviceChanged = new Alert (Alert.AlertType.INFORMATION);
        deviceChanged.setHeaderText(null);
        deviceChanged.setContentText("Remote is now pointed at " + currentDevice);
        deviceChanged.showAndWait();
    }

    public void processQuitButtonPush(ActionEvent event) {
        Alert confirmQuit = new Alert(Alert.AlertType.CONFIRMATION);
        confirmQuit.setHeaderText(null);
        confirmQuit.setContentText("Are you sure you want to quit?");
        Optional<ButtonType> quit = confirmQuit.showAndWait();
        if (quit.get() == ButtonType.OK)
            System.exit(0);
    }

    public void processPowerButtonPush(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        switch(currentDevice) {
            case "Bose CD Player":
                if (!myCDPlayer.isOn())
                    dialog.setContentText(myCDPlayer.on());
                else
                    dialog.setContentText(myCDPlayer.off());
                break;
            case "Bose DVD Player":
                if (!dvdToCDAdapter.isOn())
                    dialog.setContentText(dvdToCDAdapter.on());
                else
                    dialog.setContentText(dvdToCDAdapter.off());
                break;
            case "Bose Radio":
                if (!radioToCDAdapter.isOn())
                    dialog.setContentText(radioToCDAdapter.on());
                else
                    dialog.setContentText(radioToCDAdapter.off());
                break;
            default:
                break;
        }
        if (!dialog.getContentText().equals(""))
            dialog.showAndWait();
    }

    public void processRewindButtonPush(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        switch(currentDevice) {
            case "Bose CD Player":
                dialog.setContentText(myCDPlayer.rewind());
                break;
            case "Bose DVD Player":
                dialog.setContentText(dvdToCDAdapter.rewind());
                break;
            case "Bose Radio":
                dialog.setContentText(radioToCDAdapter.rewind());
                break;
            default:
                break;
        }
        if (!dialog.getContentText().equals(""))
            dialog.showAndWait();
    }

    public void processPlayPauseButtonPush(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        switch(currentDevice) {
            case "Bose CD Player":
                if (!myCDPlayer.isPlaying())
                    dialog.setContentText(myCDPlayer.play());
                else
                    dialog.setContentText(myCDPlayer.pause());
                break;
            case "Bose DVD Player":
                if (!dvdToCDAdapter.isPlaying())
                    dialog.setContentText(dvdToCDAdapter.play());
                else
                    dialog.setContentText(dvdToCDAdapter.pause());
                break;
            case "Bose Radio":
                dialog.setContentText(radioToCDAdapter.play());
                break;
            default:
                break;
        }
        if (!dialog.getContentText().equals(""))
            dialog.showAndWait();
    }

    public void processForwardButtonPush(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        switch(currentDevice) {
            case "Bose CD Player":
                dialog.setContentText(myCDPlayer.forward());
                break;
            case "Bose DVD Player":
                dialog.setContentText(dvdToCDAdapter.forward());
                break;
            case "Bose Radio":
                dialog.setContentText(radioToCDAdapter.forward());
                break;
            default:
                break;
        }
        if (!dialog.getContentText().equals(""))
            dialog.showAndWait();
    }

    public void processVolDownButtonPush(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        switch(currentDevice) {
            case "Bose CD Player":
                dialog.setContentText(myCDPlayer.volumeDown());
                break;
            case "Bose DVD Player":
                dialog.setContentText(dvdToCDAdapter.volumeDown());
                break;
            case "Bose Radio":
                dialog.setContentText(radioToCDAdapter.volumeDown());
                break;
            default:
                break;
        }
        if (!dialog.getContentText().equals(""))
            dialog.showAndWait();
    }

    public void processMuteButtonPush(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        switch(currentDevice) {
            case "Bose CD Player":
                if (!myCDPlayer.isMuted())
                    dialog.setContentText(myCDPlayer.mute());
                else
                    dialog.setContentText(myCDPlayer.unMute());
                break;
            case "Bose DVD Player":
                if (!dvdToCDAdapter.isMuted())
                    dialog.setContentText(dvdToCDAdapter.mute());
                else
                    dialog.setContentText(dvdToCDAdapter.unMute());
                break;
            case "Bose Radio":
                if (!radioToCDAdapter.isMuted())
                    dialog.setContentText(radioToCDAdapter.mute());
                else
                    dialog.setContentText(radioToCDAdapter.unMute());
                break;
            default:
                break;
        }
        if (!dialog.getContentText().equals(""))
            dialog.showAndWait();
    }

    public void processVolUpButtonPush(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        switch(currentDevice) {
            case "Bose CD Player":
                dialog.setContentText(myCDPlayer.volumeUp());
                break;
            case "Bose DVD Player":
                dialog.setContentText(dvdToCDAdapter.volumeUp());
                break;
            case "Bose Radio":
                dialog.setContentText(radioToCDAdapter.volumeUp());
                break;
            default:
                break;
        }
        if (!dialog.getContentText().equals(""))
            dialog.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
