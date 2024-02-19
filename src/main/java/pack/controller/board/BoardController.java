package pack.controller.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pack.model.board.BoardDao;
import pack.model.board.BoardDto;
import pack.model.user.UserDto;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	@GetMapping("/")
	public String directMain(Model model) {
		
		// 세션 체크 기능 추후 추가
		// ArrayList<BoardDto> boardList = (ArrayList<BoardDto>) boardDao.getBoardList();
		
		// 게시글 목록은 model 객체에 담아 프론트로 전송
		// model.addAttribute("boardList", boardList);
		
		model.addAttribute("item", new UserDto());
		
		return "login.html";
	}
	
	@GetMapping("/boardDirect")
	public String boardDircet(Model model) {
		
		// 세션 체크 기능 추후 추가
		// ArrayList<BoardDto> boardList = (ArrayList<BoardDto>) boardDao.getBoardList();
		
		// 게시글 목록은 model 객체에 담아 프론트로 전송
		// model.addAttribute("boardList", boardList);
		
		
		return "board.html";
	}
	
	public String postSearch(Model model) {
		
		return "";
	}
	
	public String postingDirect(Model model) {
		
		return "";
	}
	
	public String posting(Model model) {
		
		return "";
	}
	
	public String updatePost(Model model) {
		
		return "";
	}
	
	public String deletePost(Model model) {
		
		return "";
	}
}
