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
	
	// 아이디 중복 체크
	public int idCheckProcess(String user_id) {
		
		// 중복시 1, 중복이 아닐시 0을 반환
		int idCount = userMapperInterface.idCheck(user_id);
		return idCount;
	}
	
	// 회원가입 정보 DB 저장
	public boolean signinProcess(String user_id, String user_pwd, String user_mail) {
		
		boolean flag = false;
		int signin = userMapperInterface.signinConfirm(user_id, user_pwd, user_mail);
		
		if (signin > 0) flag = true;
		
		return flag;
	}
}
