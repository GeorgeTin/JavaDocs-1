package ro.teamnet.zerotohero.oop.runapp;

import ro.teamnet.zerotohero.MyException;
import ro.teamnet.zerotohero.NestedException;
import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.oop.graphicshape.*;

/**
 * Created by Aimandis on 6/30/2016.
 */
public class RunApp {

    public static void show() throws MyException{
        System.out.println("Show() called");
        throw new MyException();
    }

    public static void doSmt() throws NestedException{
        try{
            RunApp.show();
        }
        catch (MyException e){
            throw new NestedException();
        }
    }

    public static void main(String[] args) {
        Circles circles = new Circles();
        System.out.println("The default circle area is " + circles.getAreaPub());
        circles.getAreaDef();

        //Canvas canvas = new Canvas();

        Shape shape = new Circle(10);
        System.out.println(shape.area());

        ShapeBehaviour shapeBehaviour = new Square(10);
        System.out.println(shapeBehaviour.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));

        try{
            RunApp.doSmt();
        }
        catch (NestedException e){
            System.out.println(e.date);
        }
    }
}
