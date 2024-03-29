package com.iu.home.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.home.util.FileManager;
import com.iu.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service //객체 생성 안하고 로그 찍을 수 있음
@Slf4j
	//Exception 발생 시 rollback
@Transactional(rollbackFor = Exception.class)
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;
	
	//application.properties 에서 지정한 파일 경로
	@Value("${app.upload.qna}")
	private String path;
	
// 목록 -------------------------------	
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		return qnaMapper.getList(pager);
	}

// 글쓰기 -------------------------------	
	public int setAdd(QnaVO qnaVO)throws Exception{
		int result = qnaMapper.setAdd(qnaVO);		
		
							//파일 경로
		File file = new File(path);
		
		if(!file.exists()) {
			boolean check=file.mkdirs();
		}
		
		for(MultipartFile f : qnaVO.getFiles()) {
			if(!f.isEmpty()) {
				log.info("FileName : {}", f.getOriginalFilename());
				String fileName = fileManager.saveFile(f, path);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(f.getOriginalFilename());
				qnaFileVO.setNum(qnaVO.getNum());
				qnaMapper.setFileAdd(qnaFileVO);			
			}
		}	
		return result;
	}
	
	//파일 삭제
	public void setfileDelete(QnaFileVO qnaFileVO) throws Exception{
		
	}
	
// 디테일 -------------------------------		
	public QnaVO getDetail(QnaVO qnaVO) throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception{
		   return qnaMapper.getFileDetail(qnaFileVO);
	}
	
}
