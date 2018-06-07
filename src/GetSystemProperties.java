import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class GetSystemProperties {
  public HashMap<String,String> properties ;
  GetSystemProperties(){
	  properties= new HashMap<String,String>();
  }
  public HashMap<String,String> getProperties(){
	  Properties prop = System.getProperties();
	    Enumeration keys = prop.keys();
	    while (keys.hasMoreElements()) {
	    String key = (String)keys.nextElement();
	    String value = (String)prop.get(key);
	    properties.put(key, value);
	    
        }
	    return properties;
   }
  }
