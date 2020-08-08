package com.softserve.edu.service.impl;


import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Progress;
import com.softserve.edu.model.Task;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.ProgressRepository;
import com.softserve.edu.repository.SprintRepository;
import com.softserve.edu.repository.TaskRepository;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.ProgressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    final ProgressRepository progressRepository;
    final UserRepository userRepository;
    final SprintRepository sprintRepository;
    final TaskRepository taskRepository;

    @Override
    public Progress getProgressById(Long id) {
        return progressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No Progress exist for given id")));
    }

    @Override
    @Transactional
    public Progress addTaskForStudent(Task task, User user) {
        Progress progress  = new Progress();
        progress.setTrainee(userRepository.getOne(user.getId()));
        progress.setTask(taskRepository.getOne(task.getId()));
        return progressRepository.save(progress);
    }

    @Override
    @Transactional
    public Progress addOrUpdateProgress(Progress progress) {
            return progressRepository.save(progress);
    }

    @Override
    @Transactional
    public boolean setStatus(Progress.TaskStatus status, Progress progress) {

        Optional<Progress> progressEntityOpt = progressRepository.findById(progress.getId());
        if (progressEntityOpt.isPresent()) {
            Progress progressEntity = progressEntityOpt.get();
            if (progressEntity.getStatus() != status) {
                progressEntity.setStatus(status);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId) {
        return progressRepository.allProgressByUserIdAndMarathonId(userId, marathonId);
    }

    @Override
    @Transactional
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
        return progressRepository.allProgressByUserIdAndSprintId(userId, sprintId);
    }
}
