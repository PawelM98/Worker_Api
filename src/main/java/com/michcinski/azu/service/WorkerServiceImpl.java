package com.michcinski.azu.service;

import com.michcinski.azu.repo.WorkerRepo;
import com.michcinski.azu.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WorkerServiceImpl implements WorkerService{

    private WorkerRepo workerRepo;

    @Autowired
    public WorkerServiceImpl(WorkerRepo workerRepo) {
        this.workerRepo = workerRepo;
    }

    @Override
    public void modifyWorker(Long id, Worker modifiedWorker) {
        Worker found = workerRepo.findWorkerById(id);
        found.setFirstName(modifiedWorker.getFirstName());
        found.setSurName(modifiedWorker.getSurName());
        found.setEmail(modifiedWorker.getEmail());
        found.setUsername(modifiedWorker.getUsername());
        found.setPassword(modifiedWorker.getPassword());
        found.setPhoneNumber(modifiedWorker.getPhoneNumber());
        found.setSpecialization(modifiedWorker.getSpecialization());
        found.setWorkingHours(modifiedWorker.getWorkingHours());
        workerRepo.save(found);
    }
}
