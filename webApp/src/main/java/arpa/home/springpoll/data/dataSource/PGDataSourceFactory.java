package arpa.home.springpoll.data.dataSource;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

public interface PGDataSourceFactory {


	public DataSource makeSource();
	
	
}
