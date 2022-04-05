package arpa.home.springpoll.data.dataSource;

import org.postgresql.ds.PGSimpleDataSource;

public interface PGSimpleDataSourceInterface extends PGKerberosConfig {


  
  public PGSimpleDataSource Build();
	
}
