package br.com.cs.feign.api.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.cs.feign.api.constant.ReopenStenEnum;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface ReopenStep {
	ReopenStenEnum step();
	ReopenStenEnum[] nextStep() default {};
}