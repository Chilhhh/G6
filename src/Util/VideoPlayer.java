package Util;

import java.io.File;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class VideoPlayer extends Application {
    private MediaPlayer player;
    private Media media;
    private Label time = new Label();
    private double allTime = 0;
    private Slider playSlider;
    private Slider audioSlider = new Slider(0, 100, 80);
    private boolean isplay;
 
    public VideoPlayer() {
        // 默认初始化标题及其文件URL
        String videoPath = "resources/video/video.mp4";
        Media media = new Media(new File(videoPath).toURI().toString());
        this.media = media;
    }
    public VideoPlayer(String videoPath) {
        this.media = new Media(new File(videoPath).toURI().toString());
    }

    // public void setVideoPath(String videoPath){
    //     Media media = new Media(new File(videoPath).toURI().toString());
    //     this.media = media;
    // }
 
    @Override
    public void start(Stage primaryStage) {
        player = new MediaPlayer(media);
        player.play();
        isplay = true;
        MediaView mediaView = new MediaView(player);
 
        VBox vbox = new VBox(mediaView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(800);
        vbox.setPrefHeight(600);
        vbox.setStyle("-fx-background-color: #000;");
        BorderPane root = new BorderPane(vbox);
 
        root.setPrefWidth(800);
        root.setPrefHeight(600);
        player.volumeProperty().bind(audioSlider.valueProperty().divide(100));
 
 
        //视频进度条
        playSlider = new Slider();
        playSlider.setMin(0);
        playSlider.setMax(500);
        playSlider.setPrefWidth(800);
        playSlider.setValue(0);
        VBox.setMargin(playSlider, new Insets(-50, 0, 0, 0));
        vbox.getChildren().add(playSlider);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        player.currentTimeProperty().addListener((x, y, z) -> {
            if (isplay) {
                double currents = player.getCurrentTime().toSeconds();
                allTime = player.getStopTime().toSeconds();
                playSlider.setValue(currents / allTime * 500);
                time.setText(formattime(currents, allTime, 0));
            }
        });
 
        playSlider.setOnMousePressed(x -> {
            isplay = false;
        });
        playSlider.setOnMouseReleased(x -> {
            player.seek(Duration.seconds(playSlider.getValue() / 500 * allTime));
            isplay = true;
        });
        ChangeListener<Number> changeListener = (event, oldVal, newVal) -> {
            System.out.println("oldVal = " + oldVal + ", newVal = " + newVal);
            mediaView.setFitHeight(primaryStage.getHeight() - 40);
            mediaView.setFitWidth(primaryStage.getWidth());
        };
        primaryStage.widthProperty().addListener(changeListener);
        primaryStage.heightProperty().addListener(changeListener);
    
 
        //空格或点击停止播放
        root.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
            if (KeyCode.SPACE.equals(event.getCode())) {
                playOrStop();
            }
        });
        root.setOnMouseClicked(event -> {
            playOrStop();
        });
 
        // 关闭窗口时停止播放
        primaryStage.setOnCloseRequest(event -> {
            player.pause();
            player.stop();
        });
 
        // mediaView.setFitHeight(primaryStage.getHeight() - 40);
        // mediaView.setFitHeight(primaryStage.getHeight());
        mediaView.setFitWidth(primaryStage.getWidth());
    }
 
    //控制视频播放和停止
    private void playOrStop() {
        if (isplay) {
            isplay = false;
            player.pause();
        } else {
            Duration seconds = Duration.seconds(playSlider.getValue() / 500 * allTime);
            player.seek(seconds);
            isplay = true;
            player.play();
        }
    }
 

    public static String formattime(double this_time, double all_time, int type) {
        String thistime = String.format("%02d:%02d:%02d", (int) this_time / 3600, (int) this_time % 3600 / 60, (int) this_time % 60);
        String alltime = String.format("%02d:%02d:%02d", (int) all_time / 3600, (int) all_time % 3600 / 60, (int) all_time % 60);
        return type == 1 ? thistime : type == 2 ? alltime : thistime + "/" + alltime;
    }
}