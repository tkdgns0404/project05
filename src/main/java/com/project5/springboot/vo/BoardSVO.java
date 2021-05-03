package com.project5.springboot.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSVO extends Paging{
	
	private int idx;
	private String title;
	private String writer;
	private String content;
	private int cnt;
	private int likes;
	private int hates;
    
	private String[] idxs;

	
}