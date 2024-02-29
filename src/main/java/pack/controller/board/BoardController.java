package pack.controller.board;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import pack.model.board.BoardDao;
import pack.model.board.BoardDto;
import pack.model.board.Pagination;
import pack.model.user.UserDto;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

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
	public String boardDircet(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest request, Model model) {
		
		// 세션 체크
		HttpSession session = request.getSession();
		if (session.getAttribute("user_no") != null) {
			// 페이징
			Pagination pagination = new Pagination(boardDao.getCount(), page);
//			logger.info("pagination" + pagination);
			ArrayList<BoardDto> boardList = (ArrayList<BoardDto>) boardDao.getBoardList(pagination);

			// 게시글 목록을 model 객체에 담아 프론트로 전송
			model.addAttribute("datas", boardList);
			model.addAttribute("page", page);
			model.addAttribute("pagination", pagination);
			
//			model.addAttribute("searchItem", new BoardDto());
			
			// 세션이 존재할 시 게시판으로, 존재하지 않을 시 로그인 화면으로 이동
			return "board";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/search")
	public String postSearch(@RequestParam(value = "page", defaultValue = "1") int page, @ModelAttribute BoardDto boardDto, Model model) {
		System.out.println(boardDto);
		System.out.println(boardDao.getTitleSearchCount(boardDto.getPost_title()));
		ArrayList<BoardDto> boardList = new ArrayList<BoardDto>();
		
		// 카테고리별로 검색 결과 출력 후 페이징
		if (boardDto.getPost_title() != null) {
			
			Pagination pagination = new Pagination(boardDao.getTitleSearchCount(boardDto.getPost_title()), page);
			pagination.setPost_title(boardDto.getPost_title());
			boardList = (ArrayList<BoardDto>) boardDao.searchByTitleProcess(pagination);
//			System.out.println(boardList);
			model.addAttribute("pagination", pagination);
			
		} else if (boardDto.getUser_id() != null) {
			
			Pagination pagination = new Pagination(boardDao.getIdSearchCount(boardDto.getUser_id()), page);
			pagination.setUser_id(boardDto.getUser_id());
			boardList = (ArrayList<BoardDto>) boardDao.searchByIdProcess(pagination);
			model.addAttribute("pagination", pagination);
			
		} else if (boardDto.getPost_body() != null) {
			
			Pagination pagination = new Pagination(boardDao.getBodySearchCount(boardDto.getPost_body()), page);
			pagination.setPost_body(boardDto.getPost_body());
			boardList = (ArrayList<BoardDto>) boardDao.searchByBodyProcess(boardDto.getPost_body(), pagination);
			model.addAttribute("pagination", pagination);
			
		}
		
		model.addAttribute("datas", boardList);
		model.addAttribute("page", page);
		
		
		return "board.html";
	}

	@GetMapping("/postDetail")
	public String postingDetail(@RequestParam("post_no") int post_no, Model model) {

		BoardDto boardDto = boardDao.getPostDetail(post_no);
		// 조회수 업데이트 성공시 게시글 상세 페이지로 이동, 실패시 오류 페이지로 이동
		if (boardDao.viewUpdateProcess(post_no)) {
			model.addAttribute("postDetailData", boardDto);
//		logger.info("boardDto" + boardDto);
			return "post.html";
		} else {
			return "error.html";
		}
	}

	@GetMapping("posting")
	public String posting(Model model) {

		model.addAttribute("postInput", new BoardDto());
		return "posting.html";
	}

	@PostMapping("postingConfirm")
	public String postingConfirm(@ModelAttribute("postInput") BoardDto boardDto, HttpServletRequest request,
			Model model) {

		HttpSession session = request.getSession();
		boolean flag = false;
		String post_title = boardDto.getPost_title();
		String post_body = boardDto.getPost_body();
		int post_user_no = (int) session.getAttribute("user_no");
		flag = boardDao.postingProcess(post_title, post_body, post_user_no);

		if (flag == true) {
			return "redirect:/boardDirect";
		} else {
			return "error.html";
		}
	}

	@GetMapping("updatePost")
	public String updatePost(HttpServletRequest request, @RequestParam("post_no") int post_no, Model model) {

		HttpSession session = request.getSession();
		BoardDto boardDto = boardDao.getPostDetail(post_no);
		model.addAttribute("postDetailData", boardDto);

		// 세션에서 유저 번호 조회후 일치시 수정 페이지로 이동
		if ((int) session.getAttribute("user_no") != boardDto.getPost_user_no()) {
			String msg = "수정 권한이 없습니다";
			model.addAttribute("msg", msg);
			return "post.html";
		} else {
			return "updatePost.html";
		}
	}

	@PostMapping("updateConfirm")
	public String updateConfirm(@ModelAttribute("postDetailData") BoardDto boardDto, Model model) {

		// DB 업데이트 성공시 해당 게시글 상세조회로 이동, 실패시 게시판 목록 페이지로 이동
		if (boardDao.updateProcess(boardDto.getPost_title(), boardDto.getPost_body(), boardDto.getPost_no(),
				boardDto.getPost_user_no()) == true) {
			return "redirect:/postDetail?post_no=" + boardDto.getPost_no();
		} else {

			return "redirect:/boardDirect";
		}
	}

	@GetMapping("deletePost")
	public String deletePost(@RequestParam("post_no") int post_no, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		BoardDto boardDto = boardDao.getPostDetail(post_no);
		model.addAttribute("postDetailData", boardDto);

		// 세션에서 유저 번호 조회후 일치시 게시물 삭제 진행
		if ((int) session.getAttribute("user_no") != boardDto.getPost_user_no()) {
			String msg = "삭제 권한이 없습니다";
			model.addAttribute("msg", msg);
			return "post.html";
		} else {

			// 삭제 성공시 게시판 목록 페이지로 이동, 실패시 에러 페이지로 이동
			if (boardDao.deleteProcess(boardDto.getPost_no(), boardDto.getPost_user_no()) == true) {
				String msg = "삭제되었습니다";
				model.addAttribute("msg", msg);
				return "redirect:/boardDirect";
			} else {
				return "error.html";
			}
		}

	}
}
