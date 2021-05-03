package com.project5.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project5.springboot.vo.FileVO;



/**
 * File 테이블 Repository
 *
 */
public interface FileRepository extends JpaRepository<FileVO, Integer> {
	
	// FileIdx 값으로 조회
	FileVO findByFileIdx(int fileIdx);
	
	// Member Idx 값으로 조회
	FileVO findByMemberIdx(int memberIdx);
	
	
	// insert
	FileVO save(FileVO file);
	
	// FileIdx 값으로 삭제
	void deleteByFileIdx(int fileIdx);
	
	// Member Idx 값으로 삭제
	void deleteByMemberIdx(int memberIdx);
	
}