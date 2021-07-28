package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.PaginationInfo;

@Service
public class BoardServicelmpl implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	//게시글 등록
	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult=0;
		
		//해당 idx가 없으면 insert(삽입), 있으면 update(수정)
		if (params.getIdx()==null) {
			queryResult=boardMapper.insertBoard(params);
		}else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		return (queryResult == 1)?true:false;
	}
	
	//게시글의 세부사항 반환
	@Override
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}
	
	//게시글 삭제(보이는 형태를 n로 바꿀뿐, 진짜 삭제하는건 아님
	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult=0;
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		
		if(board !=null && "N".equals(board.getDeleteYn())) {
			queryResult = boardMapper.deleteBoard(idx);
		}
		
		return (queryResult==1)?true:false;
	}
	
	//삭제되지 않은 전체 게시글 조회
	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {
		List<BoardDTO> boardList = Collections.emptyList();

		int boardTotalCount = boardMapper.selectBoardTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(boardTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList(params);
		}

		return boardList;
	}
}
