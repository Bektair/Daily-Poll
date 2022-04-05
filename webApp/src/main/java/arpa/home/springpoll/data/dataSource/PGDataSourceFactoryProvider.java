package arpa.home.springpoll.data.dataSource;

public class PGDataSourceFactoryProvider {

	public enum AuthType{
		Kerberos,
		Localhost,
		Memory
	}
	
	public static PGDataSourceFactory getFactory(AuthType authMethod) {
		switch(authMethod) {
	  case Kerberos: 
		  return new PGDataSourceFactoryImpKerberos();
	  case Localhost:
	  	return new PGDataSourceFactoryImpLocalhost();
	  case Memory:
	  	return new PGDataSourceFactoryImpMemory();
	  default: 
	  	return new PGDataSourceFactoryImpLocalhost();
	}
		
	}
	
}
