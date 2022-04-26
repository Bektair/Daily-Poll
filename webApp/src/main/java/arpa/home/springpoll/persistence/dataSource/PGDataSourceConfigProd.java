package arpa.home.springpoll.persistence.dataSource;


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

import arpa.home.springpoll.persistence.dataSource.DataSourceFactoryProvider.AuthType;



@Profile("prod")
@Configuration 
public class PGDataSourceConfigProd {

	//Datasources should prevent the default destruction, as per official docs
	@Bean(destroyMethod="")
  public DataSource getDataSource() {
  	AuthType authtype = AuthType.Localhost;
  	DataSourceFactory dataSourceFactory = 
  			DataSourceFactoryProvider.getFactory(authtype);
  	AuthInitConfig.runInitConfig(authtype);
  	
  	
  	return dataSourceFactory.makeSource();
  }
  

}