package org.iti.project.presistence.util;

import java.io.*;
import java.util.Properties;

class PropertiesConnection extends Properties {

  private Properties dbInfo;

  public String getDbUrl() {
    return dbUrl;
  }

  public void setDbUrl(String dbUrl) {
    this.dbUrl = dbUrl;
  }

  private String dbUrl;
  PropertiesConnection ()
  {
    dbUrl = "jdbc:mysql://localhost:3306/chat_app_project";
     dbInfo =new Properties();
  }

   Properties getPropertiesConnection()
  {
    try (InputStream input = new FileInputStream("/config.properties")) {

      Properties prop = new Properties();

      // load a properties file
      prop.load(input);

      // get the property value and print it out
      System.out.println(prop.getProperty("db.user"));
      System.out.println(prop.getProperty("db.password"));

    } catch (FileNotFoundException e) {
      System.err.println("Config File Property Of DB doesn't found");
      e.getMessage();
    } catch (IOException e) {
      e.getMessage();
    }
    return new PropertiesConnection();
  }

  void setPropertiesConnection()
  {
    try (OutputStream output = new FileOutputStream("/config.properties")) {

      Properties prop = new Properties();

      // set the properties value
      prop.setProperty("db.user", "chat");
      prop.setProperty("db.password", "password");

      // save properties to project root folder
      prop.store(output, null);

      System.out.println(prop);

    } catch (IOException io) {
      io.getMessage();
    }
  }
}
