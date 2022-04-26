package arpa.home.springpoll.persistence.dataSource;

import org.postgresql.ds.PGSimpleDataSource;

public interface PGSimpleDataSourceInterface extends PGKerberosConfig {


  
  public PGSimpleDataSource Build();
	
}
