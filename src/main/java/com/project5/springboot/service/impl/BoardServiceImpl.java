package com.project5.springboot.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project5.springboot.dao.BoardRepository;
import com.project5.springboot.service.BoardService;
import com.project5.springboot.vo.Board;
import com.project5.springboot.vo.BoardSVO;
import com.project5.springboot.vo.Paging;


@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardRepository boardDAO;
	
	
	/**
	 * 게시글 목록 조회
	 * @param memberVO
	 * @param pageable
	 * @return Page<Member>
	 * @throws Exception
	 */
	@Transactional
	@Override
	public Page<Board> selectBoardList(Board board, Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		Page <Board> resultList = null;
		
		// 게시글 제목으로 검색 키워드가 없다면 (모든 조회일 경우)
		if("".equals(retNull(board.getTitle()))) {
			// 게시글 내용으로 검색 키워드가 없다면 (모든 조회일 경우)
			if("".equals(retNull(board.getContent()))) {
				// 둘다 없으면 전체 조회
				resultList = boardDAO.findAllByOrderByIdxDesc(pageable);
			} else {
				// 제목은 검색 키워드는 없지만 내용을 조회하면  내용 조회
				resultList = boardDAO.findByContentLikeOrderByIdxDesc(pageable, "%"+board.getContent()+"%");
			}
				// 게시글 제목으로 검색 키워드가 있다면 BoardTitle으로 like 검색
		} else {
			resultList = boardDAO.findByTitleLikeOrderByIdxDesc(pageable, "%"+board.getTitle()+"%");
		}
		        
		return resultList;
	}
	
	/**
	 * 게시글 조회 (선택 했을 시)
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public Board selectBoard(Board board) throws Exception {
		Board resultVO = boardDAO.findByIdx(board.getIdx());
		
		return resultVO;
	}

	
	/**
	 * 게시글 등록
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void insertBoard(Board board) throws Exception {
		boardDAO.save(board);
	}
		

	/**
	 * 게시글 수정
	 * @param files
	 * @param memberVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void updateBoard(Board board) throws Exception {
		Optional<Board> resultVO = boardDAO.findById(board.getIdx());
		 
		if (resultVO.isPresent()) { 
			resultVO.get().setTitle(board.getTitle());
			resultVO.get().setWriter(board.getWriter());
			resultVO.get().setContent(board.getContent());
			resultVO.get().setRegDate(board.getRegDate());
			
			boardDAO.save(board); 
		}
		
	}
	
	/**
	 * 회원 삭제(관리자)
	 * @param memberSVO
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void deleteBoardByAdmin(BoardSVO boardSVO) throws Exception {
		//선택한 건 만큼 반복을 돌려 삭제한다
		for(int i=0; i<boardSVO.getIdxs().length; i++) {
			int boardIdx = Integer.parseInt(boardSVO.getIdxs()[i]);
			// 게시글 삭제
			boardDAO.deleteByIdx(boardIdx);		
			
		}
	}
	
	// 게시글 삭제(자신이 쓴 게시글만 권한)
	@Transactional
	@Override
	public void deleteBoard(Board board) throws Exception {
		boardDAO.deleteByIdx(board.getIdx());
	}
	/**
	 * null값 처리를 위한 메소드
	 * @param str
	 * @return str
	 */
	private String retNull(String str) {
		
		if(str == null || "".equals(str) || "null".equals(str.toLowerCase())) {
			return "";
		}
		
		str = str.trim();
		
		return str;
	}
	
	// 첫 페이지
	int startPage = 0;
	// 총 페이지
	int totalPage = 0;
	// 마지막 페이지
	int endPage = 0;
	// 한 페이지의 멤버 수
	static int maxPageCnt = 10;
	
	/**
	 * 페이징 처리를 위한 메소드
	 */
	public Paging setPaging(int pageSize, int number) {
		
		Paging pagingVO = new Paging();
		int totalCount = (int)boardDAO.count();
		
		startPage = ( ( ( (number % maxPageCnt) == 0) ? number-1 : number) / maxPageCnt) * maxPageCnt + 1;
		totalPage = (totalCount / pageSize) + ( ((totalCount % pageSize) == 0) ? 0 : 1);
		endPage = ( (totalPage - startPage) >= maxPageCnt ) ? (startPage + (maxPageCnt-1)) : totalPage;
		
		pagingVO.setStartPage(startPage);
		pagingVO.setTotalPage(totalPage);
		pagingVO.setEndPage(endPage);
		
		return pagingVO;
	}
	
}
