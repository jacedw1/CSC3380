package project.applications.GeneralApps.CloseUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class CloseController {

    @FXML
    private Label timeLabel;

    public void startCountdown(){

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(timeLabel.getText());
                int time = Integer.parseInt(timeLabel.getText()) - 1;
                timeLabel.setText(Integer.toString(time));
                if(time == 0){
                    timeLabel.getScene().getWindow().hide();
                    this.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}
