package arpa.home.springpoll;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


//Binds Environment properties to this
@Validated
@ConfigurationProperties
public class ValidateConfigProperties {

	
	private String dailyAssets;
	

	
	@AssertTrue
	boolean isDailyAssetsValid() {
		
		System.out.println(dailyAssets);
		
		return dailyAssets.contains("resources");
		
	}
	
	public String getDailyAssets() {
		return this.dailyAssets;
	}

	public void setDailyAssets(String dailyAssets) {
		this.dailyAssets = dailyAssets;
	}
	
	
	
	
}
