package pack.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	@Autowired
	private BoardMapperInterface boardMapperInterface;
	
	public int totalRecord() {
		return boardMapperInterface.boardCount();
	}
	
	// 게시글 목록을 읽어와 list로 반환
	public List<BoardDto> getBoardList(Pagination pagination){
		List<BoardDto> boardList = boardMapperInterface.selectBoardList(pagination);
		return boardList;
	}
	
	// 페이징
	public int getCount() {
		int count = boardMapperInterface.boardCount();
		return count;
	}
	
	// 게시글 상세 정보 불러오기
	public BoardDto getPostDetail(int post_no) {
		BoardDto boardDto = boardMapperInterface.selectPostDetail(post_no);
		
		return boardDto;
	}
	
	// 조회수 업데이트
	public boolean viewUpdateProcess(int post_no) {
		
		boolean flag = false;
		int updating = boardMapperInterface.postViewUpdate(post_no);
		
		if (updating > 0) flag = true;
		return flag;

	}
	
	// 게시물 업로드
	public boolean postingProcess(String post_title, String post_body, int post_user_no) {
		
		boolean updateFlag = false;
		int update = boardMapperInterface.posting(post_title, post_body, post_user_no);
		
		if (update > 0) updateFlag = true;
		return updateFlag;
	}
	
	// 게시물 수정
	public boolean updateProcess(String post_title, String post_body, int post_no, int post_user_no) {
		
		boolean flag = false;
		int posting = boardMapperInterface.updatePost(post_title, post_body, post_no, post_user_no);
		
		if (posting > 0) flag = true;
		return flag;
	}
	
	// 게시물 삭제
	public boolean deleteProcess(int post_no, int post_user_no) {
		
		boolean flag = false;
		int posting = boardMapperInterface.deletePost(post_no, post_user_no);
		
		if (posting > 0) flag = true;
		return flag;
	}
	
	
}
