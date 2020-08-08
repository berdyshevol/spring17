package com.softserve.edu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

@Slf4j
public class DeleteDisableException extends DataIntegrityViolationException {
    public DeleteDisableException(Class<?> clazz){
        super("Unable to delete " + clazz.getSimpleName().toLowerCase());
        log.warn("Unable to delete {}", clazz.getSimpleName().toLowerCase());
    }
}