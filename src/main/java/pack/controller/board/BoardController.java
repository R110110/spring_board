package pack.controller.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import pack.model.board.BoardDao;
import pack.model.board.BoardDto;
import pack.model.user.UserDto;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	@GetMapping("/")
	public String directMain(HttpServletRequest request, Model model) {
		
		// 세션 체크
		HttpSession session = request.getSession();
		if (session.getAttribute("user_no") != null) {
		
		return "redirect:/boardDirect";
		} else {
			
			// 정보를 입력받을 userdto 객체를 프론트로 전송
			model.addAttribute("item", new UserDto());
			return "login.html";
		}
		
	}
	
	@GetMapping("/boardDirect")
	public String boardDircet(HttpServletRequest request, Model model) {
		
		// 세션 체크
		HttpSession session = request.getSession();
		if (session.getAttribute("user_no") != null) {
		
		 ArrayList<BoardDto> boardList = (ArrayList<BoardDto>) boardDao.getBoardList();
		System.out.println(session.getAttribute("user_no"));
		// 게시글 목록을 model 객체에 담아 프론트로 전송
		 model.addAttribute("datas", boardList);
		
		// 세션이 존재할 시 게시판으로, 존재하지 않을 시 로그인 화면으로 이동
		return "board.html";
		} else {
			return "redirect:/";
		}
	}
	
	public String postSearch(Model model) {
		
		
		return "";
	}
	
	@GetMapping("/postDetail")
	public String postingDetail(@RequestParam("post_no") int post_no, Model model) {
		
		BoardDto boardDto = boardDao.getPostDetail(post_no);
		
		// 조회수 업데이트 성공시 게시글 상세 페이지로 이동, 실패시 오류 페이지로 이동
		if (boardDao.viewUpdateProcess(post_no)) {
		model.addAttribute("postDetailData", boardDto);
		System.out.println(boardDto);
		return "post.html";
		} else {
			return "error.html";
		}
	}
	
	@GetMapping("posting")
	public String posting(Model model) {
		
		return "posting.html";
	}
	
	public String updatePost(Model model) {
		
		return "";
	}
	
	public String deletePost(Model model) {
		
		return "";
	}
}
