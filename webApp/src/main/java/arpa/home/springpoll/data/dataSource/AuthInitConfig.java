package arpa.home.springpoll.data.dataSource;

import arpa.home.springpoll.data.dataSource.PGDataSourceFactoryProvider.AuthType;

public class AuthInitConfig {

	
	public static void runInitConfig(AuthType authType) {
		switch(authType) {
			case Kerberos:{
				setKerberosSystemProperties();
			}
			case Localhost:{
				break;
			}
			default:
				break;
			}
	}
	
  private static void setKerberosSystemProperties() {
  	System.setProperty("java.security.krb5.conf",System.getenv("KRB5_CONFIG"));
    System.setProperty("javax.security.auth.useSubjectCredsOnly","false");
    System.setProperty("java.security.auth.login.config",System.getenv("KRB5_JAAS"));
  }
}
