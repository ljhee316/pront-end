package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.repository.Comment;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor //final필드들을 초기화하는 아규먼트를 갖는 생성자. 필수로 필요한 아규먼트를 갖는 생성자를 초기화해줌.
public class CommentService {
	
	private final CommentDao commentDao;  //생성자에 의한 의존성 주입.
	
	
	public List<CommentItemDto> readByPostId(Integer postId) {
		log.debug("readByPostId=(postId={})",postId);
		
		//리포지토리 계층의 메서드를 호출해서 comments 테이블의 데이터를 검색.
		List<Comment> list = commentDao.selectByPostId(postId);
		
		
		//List<Comment>를 List<CommentItemDto>로 변환해서 리턴. 방법1.2 
		//방법1
//		List<CommentItemDto> result = new ArrayList<>();
//		for(Comment c: list) {
//			CommentItemDto dto = CommentItemDto.fromEntity(c);
//			result.add(dto);
//		}
//		
//		return result;
		
		
		return list.stream().map(CommentItemDto::fromEntity).toList();	
	}
	
	public int create(CommentCreateDto dto) {
		log.debug("create({})", dto);
		
		//리포지토리 계층의 메서드를 호출해서 comments테이블에 insert
		int result = commentDao.insert(dto.toEntity());
		return result;
	}
	
	public int update(CommentUpdateDto dto) {
		log.debug("update({})",dto);
	
		int result = commentDao.update(dto.toEntity());
		return result;
	}
	
	public int deleteById(Integer id) {
		log.debug("deleteById(id={})", id);
		
		int result = commentDao.deleteById(id);
		return result;
	}	
	
	public CommentItemDto readById(Integer id) {
		log.debug("readById=({})", id);
		
		Comment comment = commentDao.selectById(id);
		return CommentItemDto.fromEntity(comment); //Comment타입을 CommentItemDto 타입 변환해서 리턴.
	}
	
	public int deleteByPostId(Integer id) {
		log.debug("deleteByPostId=({})", id);
		
		int result = commentDao.deleteByPostId(id);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
