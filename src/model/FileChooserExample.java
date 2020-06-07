package model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class FileChooserExample extends Application {

	private File matrixFile;
	private GridPane inputGridPane;

	@Override
	public void start(final Stage stage) {
		stage.setTitle("File Chooser Sample");

		final FileChooser fileChooser = new FileChooser();

		final Button openButton = new Button("import Matrix");
		final Button runButton = new Button("run");

		openButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				matrixFile = fileChooser.showOpenDialog(stage);
			}
		});

		runButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				if (matrixFile != null)
					matrixReader(matrixFile);
				else
					new Alert(Alert.AlertType.INFORMATION, "please import the matrix first", ButtonType.OK).show();
			}
		});

		inputGridPane = new GridPane();

		GridPane.setConstraints(openButton, 0, 0);
		GridPane.setConstraints(runButton, 1, 0);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(openButton, runButton);

		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));

		stage.setScene(new Scene(rootGroup));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	void matrixReader(File matrix) {

	}
}