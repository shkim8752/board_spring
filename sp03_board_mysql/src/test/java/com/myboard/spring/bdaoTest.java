package com.myboard.spring;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myboard.dao.BoardDAO;
import com.myboard.dto.BoardDTO;
import com.myboard.dto.PageDTO;

/*������ �׽�Ʈ*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class bdaoTest {

	@Resource
	private BoardDAO bdao;
	
	@Test
	public void testTotolCount() throws Exception {
		PageDTO pdto = new PageDTO();
		int totCnt = bdao.totolCount(pdto);
		System.out.println(totCnt);
		Assert.assertEquals(250,totCnt);
	}

	@Test
	public void testSelectList() throws Exception {
		PageDTO pdto = new PageDTO();
		pdto.setStartNo(11);
		pdto.setEndNo(20);
		List<BoardDTO> blist = bdao.selectList(pdto);
		System.out.println(blist);
		Assert.assertNotNull(blist);
	}

	@Test
	public void testSelectOne() throws Exception {
		Assert.assertNotNull(bdao.selectOne(3));
	}

	@Test
	public void testInsert() throws Exception {
		BoardDTO bdto = new BoardDTO();
		bdto.setWriter("ȫ�浿");
		bdto.setEmail("hong@gmail.com");
		bdto.setSubject("����");
		bdto.setContent("����");
		//bdao.insert(bdto) �� ������� 1�� ������ ����
		Assert.assertEquals(1, bdao.insert(bdto));
	}

	@Test
	public void testUpdate() throws Exception {
		BoardDTO bdto = new BoardDTO();
		bdto.setBnum(3);
		bdto.setWriter("ȫ�浿");
		bdto.setEmail("hong@gmail.com");
		bdto.setSubject("�������");
		bdto.setContent("�������");
		Assert.assertEquals(1, bdao.update(bdto));
	}

	@Test
	public void testDelete() throws Exception {
		Assert.assertEquals(1, bdao.delete(5));
	}

	@Test
	public void testReadcnt_update() throws Exception {
		Assert.assertEquals(1, bdao.readcnt_update(3));
	}

	@Test
	public void testReplycntUp_update() throws Exception {
		Assert.assertEquals(1, bdao.replycntUp_update(3));
	}

	@Test
	public void testReplycntDown_update() throws Exception {
		Assert.assertEquals(1, bdao.replycntDown_update(3));
	}

}
