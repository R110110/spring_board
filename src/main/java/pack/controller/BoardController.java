package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pack.model.BoardDao;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	@GetMapping("/")
	public String DirectMain(Model model) {
		
		return "main.html";
	}
}
