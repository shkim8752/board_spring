package com.myboard.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.myboard.dao.BFileDAO;
import com.myboard.dto.BFileDTO;

@Service
public class BFileServiceImpl implements BFileService{
	//servlet-context.xml�� ���� ���� ���丮 ��
	@Resource(name="saveDir")
	String saveDir; 

	@Resource
	BFileDAO fdao;
	
	// ���� ���� ���ε��ϰ� �����̸� ����Ʈ ��ȯ
	@Override
	public List<String> fileUpload(List<MultipartFile> files) throws Exception {
		
		List<String> list = new ArrayList<>();
		for(MultipartFile mf: files) {
			//�����̸� ����
			System.out.println(mf.getOriginalFilename());
			if (mf.getOriginalFilename() != "") { //���ϸ��� �����Ҷ��� �ݺ�
				String filename = System.currentTimeMillis()+mf.getOriginalFilename();
				//������ ���ϰ�ο� �̸� ����
				File f = new File(saveDir, filename);
				try {
					mf.transferTo(f);
					list.add(filename); //�����̸� �߰�
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;

	}

	//���ϸ� ����
	@Override
	public int insert(int bnum, List<String> filenameList) throws Exception {
		for(String filename : filenameList) {
			BFileDTO fdto = new BFileDTO();
			fdto.setBnum(bnum);
			fdto.setFilename(filename);
			
			fdao.insert(fdto);
		}
		return 0;
	}

	//���� ����Ʈ
	@Override
	public List<BFileDTO> selectList(int bnum) throws Exception {
		return fdao.selectList(bnum);
	}

	@Override
	public int delete(int bnum) throws Exception {
		return fdao.delete(bnum);
	}
	
	//������ �Ϻ� ���� ����
	@Override
	public int delete_part(int bnum, List<Integer> fnumList) throws Exception{
		String fnums = "";
		//nulló��
		if (fnumList != null) {
			fnums = fnumList.toString().replace("[", "").replace("]", "");
		}
		fdao.delete_part(bnum, fnums);

		return 0;
	}

	//���� �ٿ�ε�
	@Override
	public void fileDownload(String filename, HttpServletResponse response) throws Exception{
		String fileUrl = saveDir + "/" +filename;

		try {
			//���� �б� ��Ʈ�� ����
			FileInputStream fis = new FileInputStream(fileUrl);
			
			//�ѱ������̸� ���ڵ�
			filename = URLEncoder.encode(filename, "utf-8");
			//filename = new String(filename.getBytes("utf-8"), "iso-8859-1");
			
			//���䰴ü�� ������� ����
			//÷������ ���·� ����
			//�����̸� ����
			response.setHeader("Content-Disposition", "attachment;filename="+filename);
			
			
			//���� �������� ��Ʈ��  ����
			OutputStream out = response.getOutputStream();
			//inputstream���� ����Ÿ�� �о outputstream���� ������
			FileCopyUtils.copy(fis, out);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
