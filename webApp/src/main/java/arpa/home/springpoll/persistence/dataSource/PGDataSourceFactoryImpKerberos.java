package arpa.home.springpoll.persistence.dataSource;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;




public class PGDataSourceFactoryImpKerberos implements DataSourceFactory {

	
	
	public PGDataSourceFactoryImpKerberos() {
  	
  }

	
	@Override
	public PGSimpleDataSource makeSource() {
			  return buildPGDataSourceWithProdConfig();
	}


	
	private PGSimpleDataSource buildPGDataSourceWithProdConfig() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return  buildPGDataSourceWithKerberosConfig();
  }
  


	private PGSimpleDataSource buildPGDataSourceWithKerberosConfig() {
  	PGSimpleDataSourceInterface di = new PGSimpleDataSourceBuilder();
    String[] servers = {"pg.home.arpa"};
    di.setEncMode("require");
    di.setServerNames(servers);
    di.setUsername("webserver");
    return di.Build();
	}
	



}
