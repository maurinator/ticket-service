package ticket;

import tools.Logger;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


public class SeatHold {
  private static int MILLI_TIMEOUT = 2000;

  private int id;
  private GregorianCalendar when;
  private String customerEmail;
  private HashMap<Integer, List<Integer>> seats;
  private boolean reserved;

  public SeatHold (int id, String customerEmail, HashMap<Integer, List<Integer>> seats) {
    this.id = id;
    this.when = new GregorianCalendar();
    this.customerEmail = customerEmail;
    this.seats = seats;
    this.reserved = false;
  }

  public int getId () {
    return this.id;
  }

  public String getCustomerEmail () {
      return this.customerEmail;
  }

  public HashMap<Integer, List<Integer>> getSeats () {
    return this.seats;
  }

  public boolean isExpired ( ) {
    GregorianCalendar g = new GregorianCalendar();
    return !reserved && g.getTimeInMillis() - this.when.getTimeInMillis() > MILLI_TIMEOUT;
  }

  public void setReserved () {
      this.reserved = true;
  }
}
