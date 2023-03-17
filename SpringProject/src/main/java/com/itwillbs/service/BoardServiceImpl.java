package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject 
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardServiceImpl insertBoard()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값
		
		if(boardDAO.getMaxNum() == null) {
			boardDTO.setNum(1);
		} else {
			boardDTO.setNum(boardDAO.getMaxNum()+1);
		}
		boardDTO.setReadcount(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		boardDAO.insertBoard(boardDTO);
	}
	
	@Override
	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardServiceImpl getBoardList()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값
		
		int startRow = ((pageDTO.getCurrentPage()-1)*pageDTO.getPageSize()+1);
		int endRow = startRow + pageDTO.getPageSize()-1;
		
		pageDTO.setStartRow(startRow);
		pageDTO.setEndRow(endRow);
		
		return boardDAO.getBoardList(pageDTO);
	}
	
	@Override
	public int getBoardCount() {
		System.out.println("BoardServiceImpl getBoardCount()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값
		

		return boardDAO.getBoardCount();
	}
	
	public BoardDTO getBoard(int num) {
		System.out.println("BoardServiceImpl getBoard()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값

		return boardDAO.getBoard(num);
	}
	
	@Override
	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardServiceImpl updateBoard()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값
		
		boardDAO.updateBoard(boardDTO);
	}
	
	@Override
	public void deleteBoard(int num) {
		System.out.println("BoardServiceImpl deleteBoard()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값
		
		boardDAO.deleteBoard(num);
	}
	
	@Override
	public void fupdateBoard(BoardDTO boardDTO) {
		System.out.println("BoardServiceImpl fupdateBoard()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값
		
		boardDAO.fupdateBoard(boardDTO);
	}
	
}//class
