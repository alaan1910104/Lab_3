import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.util.Duration;

import java.util.List;

public class ClickHandler {
    private long clicks;
    private int newclik;
    private int clickAdd;
    private int clickCombo;
    private double duration1;
    private int clickssec;
    private int[] costs;
    private List<Labeled> lbld;
    private Timeline tm1;
    private Timeline tm2;


    // Constructeur
    public ClickHandler(List<Labeled> lbld){
        this.clicks = 0;
        this.newclik = 0;
        this.clickAdd = 0;
        this.clickCombo = 1;
        this.lbld = lbld;
        this.duration1 = 5;
        this.clickssec = 0;

        // costs pour les 5 upgrades
        this.costs = new int[]{10, 20, 30, 40, 50};

        // timeline pour les clicks automatiques
        this.tm1 = new Timeline();
        tm1.setCycleCount(Timeline.INDEFINITE);
        tm1.setAutoReverse(true);
        // timeline pour les points/sec
        this.tm2 = new Timeline();
        tm2.setCycleCount(Timeline.INDEFINITE);
        tm2.setAutoReverse(true);
    }

    // Augmenter les points
    public void add(){
        setNewclik();
        this.clicks = this.clicks + this.newclik;
        updateText(true);
    }

    // set le newclick pour montrer la quantite additioné dans le dernier click
    public void setNewclik() {
        this.newclik = (1 + this.clickAdd) * this.clickCombo;
        this.lbld.get(1).setText( "  (+" + this.newclik + ")");
    }

    // augmenter par 1 le points per click
    public void addClickAdd() {
        this.clickAdd = clickAdd + 1;
    }

    // donner des points automatiquement
    public void addClicksec(){
        this.tm2.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> { this.clicks = this.clicks + this.clickssec; updateText(true);})
        );
        clickssec++;
        this.tm2.play();
    }

    // donner une multiplicateur au points per click
    public void addClickCombo() {
        this.clickCombo = clickCombo + 1;
    }

    // faire un click automatiquement
    public void addClickAuto(){

        this.tm1.getKeyFrames().add(
                new KeyFrame(Duration.seconds(this.duration1), event -> {add();}));

        this.duration1 = this.duration1 / 2;
        this.tm1.play();
    }

    // donner une grose quantite de points per click
    public void ultraClick(){
        this.clickAdd = this.clickAdd + 10000;
    }

    // update le text pour le score et les buttons
    public void updateText(boolean possible){
        if(possible){
            this.lbld.get(0).setText("Clickys Clicked: " + this.clicks);
            this.lbld.get(2).setText( "+1 Clicky / click! Total: " + getClickAdd() + "\n $: " + this.costs[0]);
            this.lbld.get(3).setText( "+" + this.clickssec + " Clicky / sec!" + "\n $: " + this.costs[1]);
            this.lbld.get(4).setText( "Click automatique /  " + this.duration1 + " sec!" + "\n $: " + this.costs[2]);
            this.lbld.get(5).setText( "Clicky combo! Total: x" + getClickCombo() + "\n $: " + this.costs[3]);
            this.lbld.get(6).setText( "ULTRACLICK" + "\n $: " + this.costs[4]);
        }
        else{
            this.lbld.get(0).setText( "Clickys insouficents!");
        }
    }

    // soustraire le cost quand le usager achete un upgrade
    public void buy(int cost){
        this.clicks = this.clicks - cost;
        if(this.clicks < 0){ this.clicks = 0;}
        updateText(true);
    }


    // Getters

    public long getClicks() {
        return clicks;
    }

    public int getClickAdd() {
        return clickAdd;
    }

    public int[] getCosts() {
        return costs;
    }

    public int getClickCombo() {
        return clickCombo;
    }


}
