package pack.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import pack.model.user.UserDao;
import pack.model.user.UserDto;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("loginConfirm")
	public String loginController(@ModelAttribute("item") UserDto userDto, HttpServletRequest request, Model model) {
		
		// 로그인 폼에서 받아온 정보를 DB와 대조
		UserDto dto = userDao.loginConfirmProcess(userDto.getUser_id(), userDto.getUser_pwd());
		
		if(dto != null) {
			
			// 로그인 성공시 세션 생성 후 게시판 페이지로 이동
			HttpSession session = request.getSession();
			session.setAttribute("user_no", dto.getUser_no());
			session.setAttribute("user_id", dto.getUser_id());
			
			return "redirect:/boardDirect";
		} else {
			
			// 로그인 실패 시 에러 문구를 모델에 담아 로그인 페이지로 다시 이동
			String msg = "아이디 또는 비밀번호를 확인해주세요";
			model.addAttribute("msg", msg);
			
			return "login.html";
		}
	}
	
	@GetMapping("logoutConfirm")
	public String logoutController(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		// 세션 해제 후 메인으로 이동
		session.invalidate();
		
		return "redirect:/";
	}
	
}
