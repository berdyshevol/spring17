package com.softserve.edu.service.impl;

import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Marathon;
import com.softserve.edu.repository.MarathonRepository;
import com.softserve.edu.service.MarathonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarathonServiceImpl implements MarathonService {

    private final MarathonRepository marathonRepository;

    public MarathonServiceImpl(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Transactional
    public List<Marathon> getAll() {
        List<Marathon> marathons = marathonRepository.findAll();
        return marathons.isEmpty() ? new ArrayList<>() : marathons;
    }

    public Marathon getMarathonById(Long id)  {
        return marathonRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No marathon /w id "+id)));// add to Logger later
  }

    public Marathon createOrUpdate(Marathon marathon)  {
        if (marathon.getId() != null) {

            Optional<Marathon> marathonOptional = marathonRepository.findById(marathon.getId());

            if (marathonOptional.isPresent()) {
                Marathon newMarathon = marathonOptional.get();
                newMarathon.setTitle(marathon.getTitle());
                return marathonRepository.save(newMarathon);
            }
        }

        return marathonRepository.save(marathon);
    }

    public void deleteMarathonById(Long id)
    {
        Optional<Marathon> marathon = marathonRepository.findById(id);

        if(marathon.isPresent()){
            marathonRepository.deleteById(id);
        } else{
            throw new EntityNotFoundException("No marathon exist for given id");
        }
    }
}
