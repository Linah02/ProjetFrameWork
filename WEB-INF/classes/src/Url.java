package etu001982.framework.myAnnotations;
import java.lang.annotation.*;
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {
    String name () default "";
}