package pack.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import pack.model.user.UserDao;
import pack.model.user.UserDto;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@PostMapping("loginConfirm")
	public String loginController(@ModelAttribute("item") UserDto userDto, Model model) {
		UserDto dto = userDao.loginConfirmProcess(userDto.getUser_id(), userDto.getUser_pwd());
		if(dto != null) {
			// System.out.println("성공");
			return "redirect:/boardDirect";
		} else {
			String msg = "아이디 또는 비밀번호를 확인해주세요";
			model.addAttribute("msg", msg);
			return "login.html";
		}
	}
	
}
