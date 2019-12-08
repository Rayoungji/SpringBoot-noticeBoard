package org.zerock.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.Board.CreateboardDto;
import org.zerock.dto.Board.DeleteboardDto;
import org.zerock.dto.Board.ReadboardDto;
import org.zerock.dto.Board.UpdateboardDto;
import org.zerock.entity.Board;
import org.zerock.entity.User;
import org.zerock.repository.BoardRepository;
import org.zerock.repository.UserRepository;

@Service
public class BoardService {
	
	final private BoardRepository boardRepository;
	final private UserRepository userRepository;
	
	public BoardService(BoardRepository boardRepository, UserRepository userRepository){
		this.boardRepository=boardRepository;
		this.userRepository=userRepository;
	}
	
	//게시글 작성
	public void createboard(CreateboardDto createboardDto) {
		
	
		LocalDate insertTime = LocalDate.now();
		User user=userRepository.findByIdentity(createboardDto.getIdentity());
		Board board=new Board();
		
		board.setContents(createboardDto.getContents());
		board.setTitle(createboardDto.getTitle());
		board.setInsertTime(insertTime);
		board.setUser(user);
		
		boardRepository.save(board);
	}
	
	//게시글 수정
	public void updateboard(UpdateboardDto updateboardDto) {
		
		Board board=boardRepository.findById(updateboardDto.getId()).get();
		
		if(board.getUser().getIdentity().equals(updateboardDto.getIdentity())) {
			board.setTitle(updateboardDto.getTitle());
			board.setContents(updateboardDto.getContents());
		}
		
		boardRepository.save(board);
	}
	
	//게시글 삭제
	public void deleteboard(DeleteboardDto deleteboardDto) {
	
		Board board=boardRepository.findById(deleteboardDto.getId()).get();
		
		if(board.getUser().getIdentity().equals(deleteboardDto.getIdentity())) {
			boardRepository.delete(board);
		}
	}
	
	//게시글 조회(게시글 아이디로)
	public ReadboardDto getBoardById(Long id) {
		
		Board board=boardRepository.findById(id).get();
		ReadboardDto getboard=new ReadboardDto();
		
		getboard.setIdentity(board.getUser().getIdentity());
		getboard.setTitle(board.getTitle());
		getboard.setContents(board.getContents());
		getboard.setInsertTime(board.getInsertTime());
		
		return getboard;
		
	}
	
	//게시글 전체 조회
	public List<ReadboardDto> getBoardAll(){
		
		List<Board> BoardEntityList=new ArrayList<>();
		BoardEntityList=boardRepository.findAll();
		List<ReadboardDto> BoardList=new ArrayList<>();
		
		
		for(int i=0; i<BoardEntityList.size(); i++) {
			ReadboardDto board=new ReadboardDto();
			board.setId(BoardEntityList.get(i).getId());
			board.setIdentity(BoardEntityList.get(i).getUser().getIdentity());
			board.setTitle(BoardEntityList.get(i).getTitle());
			board.setContents(BoardEntityList.get(i).getContents());
			board.setInsertTime(BoardEntityList.get(i).getInsertTime());
			
			BoardList.add(board);
		}
		
		return BoardList;
	
	}
	
	//게시글 조회 (사용자 아이디로)
	public List<ReadboardDto> getBoardByIdentity(String identity){
		List<Board>BoardEntityList=new ArrayList<>();
		BoardEntityList=boardRepository.findAllByUserIdentity(identity);
		List<ReadboardDto> BoardList=new ArrayList<>();
		
		for(int i=0; i<BoardEntityList.size(); i++) {
			ReadboardDto board=new ReadboardDto();
			board.setId(BoardEntityList.get(i).getId());
			board.setTitle(BoardEntityList.get(i).getTitle());
			board.setContents(BoardEntityList.get(i).getContents());
			
			BoardList.add(board);
		}
		
		return BoardList;
		
	}
}
