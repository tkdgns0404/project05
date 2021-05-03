package com.project5.springboot.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project5.springboot.service.BoardService;
import com.project5.springboot.vo.Board;
import com.project5.springboot.vo.BoardSVO;
import com.project5.springboot.vo.Paging;


@Controller
public class BoardController {
	@Resource(name="boardService")
	private BoardService boardService;
	
	/**
	 * 게시글 목록 조회
	 * @param pageable
	 * @param BoardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/selectBoardList.do")
	public String selectBoardList(Pageable pageable, @ModelAttribute("Board")Board BoardSVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			// 목록 조회
			Page<Board> resultList = boardService.selectBoardList(BoardSVO, pageable);
			// 해당 목록의 페이징
			Paging pagingVO = boardService.setPaging(pageable.getPageSize(), pageable.getPageNumber());
			
			model.addAttribute("resultBoard", resultList);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("resultBoardList", resultList.getContent());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "boardList";
	}
	
	/**
	 * 게시글 작성 화면 이동
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdBoardReg.do")
	public String fwdMemberReg(HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			
		}catch(Exception e) {
			
		}
		
		return "boardReg";
	}
	/**
	 * 게시글 등록
	 * @param files
	 * @param board
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insertBoard.do")
	public String insertMember(@ModelAttribute("board")Board board, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
			board.setWriter((String)session.getAttribute("userName"));
		try {
			boardService.insertBoard(board);
			
			model.addAttribute("message", "게시글이 등록 되었습니다");
			model.addAttribute("board", board);
			model.addAttribute("returnURL", "/selectBoardList.do");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 수정 화면 이동
	 * @param board
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fwdBoardUpt.do")
	public String fwdMemberUpt(@ModelAttribute("board")Board board, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
			board.setWriter((String)session.getAttribute("userName"));
		try {
			Board resultVO = boardService.selectBoard(board);
			
			model.addAttribute("resultVO", resultVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "boardUpt";
	}
	/**
	 * 게시글 수정
	 * @param files
	 * @param board
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateBoard.do")
	public String updateMember(@ModelAttribute("board")Board board, HttpServletRequest request, 
			HttpSession session, Model model) throws Exception{
		try {
			boardService.updateBoard(board);
			
			model.addAttribute("message", "게시글이 수정되었습니다.");
			model.addAttribute("board", board);
			model.addAttribute("returnURL", "/selectBoardList.do");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 삭제(관리자)
	 * @param boardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteBoardByAdmin.do")
	public String deleteBoardByAdmin(@ModelAttribute("boardSVO")BoardSVO boardSVO, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			boardService.deleteBoardByAdmin(boardSVO);
			
			model.addAttribute("message", "게시글이 삭제되었습니다.");
			model.addAttribute("returnURL", "/selectBoardList.do");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
	
	/**
	 * 게시글 삭제(사용자 / 자신의 글에만 권한)
	 * @param boardSVO
	 * @param request
	 * @param session
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(@ModelAttribute("board")Board board, 
			HttpServletRequest request, HttpSession session, Model model) throws Exception{
		try {
			boardService.deleteBoard(board);
			
			model.addAttribute("message", "게시글이 삭제되었습니다.");
			model.addAttribute("returnURL", "/selectBoardList.do");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "resultBody";
	}
}
