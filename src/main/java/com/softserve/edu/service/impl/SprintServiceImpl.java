package com.softserve.edu.service.impl;


import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Sprint;
import com.softserve.edu.repository.MarathonRepository;
import com.softserve.edu.repository.SprintRepository;
import com.softserve.edu.service.SprintService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;
    private final MarathonRepository marathonRepository;

    public List<Sprint> getSprintsByMarathonId(Long id){
        return sprintRepository.getAllSprintsByMarathonId(id);
    }

    public boolean addSprintToMarathon(Sprint sprint, @NotNull Marathon marathon){

        if (sprint.getId() == null) {
            Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
            if (!sprintRepository.findFirstByTitleAndMarathon(sprint.getTitle(), marathonEntity).isPresent()) {
                sprint.setMarathon(marathonEntity);
                sprintRepository.save(sprint);
                marathonEntity.getSprints().add(sprint);
                return marathonRepository.save(marathonEntity) != null;
            }
            }
        return false;
    }

    @Override
    public boolean updateSprint(Sprint sprint) {
        return false;
    }

    @Override
    public Sprint getSprintById(Long id) {
        Optional<Sprint> sprint = sprintRepository.findById(id);
        return sprint.get();// new EntityNotFoundException("No marathon exist for given id");
    }
}
