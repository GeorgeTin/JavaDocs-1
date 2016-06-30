package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Aimandis on 6/30/2016.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;

    public Circle(){
        xPos = 0;
        yPos = 0;
        radius = 5;
    }

    public Circle(int radius){
        this.radius = radius;
    }

    public Circle(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public double area(){
        return Math.PI*radius*radius;
    }

    public String toString(){
        return "center = ("+xPos+","+yPos+") and radius = " + radius;
    }

    public void fillColour(){
        System.out.println(getColor());
    }

    public void fillColour(int color){
        setColor(color);
        System.out.println("The circle color is now " + getColor());
    }

    public void fillColour(float saturation){
        setSaturation(saturation);
    }
}
