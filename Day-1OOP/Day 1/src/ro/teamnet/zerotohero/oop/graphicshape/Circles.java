package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Aimandis on 6/30/2016.
 */
public class Circles {

    public double getAreaPub(){
        Circle circle = new Circle();
        return circle.area();

    }

    public void getAreaDef(){
        Circle circle = new Circle();
        circle.fillColour();
        circle.fillColour(0.15f);
        circle.fillColour(15);
    }
}
