package tools;

import java.util.Date;

public class Logger {
  private static Logger instance;

  protected Logger () {

  }

  public static Logger getInstance() {
    if (instance == null) {
      instance = new Logger();
    }
    return instance;
  }

  public void log (String text) {
    System.out.printf("[%s] Logger => %s \n", new Date(), text);
  }
}
