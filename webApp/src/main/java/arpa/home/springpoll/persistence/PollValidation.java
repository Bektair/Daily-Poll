package arpa.home.springpoll.persistence;

import java.math.BigInteger;

public class PollValidation {

	protected boolean isValidId(String id) {
		try {
			if(id.isBlank()) return false;
			BigInteger bigId = new BigInteger(id);
			if(bigId.compareTo(BigInteger.ZERO)<0) return false;
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}
