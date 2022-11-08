package com.apurba.relevel.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="achivment")
public class Achivment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer achid;
	
    private String achivmentname;
    private String certificatelink;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Resume resume;
    
    
    

	public Integer getAchid() {
		return achid;
	}

	public String getAchivmentname() {
		return achivmentname;
	}

	public String getCertificatelink() {
		return certificatelink;
	}

	public Resume getResume() {
		return resume;
	}

	public void setAchid(Integer achid) {
		this.achid = achid;
	}

	public void setAchivmentname(String achivmentname) {
		this.achivmentname = achivmentname;
	}

	public void setCertificatelink(String certificatelink) {
		this.certificatelink = certificatelink;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

    

}
