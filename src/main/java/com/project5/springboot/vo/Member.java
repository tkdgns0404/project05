package com.project5.springboot.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@SequenceGenerator(
        name="MEMBER_SEQ_GEN",
        sequenceName="MEMBER_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="MEMBER")
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="MEMBER_SEQ_GEN"        
            )
	private int idx;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String address;
	private String address2;
	private String birth;
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();
	private String role;
	
	@Transient
    private String fileName;
	

}