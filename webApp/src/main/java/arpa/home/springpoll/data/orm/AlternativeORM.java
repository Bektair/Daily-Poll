package arpa.home.springpoll.data.orm;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.Nullable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "Alternative")
public class AlternativeORM {
	String altTxt;
	Integer altOrder;
	
	@Nullable
	String emojiId;
	@Nullable
	String emoteId;

	@Id
	@GeneratedValue(generator="altSequenceGenerator")
	@SequenceGenerator(
      name = "altSequenceGenerator",
      sequenceName="altSequence", 
      allocationSize=1, initialValue=4
  )
	BigInteger altId;
	
	public AlternativeORM(Integer altOrder, String altTxt, String emojiId) {
		this.altOrder=altOrder;
		this.altTxt = altTxt;
		this.emojiId = emojiId;
	}
	
	//Collection of users that have voted
	
	
	
	@Override
	public boolean equals(Object x) {
		if(x == null) return false;
		if(this.getClass() != x.getClass()) return false;
		AlternativeORM that = ((AlternativeORM)x);
		if(this.getAltId()!=null)
			return (this.altId.compareTo(that.getAltId()))==0;
		else
			return (this.getAltTxt().equals(that.getAltTxt())&&
					this.getAltOrder()==that.getAltOrder());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(altId, altTxt);
	}
	
}
