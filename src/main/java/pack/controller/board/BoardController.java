package pack.controller.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		return "board.html";
		} else {
			return "login.html";
		}
	}
	
	public String postSearch(Model model) {
		
		return "";
	}
	
	@GetMapping("/postDirect")
	public String postingDirect(Model model) {
		
		return "post.html";
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
