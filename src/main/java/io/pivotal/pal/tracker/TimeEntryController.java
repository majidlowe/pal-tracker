package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    public TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }
    @GetMapping("")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> temp=this.timeEntryRepository.list();
        return new ResponseEntity(temp, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry temp = this.timeEntryRepository.find(id);
        if(temp == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity(temp, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        this.timeEntryRepository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = this.timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntry); //status 201

    }

    public ResponseEntity<TimeEntry> update(long timeEntryId, TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = this.timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if(timeEntry == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEntry); // needs status 200

    }

}