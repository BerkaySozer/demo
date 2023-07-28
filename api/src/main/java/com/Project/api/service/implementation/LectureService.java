package com.Project.api.service.implementation;

import com.Project.api.common.GeneralException;
import com.Project.api.entity.Lecture;
import com.Project.api.entity.User;
import com.Project.api.repository.ILectureRepo;
import com.Project.api.service.ILectureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService implements ILectureService {

    private final ILectureRepo lectureRepo;

    public LectureService(ILectureRepo lectureRepo) {
        this.lectureRepo = lectureRepo;
    }

    @Override
    public Lecture save(Lecture lecture) {
        if (StringUtils.isEmpty(lecture.getName())) {
            throw new GeneralException("Name can not be empty");
        }
        if (lecture.getTeacher() == null) {
            throw new GeneralException("Teacher can not be empty");
        }
        return lectureRepo.save(lecture);
    }

    @Override
    public Lecture getById(Integer id) throws GeneralException {

        Optional<Lecture> lectureOptional = lectureRepo.findById(id);
        if (lectureOptional.isEmpty()) {
            throw new GeneralException("Lecture Not Found");
        }
        return lectureOptional.get();
    }

    @Override
    public List<Lecture> getAll() {
        return lectureRepo.findAll();
    }

    @Override
    public Page<Lecture> getAll(Pageable pageable) {
        return lectureRepo.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!lectureRepo.existsById(id)) {
            throw new GeneralException("Lecture not Found");
        }
        lectureRepo.deleteById(id);
    }
}
