package th.co.priorsolution.springboot.novice.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import th.co.priorsolution.springboot.novice.serialization.HashJsonSerialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
@JsonSerialize(using = HashJsonSerialization.class)
public @interface Hash {

	boolean enableHash() default true;
}
