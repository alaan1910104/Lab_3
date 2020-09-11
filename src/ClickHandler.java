import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ClickHandler {
    private long clicks;
    private int newclik;
    private int clickAdd;
    private int clickCombo;
    private Label score;
    private Timeline tm;
    private int duration1;
    private int duration2;

    public ClickHandler(Label score){
        this.clicks = 0;
        this.newclik = 0;
        this.clickAdd = 0;
        this.clickCombo = 1;
        this.score = score;
        this.duration1 = 5;
        this.duration2 = 5;

        KeyFrame kf = new KeyFrame(Duration.seconds(this.duration1), event -> {add(); updateText(true);});
        this.tm = new Timeline();
        tm.setCycleCount(Timeline.INDEFINITE);
        tm.setAutoReverse(true);
        tm.getKeyFrames().add(kf);
        tm.setOnFinished((event) -> {System.out.print("done!");});
    }

    public void addClickAuto(){
        if(this.duration1 == 5){
            this.tm.play();
            this.duration1 = this.duration1 / 2;
        }
        else {
            this.duration1 = this.duration1 / 2;
        }
    }

    public void add(){
        setNewclik();
        this.clicks = this.clicks + this.newclik;
    }

    public void updateText(boolean possible){
        if(possible){
            this.score.setText("Clickys Clicked: " + this.clicks);
        }
        else{this.score.setText("Clicks Insoufisants!");}
    }

    public void buy(int cost){
        this.clicks = this.clicks - cost;
        if(this.clicks < 0){ this.clicks = 0;}
    }

    public void ultraClick(){
        this.clickAdd = this.clickAdd + 10000;
    }

    public long getClicks() {
        return clicks;
    }


    public int getNewclik() {
        return newclik;
    }

    public void setNewclik() {
        this.newclik = (1 + this.clickAdd) * this.clickCombo;
    }

    public int getClickAdd() {
        return clickAdd;
    }

    public void addClickAdd() {
        this.clickAdd = clickAdd + 1;
    }

    public int getClickCombo() {
        return clickCombo;
    }

    public void addClickCombo() {
        this.clickCombo = clickCombo + 1;
    }
}
