package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.MemberDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.MemberServiceImpl;

@Controller
public class BoardController {

	//스프링의 특징 스프링 객체생성 방식 => 의존관계주입(Dependency Injection)
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		System.out.println("BoardController write()");
		// 처리작업
		
		// 가상주소에서 주소변경 없이 이동
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		System.out.println("BoardController writePro()");
		// 처리작업
		boardService.insertBoard(boardDTO);
		
		// 가상주소에서 주소변경하며 이동
		return "redirect:/board/list";
	}
	
	// http://localhost:8080/myweb/board/list
	// http://localhost:8080/myweb/board/list?pageNum=1 => HttpServletRequest request 객체생성
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, PageDTO pageDTO, Model model) {
		System.out.println("BoardController list()");
		// 처리작업
		
		//한 화면에 보여줄 글의 개수
		int pageSize = 10;
		
		//현재페이지 번호 설정
		String pageNum= request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		} 
		int CurrentPage = Integer.parseInt(pageNum);
		
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(CurrentPage);
		
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
		
		//페이징 처리
		int count = boardService.getBoardCount();
		int pageBlock = 10;
		int startPage = (CurrentPage-1)/pageBlock*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		if(endPage > pageCount){
		 	endPage = pageCount;
		}
		
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageDTO", pageDTO);
		
		// 가상주소에서 주소변경 없이 이동
		return "board/list";
	}
	
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model) {
		System.out.println("BoardController content()");
		// 처리작업
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDTO boardDTO = boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// 가상주소에서 주소변경 없이 이동
		return "board/content";
	}

}//class
