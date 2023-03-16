package com.itwillbs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {

	//스프링의 특징 스프링 객체생성 방식 => 의존관계주입(Dependency Injection)
	@Inject
	private BoardService boardService;
	
	// 업로드 경로 변수
	@Resource(name = "uploadPath") // servelt-xml에서 설정된 경로개체 bean의 id
	private String uploadPath;
	
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		System.out.println("BoardController write()");
		// 처리작업
		
		// 가상주소변경 없이 이동
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardDTO boardDTO) {
		System.out.println("BoardController writePro()");
		// 처리작업
		boardService.insertBoard(boardDTO);
		
		// 가상주소변경하며 이동
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
		
		// 가상주소변경 없이 이동
		return "board/list";
	}
	
	@RequestMapping(value = "/board/content", method = RequestMethod.GET)
	public String content(HttpServletRequest request, Model model) {
		System.out.println("BoardController content()");
		// 처리작업
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDTO boardDTO = boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// 가상주소변경 없이 이동
		return "board/content";
	}
	
	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	public String update(HttpServletRequest request, Model model) {
		System.out.println("BoardController update()");
		// 처리작업
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDTO boardDTO = boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// 가상주소변경 없이 이동
		return "board/updateForm";
	}
	
	@RequestMapping(value = "/board/updatePro", method = RequestMethod.POST)
	public String updatePro(BoardDTO boardDTO) {
		System.out.println("BoardController updatePro()");
		// 처리작업
		boardService.updateBoard(boardDTO);
		
		
		// 가상주소변경하며 이동
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		System.out.println("BoardController delete()");
		// 처리작업
		int num = Integer.parseInt(request.getParameter("num"));
		
		boardService.deleteBoard(num);
		
		// 가상주소변경하며 이동
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/board/fwrite", method = RequestMethod.GET)
	public String fwrite() {
		System.out.println("BoardController fwrite()");
		// 처리작업
		
		// 가상주소변경 없이 이동
		return "board/fwriteForm";
	}
	
	@RequestMapping(value = "/board/fwritePro", method = RequestMethod.POST)
	public String fwritePro(HttpServletRequest request, MultipartFile file) throws Exception{
		//  throws Exception => 예외처리를 메서드 호출한 곳에서 함
		// <input type="file" name="file" > 
		//  => 이름일치 MultipartFile file
		System.out.println("BoardController fwritePro()");
		
		// 첨부파일 있으면 (enctype="multipart/form-data")
		// BoardDTO 자동으로 저장 안됨
		BoardDTO boardDTO=new BoardDTO();
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		// 파일업로드 프로그램설치 =>pom.xml
		//첨부파일 
		// 파일이름 => 랜덤파일이름 => 랜덤문자_파일이름 => 디비저장
		UUID uuid=UUID.randomUUID();
		String filename=uuid.toString()+"_"+file.getOriginalFilename();
		// 원본파일 복사 => 파일위치 / 랜덤문자_파일이름 붙여넣기
		// FileCopyUtils.copy(원본파일, 복사위치/파일이름);
		// 외부에 있는 파일 처리 => 예외처리
		
		FileCopyUtils.copy(file.getBytes(), new File(uploadPath,filename));
		
		// boardDTO file이름 저장
		boardDTO.setFile(filename);
		
		//글쓰기 작업 메서드 호출
		boardService.insertBoard(boardDTO);
		// 주소변경 하면서 이동
		return "redirect:/board/list";
	}
	

}//class
