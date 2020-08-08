package com.softserve.edu.repository;

import com.softserve.edu.model.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarathonRepository extends JpaRepository<Marathon, Long> {
}
