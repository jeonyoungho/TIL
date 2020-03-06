package org.zerock.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	private int[] bnoArr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}

//	@Test
//	public void testCreate() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[i]);
//			vo.setReply("댓글 테스트 " +i);
//			vo.setReplyer("replyer" + i);
//			
//			mapper.insert(vo);			
//		});
//	}

//	@Test
//	public void testRead() {
//		int targetRno = 2;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		log.info(vo);
//	}

//	@Test
//	public void testDelete() {
//		int targetRno = 1;
//		mapper.delete(targetRno);
//	}

//	@Test
//	public void testUpdate() {
//		int targetRno = 10;
//		ReplyVO vo = mapper.read(targetRno);
//		vo.setReply("Update Reply");
//		int count = mapper.update(vo);
//		
//		log.info("UPDATE COUNT: " + count); 
//	}

//	@Test
//	public void testList() {
//		Criteria cri = new Criteria();
//		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[5]);
//
//		replies.forEach(reply -> log.info(reply));
//	}
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria(1,10);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 10);
		
		replies.forEach(reply -> log.info(reply));
				
	}
}
