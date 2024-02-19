package pack.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private UserMapperInterface userMapperInterface;
	
	// 로그인 요청 확인
	public UserDto loginConfirmProcess(String user_id, String user_pwd) {
		
		UserDto userDto = userMapperInterface.loginConfirm(user_id, user_pwd);
		
		// 아이디 비밀번호 DB 대조 후 유저가 존재하면 유저 번호 반환
		return userDto;
	}
}
