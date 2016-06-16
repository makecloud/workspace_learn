package com.liuyihui.lrn.annotation;

import org.springframework.stereotype.Repository;

@Repository
public @interface MybatisRepository {
	String value() default "";
}
