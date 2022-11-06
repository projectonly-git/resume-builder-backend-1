package com.apurba.relevel.service;

import com.apurba.relevel.model.*;


import com.apurba.relevel.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {
    @Autowired
    UserRepo repo;

    @Autowired
    ResumeRepo resume_repo;
    @Autowired
    EducationRepo edu_repo;
    @Autowired
    ExperienceRepo exp_repo;

    //User ops
    public boolean addUser(User newUser) {
        // TODO Auto-generated method stub
        if(repo.findById(newUser.getEmailid()).equals(Optional.empty())) {
            repo.save(newUser);
            return true;
        }
        return false;
    }

    public User getUserById(String email) {
        // TODO Auto-generated method stub
        try {
            User opuser = repo.findByemailid(email);
            if(opuser != null)
                return opuser;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return null;
    }

    //Resume ops
    public void addResume(Resume resume){
        resume_repo.save(resume);
    }
    public Resume findResumeById(Integer rid){
        try {
            Resume opres = resume_repo.findByresumeid(rid);
            if(opres != null)
                return opres;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public int addEducation(Integer rid, Education education) {
        try {
            Resume opres = resume_repo.findByresumeid(rid);
            if(opres == null)
                return 403;
            else{
            	Resume updated_resume =opres;
                updated_resume.getEducations().add(education);
                resume_repo.save(updated_resume);
                education.setResume(updated_resume);
                edu_repo.save(education) ;
            	System.out.println(updated_resume.toString());
                return 200;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 403;
        }
    }

    public int addExperience(Integer rid, Experience experience) {
        try {
            Resume opres = resume_repo.findByresumeid(rid);
            if(opres == null)
                return 403;
            else{
            	Resume updated_resume = opres;
                updated_resume.getExperiences().add(experience);
                resume_repo.save(updated_resume);
                experience.setResume(updated_resume);
                exp_repo.save(experience);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 403;
        }
        return 200;
    }

    public int addSkill(Integer rid, String skill) {
        try {
            Resume opres = resume_repo.findByresumeid(rid);
            if(opres == null)
                return 403;
            else{
                Resume updated_resume = opres;
                updated_resume.setSkills(updated_resume.getSkills()+skill+",");
                resume_repo.save(updated_resume);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 403;
        }
        return 200;
    }

    public int removeEducation(Integer eid) {
        edu_repo.deleteById(eid);
        return 200;
    }

    public int removeExperience(Integer xpid) {
        exp_repo.deleteById(xpid);
        return 200;
    }

    public int removeSkill(Integer rid, String skill) {
        try {
            Resume opres = resume_repo.findByresumeid(rid);
            if(opres == null)
                return 403;
            else{
                Resume updated_resume = opres;
                String skills = updated_resume.getSkills();
                String skillArray[] = skills.split(",");
                String updatedSkills = "";
                for(String s: skillArray){
                    if(!s.equalsIgnoreCase(skill))
                        updatedSkills+=s+",";
                }
                updated_resume.setSkills(updatedSkills);
                resume_repo.save(updated_resume);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 403;
        }
        return 200;
    }

}
