import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage pstage){

        // initialization


        Label score= new Label();
        ClickHandler ch = new ClickHandler(score);
        ch.updateText(true);
        Label tempscore = new Label("  (+" + ch.getNewclik() + ")");

        Button btn = new Button("Clicky!");

        Button upgrd1 = new Button("+1 Clicky / click!");
        Button upgrd2 = new Button("+1 Clicky / 5 sec!");
        Button upgrd3 = new Button("Click automatique!");
        Button upgrd4 = new Button("Clicky combo!");
        Button upgrd5 = new Button("ULTRACLICK");


        Group root = new Group(btn, score, tempscore, upgrd1, upgrd2, upgrd3, upgrd4, upgrd5);



        // labels size
        tempscore.setTranslateX(150);
        score.setScaleY(1.5);


        //btn
        btn.setMinSize(80, 145);
        btn.setTranslateY(20);
        btn.setOnAction(event -> {
            ch.add();
            score.setText("Clickys Clicked: " + ch.getClicks());
            tempscore.setText("  (+" + ch.getNewclik() + ")");
        });


        // Upgrade 1
        upgrd1.setMinSize(40,20);
        upgrd1.setTranslateY(20);
        upgrd1.setTranslateX(100);
        upgrd1.setOnAction(event -> { action(1, ch);});

        // Upgrade 2
        upgrd2.setMinSize(40,20);
        upgrd2.setTranslateY(50);
        upgrd2.setTranslateX(100);
        upgrd2.setOnAction(event -> { action(2, ch);});

        // Upgrade 3
        upgrd3.setMinSize(40, 20);
        upgrd3.setTranslateY(80);
        upgrd3.setTranslateX(100);
        upgrd3.setOnAction(event -> { action(3, ch);});

        // Upgrade 4
        upgrd4.setMinSize(40,20);
        upgrd4.setTranslateY(110);
        upgrd4.setTranslateX(100);
        upgrd4.setOnAction(event -> { action(4, ch);});

        // Upgrade 5
        upgrd5.setMinSize(40,20);
        upgrd5.setTranslateY(140);
        upgrd5.setTranslateX(100);
        upgrd5.setOnAction(event -> { action(5, ch);});

        //Set and show
        pstage.setTitle("ButtonClicker!");
        pstage.setScene(new Scene(root));
        pstage.show();

    }

    public static void action(int btnumb, ClickHandler ch){

        int cost = 0;

        switch (btnumb){
            case 1: cost = 10;
            break;
            case 2: cost = 20;
            break;
            case 3: cost = 30;
            break;
            case 4: cost = 40;
            break;
            default: cost = 50;
        }

        boolean reussi = ch.getClicks() >= cost;
        if(reussi){

            switch(btnumb){
                case 1: ch.addClickAdd();
                break;
                case 2: ;
                break;
                case 3: ch.addClickAuto();
                break;
                case 4: ch.addClickCombo();
                break;
                case 5: ch.ultraClick();
                break;
            }
            ch.buy(cost);
        }
        ch.updateText(reussi);
    }

}
