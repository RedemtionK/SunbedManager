package application;

import application.model.SunBedCollection;
import application.util.View;
import java.util.Objects;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * Main class for the Sun Lounger application. The application shows a
 * collection of sun beds that are available for hire for the day. Selecting a
 * sun bed marks it as hired, selecting it again makes it available.
 */
public class App extends Application {


	/** 
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
//		logger.info("JavaFX initializing");

		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/application/view/SunBedView.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application/resources/css/mainStyle.css")).toExternalForm());

			primaryStage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/application/resources/img/sunbed.png"))));
		
			primaryStage.setTitle("Sun Bed Manager");
			primaryStage.setScene(scene);
			primaryStage.show();

			View.centerOnScreen(primaryStage);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Before calling the JavaFX launch, ensure we can find and open the file used
	 * to hold the data. First try opening, if that fails, try creating and then
	 * opening the file. If that fails then terminate the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Access the file containing data for the application
		try {
			System.out.println("Read file");
			SunBedCollection.getInstance().readDataFromFile();
		}

		catch (Exception e) {
			try {
				System.out.println("Try writing file");
				SunBedCollection.getInstance().writeDataToFile();
				System.out.println("Now read file");
				SunBedCollection.getInstance().readDataFromFile();
			}

			catch (Exception ex) {
				ex.printStackTrace();
				System.exit(0);
			}
		}
		launch(args);
	}
}
