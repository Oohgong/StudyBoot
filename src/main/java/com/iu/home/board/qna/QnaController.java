package com.iu.home.board.qna;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.home.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaService qnaService;

	
// 글 쓰기 --------------------------------	
	@PostMapping("add")
	public String setAdd(QnaVO qnaVO, RedirectAttributes redirectAttributes)throws Exception{
		
		int result = qnaService.setAdd(qnaVO);
		redirectAttributes.addAttribute("result", result);
		
		return "redirect:./list";
	}
	
	@GetMapping("add")
	public String setAdd()throws Exception{
		return "board/write";
	}
	
// 글 쓰기 내 파일 삭제 ----------------	
	@PostMapping("fileDelete")
	public void setfileDelete(QnaFileVO qnaFileVO)throws Exception{
		//DB에서 fileNum으로 fileName 조회
		//그 후 DB에서 삭제
		//실제 파일 경로에서 파일 삭제
	}
	
// 목록 --------------------------------	
	@GetMapping("list")
	public ModelAndView getList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		List<QnaVO> ar = qnaService.getList(pager);
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/list");
		return mv;
	}

// 디테일 --------------------------------	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("vo", qnaVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
// Hack ---------------------------------
	@GetMapping("hack")
	@ResponseBody
	public int hack (QnaVO qnaVO) throws Exception {
		qnaService.setAdd(qnaVO);
		
		return 1;
	}


	
}
