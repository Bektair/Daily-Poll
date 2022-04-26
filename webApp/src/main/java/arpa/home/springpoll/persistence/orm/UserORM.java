package arpa.home.springpoll.persistence.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserORM {
	private boolean isAdmin;
	private String discord_nickname;
	private String discord_id;
	private String avatar;
}
