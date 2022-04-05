package arpa.home.springpoll.data.dataSource;

import org.postgresql.PGProperty;
import org.postgresql.ds.PGSimpleDataSource;

public class PGSimpleDataSourceBuilder implements PGSimpleDataSourceInterface {

	private PGSimpleDataSource pg = new PGSimpleDataSource();
	
  public void setServerNames(String[] serverNames) {
  	pg.setServerNames(serverNames);
  }
  
  public void setDatabaseNames(String databaseNames) {
  	pg.setDatabaseName(databaseNames);
  }
  
  public void setPortNumbers(int[] portNumbers) {
  	pg.setPortNumbers(portNumbers);
  }
  
  public void setEncMode(String mode) {
  	pg.setProperty(PGProperty.GSS_ENC_MODE, mode);
  }
  
  public void setUsername(String name) {
  	pg.setUser(name);
  }
  
  public PGSimpleDataSource Build() {
  	return pg;
  }
	
	
}
