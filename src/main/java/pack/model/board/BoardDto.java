package pack.model.board;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BoardDto {
	private int post_no, post_user_no;
	private String post_name, post_body;
	private LocalDate post_date;
}
