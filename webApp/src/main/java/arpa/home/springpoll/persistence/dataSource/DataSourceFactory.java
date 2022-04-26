package arpa.home.springpoll.persistence.dataSource;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

public interface DataSourceFactory {


	public DataSource makeSource();
	
	
}
