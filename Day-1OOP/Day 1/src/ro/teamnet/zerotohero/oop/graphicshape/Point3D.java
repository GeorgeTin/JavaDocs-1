package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Aimandis on 6/30/2016.
 */
public class Point3D extends Point {
    private int zPos;

    public Point3D(int zPos, int xPos, int yPos){
        super(xPos, yPos);
        this.zPos = zPos;
    }
}
