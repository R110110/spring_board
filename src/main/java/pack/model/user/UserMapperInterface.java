package pack.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapperInterface {
	
	@Select("select * from user where user_no = #{user_no}")
	List<UserDto> selectUserInfo(int user_no);
	
	@Select("select * from user where user_id = #{param1} and user_pwd = #{param2} and user_enable = 1")
	UserDto loginConfirm(String user_id, String user_pwd);
	
	@Select("select count(*) from user where user_id = #{user_id}")
	int idCheck(String user_id);
	
	@Insert("insert into user(user_id, user_pwd, user_mail) values(#{param1}, #{param2}, #{param3})")
	int signinConfirm(String user_id, String user_pwd, String user_mail);
}
