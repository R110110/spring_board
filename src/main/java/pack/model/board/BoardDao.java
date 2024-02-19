package pack.model.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	@Autowired
	private BoardMapperInterface boardMapperInterface;
	
	// 게시글 목록을 읽어와 list로 반환
	public List<BoardDto> getBoardList(){
		List<BoardDto> boardList = boardMapperInterface.selectBoardList();
		
		return boardList;
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
	
	
}
