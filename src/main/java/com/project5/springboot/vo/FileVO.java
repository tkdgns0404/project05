package com.project5.springboot.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SequenceGenerator(
        name="FILE_SEQ_GEN",
        sequenceName="FILE_SEQ",
        initialValue=1,
        allocationSize=1
        )
@Entity
@Table(name="memberFile")
@Getter @Setter
public class FileVO {
	
	@Id
	@GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="FILE_SEQ_GEN"        
            )
	private int fileIdx;
	private int memberIdx;
    private String origFileName;
    private String fileName;
    private String filePath;
    
    
	@Override
	public String toString() {
		return "FileVO [fileIdx=" + fileIdx + ", memberIdx=" + memberIdx + ", origFileName=" + origFileName
				+ ", fileName=" + fileName + ", filePath=" + filePath + "]";
	}

    
}