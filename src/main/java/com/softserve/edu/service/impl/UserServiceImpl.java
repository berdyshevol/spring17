package com.softserve.edu.service.impl;

import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.MarathonRepository;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MarathonRepository marathonRepository;

    public UserServiceImpl(UserRepository userRepository,
                           MarathonRepository marathonRepository) {
        this.userRepository = userRepository;
        this.marathonRepository = marathonRepository;
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No user /w id "+id)));
    }

    public User createOrUpdateUser(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean addUserToMarathon(User user, Marathon marathon) {
        User userEntity = userRepository.getOne(user.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getUsers().add(userEntity);
        return marathonRepository.save(marathonEntity) != null;
    }

    @Override
    public boolean deleteUserFromMarathon(User user, Marathon marathon) {
        User userEntity = userRepository.getOne(user.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getUsers().remove(userEntity);
        return marathonRepository.save(marathonEntity) != null;
    }
}
