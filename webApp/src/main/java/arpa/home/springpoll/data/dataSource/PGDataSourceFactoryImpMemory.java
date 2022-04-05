package arpa.home.springpoll.data.dataSource;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class PGDataSourceFactoryImpMemory implements PGDataSourceFactory {

	
	@Override
	public DataSource makeSource() {
		return insertDataSourceConfig();
	}
	
	private DataSource insertDataSourceConfig() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2://mem:db;DB_CLOSE_DELAY=-1");
		ds.setUsername("sa");
		ds.setPassword("sa");
		return ds;
	}
	

}
