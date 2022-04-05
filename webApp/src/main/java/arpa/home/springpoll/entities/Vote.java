package arpa.home.springpoll.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
	private long discord_id;
	private int alt_id;
}
