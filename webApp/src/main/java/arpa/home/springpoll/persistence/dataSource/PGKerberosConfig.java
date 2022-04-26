package arpa.home.springpoll.persistence.dataSource;

public interface PGKerberosConfig  {

  public void setServerNames(String[] serverNames);
  
  public void setDatabaseNames(String databaseNames);
  
  public void setPortNumbers(int[] portNumbers);
  
  public void setEncMode(String mode);
  
  public void setUsername(String name);
}
