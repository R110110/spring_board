package pack.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapperInterface {
	
	@Select("select * from user where user_no = #{user_no}")
	List<UserDto> selectUserInfo(int user_no);
	
	@Select("select * from user where user_id = #{param1} and user_pwd = #{param2} and user_enable = 1")
	UserDto loginConfirm(String user_id, String user_pwd);
}
