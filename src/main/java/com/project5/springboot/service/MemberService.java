package com.project5.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project5.springboot.vo.Member;
import com.project5.springboot.vo.MemberSVO;
import com.project5.springboot.vo.Paging;



@Service
public interface MemberService {
	/**
	 * 회원 목록 조회
	 * @param memberVO
	 * @param pageable
	 * @return Page<MemberVO>
	 * @throws Exception
	 */
	Page<Member> selectMemberList(Member member, Pageable pageable) throws Exception;
	
	/**
	 * 페이징 처리를 위한 메소드
	 * @param pageSize
	 * @param number
	 * @return PagingVO
	 * @throws Exception
	 */
	public Paging setPaging(int pageSize, int number) throws Exception;
	
	/**
	 * 회원 조회
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 */
	Member selectMember(Member member) throws Exception;
	
	/**
	 * 아이디, 패스워드로 회원 조회
	 * @param memberVO
	 * @throws Exception
	 */
	Member selectMemberByIdByPw(Member member) throws Exception;
	
	/**
	 * 회원 등록
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	void insertMember(MultipartFile files, Member member) throws Exception;
	
	/**
	 * 회원 수정
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	void updateMember(MultipartFile files, Member member) throws Exception;
	
	/**
	 * 회원 삭제
	 * @param memberSVO
	 * @throws Exception
	 */
	void deleteMember(Member member) throws Exception;
	void deleteMemberByAdmin(MemberSVO memberSVO) throws Exception;

	Member selectMemberByphoneNum(Member member) throws Exception;

	Member selectMemberById(Member member)throws Exception;
}

