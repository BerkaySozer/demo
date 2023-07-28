package com.Project.api.controller;


import com.Project.api.entity.Lecture;
import com.Project.api.service.ILectureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lectures")
public class LectureControl {
    private final ILectureService lectureService;

    public LectureControl(ILectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping
    ResponseEntity<Page<Lecture>> getLectures(@RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseEntity.ok(lectureService.getAll(PageRequest.of(page, pageSize, Sort.by("id"))));
    }

    @PostMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture){
        return ResponseEntity.ok(lectureService.save(lecture));
    }

    @GetMapping("/{id}")
    ResponseEntity<Lecture> getLecture(@PathVariable Integer id){
        return ResponseEntity.ok(lectureService.getById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteLecture(@PathVariable Integer id){
        lectureService.delete(id);
        return ResponseEntity.ok().build();
    }

}
