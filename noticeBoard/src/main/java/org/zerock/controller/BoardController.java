package org.zerock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.Board.CreateboardDto;
import org.zerock.dto.Board.DeleteboardDto;
import org.zerock.dto.Board.ReadboardDto;
import org.zerock.dto.Board.UpdateboardDto;
import org.zerock.service.BoardService;

@RestController
public class BoardController {

	final private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService=boardService;
	}
	
	//게시글 작성
	@RequestMapping(value="/Board/write", method=RequestMethod.POST)
	public void write(@RequestBody CreateboardDto createboardDto) {
		boardService.createboard(createboardDto);
	}
	
	//게시글 수정
	@RequestMapping(value="/Board/update", method=RequestMethod.PUT)
	public void update(@RequestBody UpdateboardDto updateboardDto) {
		boardService.updateboard(updateboardDto);
	}

	//게시글 삭제
	@RequestMapping(value="/Board/delete",method=RequestMethod.DELETE)
	public void delete(@RequestBody DeleteboardDto deleteboardDto) {
		boardService.deleteboard(deleteboardDto);
	}
	
	//게시글 조회 (게시글 아이디로)
	@RequestMapping(value="/Board/read/{id}", method=RequestMethod.GET)
	public ReadboardDto getBoardById(@PathVariable Long id) {
		
		return boardService.getBoardById(id);
	}
	
	//게시글 전체 조회
	@RequestMapping(value="/Board/read",method=RequestMethod.GET)
	public List<ReadboardDto> getBoardAll(){
		
		return boardService.getBoardAll();
	}
	
	//게시글 조회(사용자 아이디로)
	@RequestMapping(value="Board/userread/{identity}",method=RequestMethod.GET)
	public List<ReadboardDto> getBoardByIdentity(@PathVariable String identity){
		return boardService.getBoardByIdentity(identity);
	}

	
}
