package com.Project.api.repository;

import com.Project.api.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILectureRepo extends JpaRepository<Lecture, Integer> {
    Page<Lecture> findAllBy(Pageable pageable);
}
