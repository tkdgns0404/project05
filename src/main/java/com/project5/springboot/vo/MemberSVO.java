package com.project5.springboot.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSVO extends Paging{
	
	private int idx;
    private String id;
    private String password;
    private String name;
    private String email;
    private String phoneNum;
    private String address;
    private String address2;
    private String birth;
    private String role;
    
	private String[] idxs;

	
}