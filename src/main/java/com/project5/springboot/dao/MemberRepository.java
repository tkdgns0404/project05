package com.project5.springboot.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project5.springboot.vo.Member;


public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	// 모든 데이터 조회
	Page<Member> findAll(Pageable pageable);
	// 모든 데이터 조회 Idx로 내림차순 조회
	Page<Member> findAllByOrderByIdxDesc(Pageable pageable);
	
	// Name으로 like 조회 
	Page<Member> findByNameLike(Pageable pageable, String name);
	// Name 조회 Idx로 내림차순 조회
	Page<Member> findByNameLikeOrderByIdxDesc(Pageable pageable, String name);
	
	// MemberIdx로  조회
	Member findByIdx(int idx);
	
	// 회원가입여부를 확인하기 위한(로그인하기 위한) Id, Pw로  조회
	Member findByIdAndPassword(String id, String password);
	
	// insert
	Member save(Member member);
		
	// MemberIdx로 삭제
	void deleteByIdx(int idx);
	
	Member findByPhoneNum(String phoneNum);
	
	Member findById(String id);


}
