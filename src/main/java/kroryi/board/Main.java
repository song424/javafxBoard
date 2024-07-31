package kroryi.board;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kroryi.board.controller.UI;
import kroryi.board.util.SceneUtil;

import java.io.IOException;
import java.net.URL;

//메인수정

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LOGIN.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 880, 600);

        URL cssUrl = getClass().getResource("styles.css");

        scene.getStylesheets().add(cssUrl.toString());

        stage.setTitle("JavaFX Board!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

//        stage.setOnCloseRequest(event->{
//            event.consume();
//        });
    }

    public static void main(String[] args) {
        launch();
    }


}