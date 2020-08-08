package com.softserve.edu.service;



import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Sprint;

import java.util.List;

public interface SprintService {
     List<Sprint> getSprintsByMarathonId(Long id);
     boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
     boolean updateSprint(Sprint sprint);
     Sprint getSprintById(Long id);
}
