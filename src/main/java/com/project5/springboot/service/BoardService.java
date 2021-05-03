package com.project5.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project5.springboot.vo.Board;
import com.project5.springboot.vo.BoardSVO;
import com.project5.springboot.vo.Paging;



@Service
public interface BoardService {
	/**
	 * 게시글 목록 조회
	 * @param board
	 * @param pageable
	 * @return Page<Board>
	 * @throws Exception
	 */
	Page<Board> selectBoardList(Board board, Pageable pageable) throws Exception;
	
	/**
	 * 페이징 처리를 위한 메소드
	 * @param pageSize
	 * @param number
	 * @return PagingVO
	 * @throws Exception
	 */
	public Paging setPaging(int pageSize, int number) throws Exception;
	
	/**
	 * 게시글 조회
	 * @param board
	 * @return Board
	 * @throws Exception
	 */
	Board selectBoard(Board board) throws Exception;
	
	
	/**
	 * 게시글 등록
	 * @param files
	 * @param board
	 * @throws Exception
	 */
	void insertBoard(Board board) throws Exception;
	
	/**
	 * 게시글 수정
	 * @param files
	 * @param board
	 * @throws Exception
	 */
	void updateBoard(Board board) throws Exception;
	
	/**
	 * 게시글 삭제(관리자)
	 * @param boardSVO
	 * @throws Exception
	 */
	void deleteBoardByAdmin(BoardSVO boardSVO) throws Exception;
	
	
	/**
	 * 게시글 삭제
	 * @param board
	 * @throws Exception
	 */
	void deleteBoard(Board board) throws Exception;
}

