package pack.model.user;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
	private int user_no;
	private String user_id, user_pwd, user_mail;
	private LocalDate user_join_date;
	private boolean user_enable;
}
