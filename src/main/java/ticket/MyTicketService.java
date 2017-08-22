package ticket;

import tools.Logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class MyTicketService implements TicketService {
    private int numRows;
    private int numCols;
    private static MyTicketService instance = null;
    public int seatIds[][];
    public HashMap<Integer, SeatHold> seatHoldInformation;

    protected MyTicketService() {
        Logger.getInstance().log("MyTicketService");
        this.numRows = 10;
        this.numCols = 15;
        this.setup();
    }

    public void setup () {
        this.seatHoldInformation = new HashMap<Integer, SeatHold>();
        seatIds = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                seatIds[i][j] = -1;
            }
        }
    }

    @Override
    public int numSeatsAvailable () {
        int result = 0;
        for (int[] row: seatIds) {
            for (int seatId: row) {
                if (isSeatAvailable(seatId)) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private boolean isSeatAvailable(int seatId) {
        return seatId == -1 || (this.seatHoldInformation.containsKey(seatId) && this.seatHoldInformation.get(seatId).isExpired());
    }

    private List<List<Integer>> rowSeatGroupings (int row) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> group = new LinkedList<Integer>();
        int[] rowSeatIds = seatIds[row];
        for (int i = 0; i < numCols; i++) {
            int seatId = rowSeatIds[i];
            if (isSeatAvailable(seatId)) {
                group.add(i);
            } else if (group.size() > 0) {
                result.add(group);
                group = new LinkedList<Integer>();
            }
        }
        if (group.size () > 0) {
            result.add(group);
        }

        return result;
    }

  @Override
  public SeatHold findAndHoldSeats (int numSeats, String customerEmail) {
      // hold consecutive seats...
      for (int i = 0; i < numRows; i++) {
          for (List<Integer> group: rowSeatGroupings(i)) {
              if (numSeats <= group.size()) {
                  int seatHoldId = seatHoldInformation.size() + 1;
                  List<Integer> seatList = group.subList((group.size() - numSeats) / 2, (group.size() + numSeats) / 2);
                  for (int seat : seatList) {
                      seatIds[i][seat] = seatHoldId;
                  }
                  HashMap<Integer, List<Integer>> seats = new HashMap<Integer, List<Integer>>();
                  seats.put(i, seatList);
                  SeatHold sh = new SeatHold(seatHoldId, customerEmail, seats);
                  seatHoldInformation.put(sh.getId(), sh);
                  return sh;
              }
          }
      }
      // seat people anywhere
      if (numSeats <= numSeatsAvailable()) {
          // there are enough seats to process user request
          int holdSeats = numSeats;
          int seatHoldId = seatHoldInformation.size() + 1;
          HashMap<Integer, List<Integer>> seats = new HashMap<Integer, List<Integer>>();
          for (int i = 0; i < numRows; i++) {
              for (List<Integer> group : rowSeatGroupings(i)) {
                  List<Integer> seatList;
                  if (holdSeats > group.size()) {
                      seatList = group;
                  } else {
                      seatList = group.subList((group.size() - holdSeats) / 2, (group.size() + holdSeats) / 2);
                  }
                  for (int seat : seatList) {
                      seatIds[i][seat] = seatHoldId;
                  }
                  holdSeats -= seatList.size();
                  seats.put(i, seatList);
                  if (holdSeats == 0) {
                      SeatHold sh = new SeatHold(seatHoldId, customerEmail, seats);
                      seatHoldInformation.put(sh.getId(), sh);
                      return sh;
                  }
              }
          }
      }
      return null;
  }

  @Override
  public String reserveSeats (int seatHoldId, String customerEmail) {
        Logger.getInstance().log("reserveSeats(" + seatHoldId + ", " + customerEmail + ")");
        if (seatHoldInformation.containsKey(seatHoldId)) {
            SeatHold sh = seatHoldInformation.get(seatHoldId);
            if (sh.getCustomerEmail().equals(customerEmail)) {
                sh.setReserved();
                return UUID.randomUUID().toString();
            }
        };
        return null;
  }

    public int[][] getAvailableSeats () {
        int [][] result = new int [numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (isSeatAvailable(seatIds[i][j])){
                    result[i][j] = 1;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }

  public static MyTicketService getInstance() {
    if (instance == null) {
      instance = new MyTicketService();
    }
    return instance;
  }

    public String toString () {
        StringBuilder sb = new StringBuilder () ;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int seatId = seatIds[i][j];
                if (isSeatAvailable(seatId)) {
                    sb.append("-");
                } else {
                    sb.append("*");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
