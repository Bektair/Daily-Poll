package arpa.home.springpoll.persistence.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteORM {
	private long discord_id;
	private int alt_id;
}
