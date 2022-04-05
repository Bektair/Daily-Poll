package arpa.home.springpoll.data.dataSource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import arpa.home.springpoll.data.dataSource.PGDataSourceFactoryProvider.AuthType;

@Profile("test")
@Configuration 
public class PGDataSourceConfigTest {
	
  @Bean
  public DataSource getDataSource() {
  	AuthType authtype = AuthType.Localhost;
  	PGDataSourceFactory dataSourceFactory = 
  			PGDataSourceFactoryProvider.getFactory(authtype);
  	AuthInitConfig.runInitConfig(authtype);
  	
  	return dataSourceFactory.makeSource();
  }

}
