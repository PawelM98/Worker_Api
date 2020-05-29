package com.michcinski.azu.repo;

import com.michcinski.azu.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkerRepo extends JpaRepository<Worker,Long> {

    Worker findWorkerById(Long id);
    List<Worker> findWorkersBySpecialization(String specialization);
}
