package com.softserve.edu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DeleteDisableException.class)
    public ModelAndView handleDeleteException(HttpServletRequest request, DeleteDisableException ex){
        log.info("Exception raised = {} :: URL = {}", ex.getMessage(), request.getRequestURL());
        ModelAndView mav = new ModelAndView("/error/400");
        mav.setStatus(HttpStatus.BAD_REQUEST);
        mav.addObject("error", ex.getMessage());
        return mav;
    }

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason = "Exception occurred")
    @ExceptionHandler(Exception.class)
    public void handle500(HttpServletRequest request, Exception ex){
        log.error("Exception raised = {} :: URL = {}", ex.getMessage(), request.getRequestURL());
    }

}