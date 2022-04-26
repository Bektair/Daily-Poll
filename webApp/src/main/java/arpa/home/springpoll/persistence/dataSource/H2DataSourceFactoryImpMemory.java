package arpa.home.springpoll.persistence.dataSource;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class H2DataSourceFactoryImpMemory implements DataSourceFactory {

	
	@Override
	public DataSource makeSource() {
		return insertDataSourceConfig();
	}
	
	private DataSource insertDataSourceConfig() {
		JdbcDataSource ds = new JdbcDataSource();
		//Delay is needed to make h2 keep the content as long as vm lives
		ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
		ds.setUser("sa");
		ds.setPassword("sa");
		
		
		return ds;
	}
	

}
