package arpa.home.springpoll.persistence.dataSource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import arpa.home.springpoll.persistence.dataSource.DataSourceFactoryProvider.AuthType;

@Profile("test")
@Configuration 
public class PGDataSourceConfigTest {
	
	@Bean(destroyMethod="")
  public DataSource getDataSource() {
  	AuthType authtype = AuthType.Memory;
  	DataSourceFactory dataSourceFactory = 
  			DataSourceFactoryProvider.getFactory(authtype);
  	AuthInitConfig.runInitConfig(authtype);
  	return dataSourceFactory.makeSource();
  }
  
}
