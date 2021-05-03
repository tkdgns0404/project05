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

import lombok.Getter;
import lombok.Setter;

@SequenceGenerator(
        name="BOARD_SEQ_GEN",
        sequenceName="BOARD_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="BOARD")
@Getter @Setter
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="BOARD_SEQ_GEN"        
            )
	private int idx;
	private String title;
	private String writer;
	private String content;
	private int cnt;
	private int likes;
	private int hates;
	
	
	@Temporal(TemporalType.DATE)
	private Date regDate = new Date();

	
}

