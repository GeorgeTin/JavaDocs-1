package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Aimandis on 6/30/2016.
 */
public class Shape extends AbstractShape implements ShapeBehaviour{
    protected int color;
    protected float saturation;

    public float getSaturation() {
        return saturation;
    }

    public int getColor() {
        return color;
    }



    public void setColor(int color) {
        this.color = color;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }


    public double area(){
        return 0;
    }
}
