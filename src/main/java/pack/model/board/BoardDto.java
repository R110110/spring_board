package pack.model.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardDto {
	private int post_no, post_user_no, post_views;
	private String post_title, post_body, word, user_id;
	private Timestamp post_date;
}
