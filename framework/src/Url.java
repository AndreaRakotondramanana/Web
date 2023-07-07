package etu1831.framework;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {
    String method() default "your method is empty";
}