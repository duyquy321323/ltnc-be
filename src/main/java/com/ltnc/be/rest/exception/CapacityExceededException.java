package com.ltnc.be.rest.exception;

import com.ltnc.be.domain.BaseException;
import lombok.NonNull;

public class CapacityExceededException extends BaseException {
    public CapacityExceededException(@NonNull final String entityCaption) {
        super("Capacity exceeded for " + entityCaption, "CAPACITY_EXCEEDED");
    }
}