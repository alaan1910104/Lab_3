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
import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage pstage){

        // initialization

        List<Labeled> lbld = new ArrayList<>();




        Label score= new Label();
        lbld.add(score);
        Label tempscore = new Label();
        lbld.add(tempscore);


        Button btn = new Button("Clicky!");
        Button upgrd1 = new Button();
        lbld.add(upgrd1);
        Button upgrd2 = new Button();
        lbld.add(upgrd2);
        Button upgrd3 = new Button();
        lbld.add(upgrd3);
        Button upgrd4 = new Button();
        lbld.add(upgrd4);
        Button upgrd5 = new Button();
        lbld.add(upgrd5);


        ClickHandler ch = new ClickHandler(lbld);

        ch.updateText(true);


        Group root = new Group(btn, score, tempscore, upgrd1, upgrd2, upgrd3, upgrd4, upgrd5);



        // labels size
        tempscore.setTranslateX(150);
        score.setScaleY(1.5);


        int dif = 25;
        int height = 40;
        int width = 120;

        //btn
        btn.setMinSize((width + 50), (5 * dif) + (5 * height));
        btn.setTranslateY(20);
        btn.setOnAction(event -> {
            ch.add();
        });

        // Upgrade 1
        upgrd1.setMinSize(width,height);
        upgrd1.setTranslateY(dif);
        upgrd1.setTranslateX(200);
        upgrd1.setOnAction(event -> { action(1, ch);});

        // Upgrade 2
        upgrd2.setMinSize(width,height);
        upgrd2.setTranslateY( (2 * dif) + height);
        upgrd2.setTranslateX(200);
        upgrd2.setOnAction(event -> { action(2, ch);});

        // Upgrade 3
        upgrd3.setMinSize(width, height);
        upgrd3.setTranslateY((3 * dif) + (2 * height));
        upgrd3.setTranslateX(200);
        upgrd3.setOnAction(event -> { action(3, ch);});

        // Upgrade 4
        upgrd4.setMinSize(width, height);
        upgrd4.setTranslateY((4 * dif) + (3 * height));
        upgrd4.setTranslateX(200);
        upgrd4.setOnAction(event -> { action(4, ch);});

        // Upgrade 5
        upgrd5.setMinSize(width, height);
        upgrd5.setTranslateY((5 * dif) + (4 * height));
        upgrd5.setTranslateX(200);
        upgrd5.setOnAction(event -> { action(5, ch);});

        //Set and show
        pstage.setTitle("ButtonClicker!");
        pstage.setScene(new Scene(root));
        pstage.show();

    }

    public static void action(int btnumb, ClickHandler ch){

        boolean reussi = ch.getClicks() >= ch.getCosts()[btnumb - 1];
        if(reussi){

            switch(btnumb){
                case 1: ch.addClickAdd();
                break;
                case 2: ch.addClicksec();
                break;
                case 3: ch.addClickAuto();
                break;
                case 4: ch.addClickCombo();
                break;
                case 5: ch.ultraClick();
                break;
            }
            ch.buy(ch.getCosts()[btnumb - 1]);
            ch.getCosts()[btnumb - 1] = ch.getCosts()[btnumb - 1] * 2;
        }
        ch.updateText(reussi);
    }

}
