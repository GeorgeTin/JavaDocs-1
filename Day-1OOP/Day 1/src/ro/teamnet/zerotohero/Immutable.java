package ro.teamnet.zerotohero;

/**
 * Created by Aimandis on 6/30/2016.
 */
public final class Immutable {
    private final String name;
    private final int age;

    public Immutable(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }
}
