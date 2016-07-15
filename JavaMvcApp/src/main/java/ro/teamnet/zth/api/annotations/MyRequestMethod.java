package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

/**
 * Created by Aimandis on 7/14/2016.
 */

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRequestMethod {
    String urlPath();
    String methodType() default "GET";
    String id() default "";

}
