package com.ltnc.be.rest.exception;

import com.ltnc.be.domain.BaseException;
import io.micrometer.common.lang.NonNull;

public class EmployeeLimitExceededException extends BaseException {
    public EmployeeLimitExceededException(@NonNull final String entityCaption) {
        super("Employee limit exceeded for " + entityCaption, "EMPLOYEE_LIMIT_EXCEEDED");
    }
}