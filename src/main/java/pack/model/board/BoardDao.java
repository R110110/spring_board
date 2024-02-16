package pack.model.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	
	@Autowired
	private BoardMapperInterface boardMapperInterface;
}
