package com.itwillbs.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	static String namespace="com.itwillbs.mappers.boardMapper";
	
	@Override
	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAOImpl insertBoard()");
		
		sqlSession.insert(namespace+".insertBoard", boardDTO);
	}
	
	@Override
	public Integer getMaxNum() {
		System.out.println("BoardDAOImpl getMaxNum()");
		
		return sqlSession.selectOne(namespace+".getMaxNum");
	} 
	
	@Override
	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardDAOImpl getBoardList()");
		
		// mySQL limit 함수 계산용
		pageDTO.setStartRow(pageDTO.getStartRow()-1);
		
		return sqlSession.selectList(namespace+".getBoardList", pageDTO);
	} 
	
	@Override
	public int getBoardCount() {
		System.out.println("BoardDAOImpl getBoardCount()");
		
		return sqlSession.selectOne(namespace+".getBoardCount");
	} 
	
	@Override
	public BoardDTO getBoard(int num) {
		System.out.println("BoardDAOImpl getBoard()");
		
		return sqlSession.selectOne(namespace+".getBoard", num);
	} 
	
	@Override
	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAOImpl updateBoard()");
		
		sqlSession.update(namespace+".updateBoard", boardDTO);
	}
	
	@Override
	public void deleteBoard(int num) {
		System.out.println("BoardServiceImpl deleteBoard()");
		// form input에서 자동으로 set함수로 할당되는 값 = name, subject, content
		// DAO단에서 할당할 값 = num, readcount, date, file
		// num => DB단의 결과를 가져와서 계산해야하는 값
		
		sqlSession.delete(namespace+".deleteBoard", num);
	}
	
	@Override
	public void fupdateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAOImpl fupdateBoard()");
		
		sqlSession.update(namespace+".fupdateBoard", boardDTO);
	}
	
}//class
