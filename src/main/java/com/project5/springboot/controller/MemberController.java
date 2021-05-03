package com.project5.springboot.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project5.springboot.service.MemberService;
import com.project5.springboot.vo.Member;
import com.project5.springboot.vo.MemberSVO;
import com.project5.springboot.vo.Paging;


@Controller
public class MemberController {
	@Resource(name="memberService")
	private MemberService memberService;
	
	/**
	 * 로그인 페이지
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/changPw.do"})
	public String changePw(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "changePassword";
	}
	
	@RequestMapping(value={"/logout.do"})
	public String logout(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			session.invalidate();
		}catch(Exception e) {
			
		}
		return "index";
	}
	@RequestMapping(value={"/login.do"})
	public String login(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "login";
	}
	@RequestMapping(value={"","/index.do"})
	public String index(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "index";
	}
	
	/**
	 * 로그인 
	 * @param memberVO
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/loginAction.do")
	public @ResponseBody String loginAction(@ModelAttribute("memberVO")Member member, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{
		String result = "N";
		
		try {
			Member resultVO = memberService.selectMemberByIdByPw(member);
			
			// 멤버의 정보가 있다면, 멤버의 이름을 리턴
			if(resultVO != null) {
				result = resultVO.getName();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("userName", result);
		return result;
	}
	@RequestMapping(value={"/findMember.do"})
	public String findMember(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "findId";
	}
	
	@RequestMapping(value="/findId.do")
	public @ResponseBody String findMember(@ModelAttribute("memberVO")Member member, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{
		String result = "N";
		
		try {
			Member resultVO = memberService.selectMemberByphoneNum(member);
			
			// 멤버의 정보가 있다면, 멤버의 이름을 리턴
			if(resultVO != null) {
				result = resultVO.getId();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value={"/findMemberPw.do"})
	public String findMemberPw(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		return "findPw";
	}
	
	@RequestMapping(value="/findPw.do")
	public @ResponseBody String findPw(@ModelAttribute("memberVO")Member member, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{
		String result = "N";
		
		try {
			Member resultVO = memberService.selectMemberById(member);
			
			// 멤버의 정보가 있다면, 멤버의 이름을 리턴
			if(resultVO != null) {
				result = resultVO.getPassword();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 멤버 목록 조회
	 * @param pageable
	 * @param memberSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectMemberList.do")
	public String selectMemberList(Pageable pageable, @ModelAttribute("memberSVO")Member memberSVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			// 목록 조회
			Page<Member> resultList = memberService.selectMemberList(memberSVO, pageable);
			// 해당 목록의 페이징
			Paging pagingVO = memberService.setPaging(pageable.getPageSize(), pageable.getPageNumber());
			
			model.addAttribute("result", resultList);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("resultList", resultList.getContent());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "memberList";
	}
	
	/**
	 * 멤버 등록화면 이동
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdMemberReg.do")
	public String fwdMemberReg(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		
		return "memberReg";
	}
	/**
	 * 멤버 등록
	 * @param files
	 * @param memberVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insertMember.do")
	public String insertMember(@RequestParam("userImageAttachFile") MultipartFile files, 
			@ModelAttribute("memberVO")Member memberVO, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			memberService.insertMember(files, memberVO);
			
			model.addAttribute("message", "회원가입 되셨습니다");
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("returnURL", "/login.do");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 멤버 수정화면 이동
	 * @param memberVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdMemberUpt.do")
	public String fwdMemberUpt(@ModelAttribute("memberVO")Member memberVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			Member resultVO = memberService.selectMember(memberVO);
			
			model.addAttribute("resultVO", resultVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "memberUpt";
	}
	/**
	 * 멤버 수정
	 * @param files
	 * @param memberVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateMember.do")
	public String updateMember(@RequestParam("userImageAttachFile") MultipartFile files,
			@ModelAttribute("memberVO")Member memberVO, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			memberService.updateMember(files, memberVO);
			
			model.addAttribute("message", "수정되었습니다.");
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("returnURL", "/selectMemberList.do");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	/**
	 * 멤버 삭제(관리자)
	 * @param boardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteMemberByAdmin.do")
	public String deleteMemberByAdmin(@ModelAttribute("memberSVO")MemberSVO memberSVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			memberService.deleteMemberByAdmin(memberSVO);
			
			model.addAttribute("message", "회원 삭제되었습니다.");
			model.addAttribute("returnURL", "/selectMemberList.do");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 멤버 삭제
	 * @param memberSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteMember.do")
	public String deleteMember(@ModelAttribute("member")Member member, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			memberService.deleteMember(member);
			
			model.addAttribute("message", "회원 탈퇴 하셨습니다.");
			model.addAttribute("returnURL", "/selectMemberList.do");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	
	/**
	 * 주소 검색 팝업페이지 이동
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdSearchAddressPopup.do")
	public String fwdSearchAddressPopup(HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		
		return "addressAPIPopup";
	}
	

	
}
