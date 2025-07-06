package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadFile {
	
	Properties prop = new Properties();

	public ReadFile()  {

		File file = new File("config.properties");
		try {
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
	}
	catch(Exception e)
	{
		System.err.println("Exception is "+e.getMessage());
	}

	}

	
	
	public String urlvalue()
	{
		String url = prop.getProperty("URL");
		return url;
	}

	
}

