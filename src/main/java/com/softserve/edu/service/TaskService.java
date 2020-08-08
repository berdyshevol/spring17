package com.softserve.edu.service;


import com.softserve.edu.model.Sprint;
import com.softserve.edu.model.Task;

public interface TaskService {
    Task addTaskToSprint(Task task, Sprint sprint);
    Task getTaskById(Long id);
}
