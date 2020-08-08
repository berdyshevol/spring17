package com.softserve.edu.service;

import com.softserve.edu.model.Marathon;

import java.util.List;

public interface MarathonService {
    List<Marathon> getAll();
    Marathon getMarathonById(Long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(Long id);
}
