package com.project5.springboot.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project5.springboot.vo.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	// 모든 게시글 조회
	Page<Board> findAll(Pageable pageable);
	// 모든 게시글 조회 Idx로 내림차순 조회
	Page<Board> findAllByOrderByIdxDesc(Pageable pageable);
	
	// Title으로 like 검색 Idx 내림차순 조회
	Page<Board> findByTitleLikeOrderByIdxDesc(Pageable pageable, String title);
	// Content으로 like 검색 Idx 내림차순 조회
	Page<Board> findByContentLikeOrderByIdxDesc(Pageable pageable, String content);
	
	// Board Idx로  조회
	Board findByIdx(int idx);
	
	// 본인이 쓴 글인지 확인 하려는 쿼리
	Board findByWriter(String writer);
	
	// insert
	Board save(Board board);
		
	// MemberIdx로 삭제
	void deleteByIdx(int idx);


}
