package arpa.home.springpoll.data.dataSource;


import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import arpa.home.springpoll.data.dataSource.PGDataSourceFactoryProvider.AuthType;




@Profile("dev")
@Configuration
public class PGDataSourceConfigDev {

  @Bean
  public DataSource getDataSource() {
  	
  	AuthType authtype = AuthType.Localhost;
  	PGDataSourceFactory dataSourceFactory = 
  			PGDataSourceFactoryProvider.getFactory(authtype);
  	AuthInitConfig.runInitConfig(authtype);
  	
  	return dataSourceFactory.makeSource();
  }

}