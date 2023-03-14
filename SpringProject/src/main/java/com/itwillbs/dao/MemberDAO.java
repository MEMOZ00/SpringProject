package com.itwillbs.dao;

import java.util.List;

import org.springframework.ui.Model;

import com.itwillbs.domain.MemberDTO;

public interface MemberDAO {
	// 추상메서드 틀
	
	public void insertMember(MemberDTO memberDTO);
	
	public MemberDTO userCheck(MemberDTO memberDTO);
	
	public MemberDTO getMember(String id);
	
	public void updateMember(MemberDTO memberDTO);
	
	public void deleteMember(MemberDTO memberDTO);
	
	public List<MemberDTO> getMemberList();
	
}// class
