package ro.teamnet.zerotohero;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Aimandis on 6/30/2016.
 */
public class NestedException extends Exception {
    public String date;
    public NestedException(){
        date = LocalDateTime.now().toString();
        System.out.println("NestedException creted");
    }
}
