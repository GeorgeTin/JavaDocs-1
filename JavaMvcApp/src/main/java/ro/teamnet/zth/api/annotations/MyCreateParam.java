package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

/**
 * Created by Aimandis on 7/18/2016.
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCreateParam {
    String name();
}
