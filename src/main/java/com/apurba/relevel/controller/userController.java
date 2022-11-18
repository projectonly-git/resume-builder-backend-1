package com.apurba.relevel.controller;

import com.apurba.relevel.model.*;
import com.apurba.relevel.service.*;
import com.apurba.relevel.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

import java.util.Date;
import java.util.List;

@RestController
public class userController {
    @Autowired
    userService user_service;
    
    @Autowired
    ResumeRepo res_repo;

    @Autowired
    UserRepo user_repo;
    /* user start */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public int register(@RequestBody User user) {
        if(user_service.getUserById(user.getEmailid()) != null)
            return 400;
        if (user_service.addUser(user)) {
            return 200;
        }
        return 401;
    }
    
    
    @RequestMapping(value = "/user/resume/all", method = RequestMethod.GET)
    public List<Resume> getUserResumeList(@RequestParam String email) {
        User user = user_service.getUserById(email);
        if(user == null) {
            return null;
        }else {
            return user.getResumelist();
        }
    }
    
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
        User registeredUser = user_service.getUserById(user.getEmailid());
        if(registeredUser.getPassword().equalsIgnoreCase(user.getPassword())) {
            return registeredUser;
        }
        return null;
    }
    
    @RequestMapping(value = "/createresume", method = RequestMethod.GET)
    public int createResume(@RequestParam String emailid) {
        int rid = (int)Math.round(Math.random()*10000);
        User user = user_service.getUserById(emailid);
        Resume resume = new Resume();
        resume.setResumeid(rid); resume.setMedoreng("engineer"); 
        
        resume.setTemplateid("1"); resume.setDate( String.valueOf(  new Date() ) );
        resume.setFirstname(""); resume.setSecondname(""); resume.setDesignation("");
        resume.setAddress(""); resume.setCity(""); resume.setState("AK-Alaska"); resume.setPincode("");
        resume.setEmailId(""); resume.setPhonenumber("");
        resume.setLinkedin(""); resume.setGithub("");
        
        resume.setUser(user);
        
        resume.setSkills(""); resume.setEducations(null); resume.setExperiences(null); resume.setAchivments(null);

        user_service.addResume(resume);
        //Resume newResume = new Resume(rid, "","","","","","",0, emailid, "", "","",user, "",null,null);
        return rid;
    }
    
    @RequestMapping(value = "/updateresumemedoreng", method = RequestMethod.GET)
    public int updateresumemedoreng(@RequestParam String emailid, @RequestParam Integer resumeid, @RequestParam String medoreng) {
        return user_service.updateresumemedoreng(emailid, resumeid, medoreng);
    }
    
    @RequestMapping(value = "/setresumetemplate", method = RequestMethod.GET)
    public int setresumetemplate(@RequestParam String emailid, @RequestParam Integer resumeid, @RequestParam String templateid) {
        return user_service.setresumetemplate(emailid, resumeid, templateid);
    }
    
    
    
    
    
    /* save part */
    @RequestMapping(value = "/savepersonaldetails/{emailid}", method = RequestMethod.POST)
    public int savePersonalDetails(@PathVariable String emailid, @RequestBody Resume resume) {
    	User user = user_service.getUserById(emailid);
    	resume.setUser(user);
        user_service.addResume(resume);
        return 200;
    }

    @RequestMapping(value = "/saveeducationdetails/{resumeid}", method = RequestMethod.POST)
    public int savePersonalDetails(@PathVariable Integer resumeid, @RequestBody Education education) {
        user_service.addEducation(resumeid, education);
        return 200;
    }

    @RequestMapping(value = "/saveexperiencedetails/{resumeid}", method = RequestMethod.POST)
    public int saveEperienceDetails(@PathVariable Integer resumeid, @RequestBody Experience experience) {
        user_service.addExperience(resumeid, experience);
        return 200;
    }
    
    @RequestMapping(value = "/saveskill/{resumeid}", method = RequestMethod.GET)
    public int saveSkillDetails(@PathVariable Integer resumeid, @RequestParam String skill) {
        return user_service.addSkill(resumeid, skill);
    }
    
    @RequestMapping(value = "/saveachivement/{resumeid}", method = RequestMethod.POST)
    public int saveachivement(@PathVariable Integer resumeid, @RequestBody Achivment achivment) {
        return user_service.addAchivment(resumeid, achivment);
    }

   
    
    
    
    
    
    /* get part */
    @RequestMapping(value = "/getalleducation/{resumeid}", method = RequestMethod.GET)
    public List<Education> getalleducation(@PathVariable Integer resumeid) {
    	Resume resume = res_repo.findByresumeid(resumeid);
        return resume.getEducations();
    }

    @RequestMapping(value = "/getallexp/{resumeid}", method = RequestMethod.GET)
    public List<Experience> getallexp(@PathVariable Integer resumeid) {
    	Resume resume = res_repo.findByresumeid(resumeid);
        return resume.getExperiences();
    }

    @RequestMapping(value = "/getresumedetails/{resumeid}", method = RequestMethod.GET)
    public Resume saveResumeDetails(@PathVariable Integer resumeid) {
        return user_service.findResumeById(resumeid);
    }

    
    
    
    
    
    /* delete part */
    @RequestMapping(value = "/deleteeducationdetails/{eid}", method = RequestMethod.GET)
    public int deleteEducationDetails(@PathVariable Integer eid) {
        return user_service.removeEducation(eid);
    }

    
    @RequestMapping(value = "/deleteskill/{resumeid}", method = RequestMethod.GET)
    public int deleteSkillDetails(@PathVariable Integer resumeid, @RequestParam String skill) {
        return user_service.removeSkill(resumeid, skill);
    }
    
    @RequestMapping(value = "/deleteexperiencedetails/{xpid}", method = RequestMethod.GET)
    public int deleteExperienceDetails(@PathVariable Integer xpid) {
        return user_service.removeExperience(xpid);
    }
    @RequestMapping(value = "/deleteresume/{resumeid}", method = RequestMethod.GET)
    public int deleteresume(@PathVariable Integer resumeid) {
    	System.out.println(resumeid);
        return user_service.removeResume(resumeid);
    }
    @RequestMapping(value = "/deletethisachivment/{achivmentid}", method = RequestMethod.GET)
    public int deletethisachivment(@PathVariable Integer achivmentid) {
        return user_service.removeAchivment(achivmentid);
    }

    


}
