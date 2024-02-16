package pack.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletRequest;
import pack.model.user.UserDao;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
}
