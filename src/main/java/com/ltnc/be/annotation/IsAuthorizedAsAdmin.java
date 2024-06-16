package com.ltnc.be.annotation;

import java.lang.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('ADMIN')")
public @interface IsAuthorizedAsAdmin {}
