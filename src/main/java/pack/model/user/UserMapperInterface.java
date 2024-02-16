package pack.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapperInterface {
	
	@Select("select * from user where user_no = #{user_no}")
	List<UserDto> selectUserInfo(int user_no);
}
