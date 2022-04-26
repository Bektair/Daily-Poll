package arpa.home.springpoll.persistence.dataSource;

public class DataSourceFactoryProvider {

	public enum AuthType{
		Kerberos,
		Localhost,
		Memory
	}
	
	public static DataSourceFactory getFactory(AuthType authMethod) {
		switch(authMethod) {
	  case Kerberos: 
		  return new PGDataSourceFactoryImpKerberos();
	  case Localhost:
	  	return new PGDataSourceFactoryImpLocalhost();
	  case Memory:
	  	return new H2DataSourceFactoryImpMemory();
	  default: 
	  	return new PGDataSourceFactoryImpLocalhost();
	}
		
	}
	
}
