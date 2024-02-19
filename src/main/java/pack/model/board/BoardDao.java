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
	
	
}
