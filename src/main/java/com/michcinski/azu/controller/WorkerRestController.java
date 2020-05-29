package com.michcinski.azu.controller;

import com.michcinski.azu.repo.WorkerRepo;
import com.michcinski.azu.model.Worker;
import com.michcinski.azu.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class WorkerRestController {

    private WorkerRepo workerRepo;
    private WorkerService workerService;

    @Autowired
    public WorkerRestController(WorkerRepo workerRepo, WorkerService workerService) {
        this.workerRepo = workerRepo;
        this.workerService = workerService;
    }

    @GetMapping(value = "/getWorkersList",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Worker>> getWorkersList(){
        if(!workerRepo.findAll().isEmpty()) {
            return new ResponseEntity<>(workerRepo.findAll(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getWorkerById/{id}")
    public ResponseEntity<Worker> getOne(@PathVariable Long id){
        if(workerRepo.findWorkerById(id) != null){
            return new ResponseEntity<>(workerRepo.findWorkerById(id),HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getWorkerBySpecialization/{specialization}")
    public ResponseEntity<List<Worker>> getWorkersBySpecialization(@PathVariable String specialization){
        if(!workerRepo.findWorkersBySpecialization(specialization).isEmpty()){
            return new ResponseEntity<>(workerRepo.findWorkersBySpecialization(specialization),HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addNewWorker")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker newWorker){
        if(workerRepo.findWorkerById(newWorker.getId()) == null){
            workerRepo.save(newWorker);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else
            return new ResponseEntity<>(HttpStatus.IM_USED);
    }

    @PutMapping("/editWorkerData/{id}")
    public ResponseEntity<Worker> modifyWorker(@PathVariable Long id,@RequestBody Worker modifiedWorker){
        Worker found = workerRepo.findWorkerById(id);
        if(found != null){
            workerService.modifyWorker(found.getId(),modifiedWorker);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
