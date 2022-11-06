package com.apurba.relevel.repository;

import com.apurba.relevel.model.*;
import com.builder.resume.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepo extends JpaRepository<Resume, Integer> {
}
