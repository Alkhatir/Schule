package model;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

/**
 * 
 * @author Peer Hanna
 *
 */
public final class CalculatorGUI extends Application {

	private File matrixFile;
	private BorderPane mainPane;

	@Override
	public void start(final Stage stage) {
		stage.setTitle("Matrix calculator");

		final FileChooser fileChooser = new FileChooser();

		final Button openButton = new Button("import Matrix");
		final Button runButton = new Button("run");
		TextArea potIn = new TextArea("please enter here the power of the matrix");
		potIn.setEditable(true);
		potIn.setMaxSize(300.0, 5.0);

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
					if (!potIn.getText().isEmpty())
						try {
							Integer.parseInt(potIn.getText());
							matrixReader(matrixFile, Integer.parseInt(potIn.getText()));
						} catch (Exception ex) {
							new Alert(Alert.AlertType.ERROR, "Wrong type! it hast to be an integer", ButtonType.OK)
									.show();
						}
					else
						new Alert(Alert.AlertType.INFORMATION, "please write a number", ButtonType.OK).show();
				else
					new Alert(Alert.AlertType.INFORMATION, "please import the matrix first", ButtonType.OK).show();
			}
		});

		mainPane = new BorderPane();
		FlowPane topPane = new FlowPane();
		topPane.getChildren().addAll(potIn, openButton, runButton);
		mainPane.setTop(topPane);
		topPane.setHgap(12.0);
		topPane.setVgap(12.0);
		topPane.setPadding(new Insets(12.0));

		stage.setScene(new Scene(mainPane, 800.0, 950.0));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	void matrixReader(File matrix, int c) {
		try {
			MatrixInput mat = new MatrixInput(matrix);
			Matrix m = new Matrix(mat.rowMatrixLength());
			for (int i = 0; i < mat.rowMatrixLength(); i++)
				for (int y = 0; y < mat.rowMatrixLength(); y++)
					m.fillMatrix(i, y, mat.getMatrix()[i][y]);
			m.distanceMatrixCreator();
			m.potenzMatrixCreator(c - 1);
			System.out.println(m);
			m.showExzentrizitaeten();
			GridPane everyThing = new GridPane();
			GridPane adjMatrix = new GridPane();
			for (int i = 0; i < mat.rowMatrixLength(); i++)
				for (int y = 0; y < mat.rowMatrixLength(); y++) {
					TextField tf = new TextField();
					tf.setPrefHeight(30);
					tf.setPrefWidth(30);
					tf.setAlignment(Pos.CENTER);
					tf.setEditable(false);
					tf.setText(String.valueOf(m.getM()[i][y]));
					GridPane.setRowIndex(tf, i);
					GridPane.setColumnIndex(tf, y);
					adjMatrix.getChildren().add(tf);
				}
			GridPane potMatrix = new GridPane();
			for (int i = 0; i < mat.rowMatrixLength(); i++)
				for (int y = 0; y < mat.rowMatrixLength(); y++) {
					TextField tf = new TextField();
					tf.setPrefHeight(30);
					tf.setPrefWidth(30);
					tf.setAlignment(Pos.CENTER);
					tf.setEditable(false);
					tf.setText(String.valueOf(m.getPm()[i][y]));
					GridPane.setRowIndex(tf, i);
					GridPane.setColumnIndex(tf, y);
					potMatrix.getChildren().add(tf);
				}
			GridPane distMatrix = new GridPane();
			for (int i = 0; i < mat.rowMatrixLength(); i++)
				for (int y = 0; y < mat.rowMatrixLength(); y++) {
					TextField tf = new TextField();
					tf.setPrefHeight(30);
					tf.setPrefWidth(30);
					tf.setAlignment(Pos.CENTER);
					tf.setEditable(false);
					tf.setText(String.valueOf(m.getDm()[i][y]));
					GridPane.setRowIndex(tf, i);
					GridPane.setColumnIndex(tf, y);
					distMatrix.getChildren().add(tf);
				}

			everyThing.add(new TextField("Adjazenzmatrix ist "), 0, 0);
			everyThing.add(adjMatrix, 1, 0);
			everyThing.add(new TextField("Potenzmatrix ist "), 0, 1);
			everyThing.add(potMatrix, 1, 1);
			everyThing.add(new TextField("Distanzmatrix ist "), 0, 2);
			everyThing.add(distMatrix, 1, 2);
			everyThing.setHgap(12.0);
			everyThing.setVgap(12.0);
			String str = "\n";
			for (int[] i : m.komponenten(m.getM())) {
				if (i.length >= 1)
					str += "Der Komponente " + m.komponenten(m.getM()).indexOf(i) + " sind die Knoten ";
				for (int y = 0; y < i.length; y++)
					str += "[" + i[y] + "]";
				str += "\n";
			}
			String str1 = "\n";
			for (IntPairs i : m.bruecken()) {
				str1 += "Ein Brueck ist " + i.toString() + "\n";
			}
			ScrollPane botPane = new ScrollPane(new Text(m.showExzentrizitaeten() + str + str1
					+ "die Artikulationen sind " + m.artikulationen().toString()));
			botPane.setHbarPolicy(ScrollBarPolicy.NEVER);
			botPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			VBox box = new VBox();
			box.getChildren().add(botPane);
			VBox.setVgrow(botPane, Priority.ALWAYS);
			botPane.setMaxSize(500.0, 500.0);
			mainPane.setRight(box);
			mainPane.setCenter(everyThing);
		} catch (IOException e) {
			new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK).show();
		}

	}
}