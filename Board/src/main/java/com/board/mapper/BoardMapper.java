//데이터베이스와 통신 역할을 하는 Mapper 인터페이스
package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	
	//게시글 삽입(작성)
	public int insertBoard(BoardDTO params);
	
	//게시글 조회
	public BoardDTO selectBoardDetail(Long idx);
	
	//게시글 수정
	public int updateBoard(BoardDTO params);
	
	//게시글 삭제
	public int deleteBoard(Long idx);
	
	//게시글 목록을 조회 - selectBoardDetail 호출한 결과 여러개를 저장하는 것과 유사
	public List<BoardDTO> selectBoardList();

	//삭제 여부가 'N'으로 지정된 즉, 게시물로 보여져야하는 개수를 조회
	public int selectBoardTotalCount();

}
