package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.platform.commons.util.CollectionUtils;
import java.util.List;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class MapperTests {
	@Autowired
	private BoardMapper boardMapper;
	
	//게시글 생성을 처리하는 메서드
	/*@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");

		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 " + result + "입니다.");
	}*/
	//테스트를 위한 여러 게시글
	@Test
	public void testMultipleInsert() {
		for(int i =2;i<=50;i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i+"번 게시글 제목");
			params.setContent(i+"번 게시글 내용");
			params.setWriter(i+"번 게시글 작성자");
			boardMapper.insertBoard(params);
		}
	}
	
	
	//게시글 수정을 처리하는 메서드-> set 메서드를 이용해서 수정할 제목, 내용, 작성자, 게시글 번호를 지정
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목을 수정합니다.");
		params.setContent("1번 게시글 내용을 수정합니다.");
		params.setWriter("홍길동");
		params.setIdx((long) 1);

		int result = boardMapper.updateBoard(params);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);

				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	//게시글의 삭제를 처리하는 메소드
	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard((long)1);
		if(result==1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");
			}catch(JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	@Test
	public void testSelectList() {
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if (boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			for (BoardDTO board : boardList) {
				System.out.println("=========================");
				System.out.println(board.getTitle());
				System.out.println(board.getContent());
				System.out.println(board.getWriter());
				System.out.println("=========================");
			}
			
		}
	}
	*/
	//삭제되지 않은 게시글을 전부 조회하는 메소드
	@Test
	public void selectBoardList() {
		
	}
}

