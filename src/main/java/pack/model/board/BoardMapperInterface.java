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
			+ " left join user on post_user_no = user_no;")
	List<BoardDto> selectBoardList();
	
	@Select("select post_no, post_title, post_body, post_user_no, user_id, post_date, post_views from board "
			+ "left join user on post_user_no = user_no where post_no = #{post_no}")
	BoardDto selectPostDetail(int post_no);
	
	@Update("update board set post_views = post_views + 1 where post_no = #{post_no}")
	int postViewUpdate(int post_no);
	
	@Select("")
	List<BoardDto> selectSearchedPost(String word);
	
	@Insert("insert into board(post_title, post_body, post_user_no) values(#{post_title}, #{post_body}, #{post_user_no})")
	int posting(@Param("post_title") String post_title, @Param("post_body") String post_body, @Param("post_user_no") int post_user_no);
	
	@Update("update board set post_title = #{post_title}, post_body = #{post_body} "
			+ "where post_no = #{post_no} and post_user_no = #{post_user_no}")
	int updatePost(@Param("post_title") String post_title, @Param("post_body") String post_body, @Param("post_no") int post_no, @Param("post_user_no") int post_user_no);
	
	@Delete("delete from board where post_no = #{post_no} and post_user_no = #{post_user_no}")
	int deletePost(int post_no, int post_user_no);
}
