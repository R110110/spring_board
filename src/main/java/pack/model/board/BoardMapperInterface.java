package pack.model.board;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BoardMapperInterface {
	
	@Select(" select post_no, post_title, post_body, user_id, post_date, post_views from board"
			+ " left join user on post_user_no = user_no order by post_no desc limit #{rowCount} offset #{offset};")
	List<BoardDto> selectBoardList(Pagination pagination);
	
	@Select("select post_no, post_title, post_body, post_user_no, user_id, post_date, post_views from board "
			+ "left join user on post_user_no = user_no where post_no = #{post_no}")
	BoardDto selectPostDetail(int post_no);
	
	@Select("select count(*) from board")
	int boardCount();
	
	@Select("select count(*) from board left join user on post_user_no = user_no where user_id = #{user_id}")
	int idSearchCount(String user_id);
	
	@Select("select count(*) from board where post_title like CONCAT('%', #{post_title}, '%')")
	int titleSearchCount(String post_title);
	
	@Select("select count(*) from board where post_body like CONCAT('%', #{post_body}, '%')")
	int bodySearchCount(String post_body);
	
	@Update("update board set post_views = post_views + 1 where post_no = #{post_no}")
	int postViewUpdate(int post_no);
	
	@Select(" select post_no, post_title, post_body, user_id, post_date, post_views from board"
			+ " left join user on post_user_no = user_no where user_id like CONCAT('%', #{user_id}, '%') order by post_no desc limit #{rowCount} offset #{offset};")
	List<BoardDto> SearchById(Pagination pagination);
	
	@Select(" select post_no, post_title, post_body, user_id, post_date, post_views from board"
			+ " left join user on post_user_no = user_no where post_title like CONCAT('%', #{post_title}, '%') order by post_no desc limit #{rowCount} offset #{offset};")
	List<BoardDto> SearchByTitle(Pagination pagination);
	
	@Select(" select post_no, post_title, post_body, user_id, post_date, post_views from board"
			+ " left join user on post_user_no = user_no where post_body like CONCAT('%', #{post_body}, '%') order by post_no desc limit #{rowCount} offset #{offset};")
	List<BoardDto> SearchByBody(Pagination pagination);
	
	@Insert("insert into board(post_title, post_body, post_user_no) values(#{post_title}, #{post_body}, #{post_user_no})")
	int posting(@Param("post_title") String post_title, @Param("post_body") String post_body, @Param("post_user_no") int post_user_no);
	
	@Update("update board set post_title = #{post_title}, post_body = #{post_body} "
			+ "where post_no = #{post_no} and post_user_no = #{post_user_no}")
	int updatePost(@Param("post_title") String post_title, @Param("post_body") String post_body, @Param("post_no") int post_no, @Param("post_user_no") int post_user_no);
	
	@Delete("delete from board where post_no = #{post_no} and post_user_no = #{post_user_no}")
	int deletePost(@Param("post_no") int post_no, @Param("post_user_no") int post_user_no);
}
