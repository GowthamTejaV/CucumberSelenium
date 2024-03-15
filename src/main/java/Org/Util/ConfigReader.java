package Org.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
	private Properties prop;

	/**
	 * @return Properties
	 **/
	public Properties read_properties(String filePath) {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(filePath);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * @param map
	 * @return Properties
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Properties write_properties(Map<String, String> map, String filePath) throws FileNotFoundException, IOException {
		prop = new Properties();
		try (InputStream in = new FileInputStream(filePath)) {
			prop.load(in);
			prop.putAll(map);
			OutputStream out = new FileOutputStream(filePath);
			prop.store(out, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * @param map
	 * @return Properties
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Properties mapToProperties(Map<String, String> map, String filePath) throws FileNotFoundException, IOException {
		prop = new Properties();
		try (InputStream in = new FileInputStream(filePath)) {
			prop.load(in);
			prop.putAll(map);

			OutputStream out = new FileOutputStream(filePath);
			prop.store(out, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("PROP:- " + prop);
		return prop;
	}

	/**
	 * @param key
	 * @param value
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void write_properties(String filePath, String key, String value) throws FileNotFoundException, IOException {
		prop = new Properties();
		try (InputStream in = new FileInputStream(filePath)) {
			prop.load(in);
			prop.setProperty(key, value);

			OutputStream out = new FileOutputStream(filePath);
			prop.store(out, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Map<String, String>
	 * @throws IOException
	 */
	public Map<String, String> propertiesToMap(String filePath) throws IOException {
		prop = new Properties();
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = new FileInputStream(filePath);
		prop.load(in);
		for (String key : prop.stringPropertyNames()) {
			String value = prop.getProperty(key);
			map.put(key, value);
		}
		System.out.println("MAP VALUE:- " + map);
		return map;
	}

}
