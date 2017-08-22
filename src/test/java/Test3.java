
import org.junit.Assert;
import org.junit.Test;
import ticket.MyTicketService;
import ticket.SeatHold;

import java.util.LinkedList;
import java.util.List;

public class Test3 {
    private String email = "mamezcua94@gmail.com";
    @Test
    public void findRowThatCanHoldGroupPersistent() {
        MyTicketService.getInstance().setup();
        List<Integer> seatIds = new LinkedList<Integer>();
        Assert.assertEquals("---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n",
                MyTicketService.getInstance().toString()
        );
        seatIds.add(MyTicketService.getInstance().findAndHoldSeats(6, email).getId());
        Assert.assertEquals("----******-----\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n",
                MyTicketService.getInstance().toString()
        );
        Assert.assertEquals(144, MyTicketService.getInstance().numSeatsAvailable());
        seatIds.add(MyTicketService.getInstance().findAndHoldSeats(4, email).getId());
        Assert.assertEquals("**********-----\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n",
                MyTicketService.getInstance().toString()
        );
        Assert.assertEquals(140, MyTicketService.getInstance().numSeatsAvailable());
        seatIds.add(MyTicketService.getInstance().findAndHoldSeats(8, email).getId());
        Assert.assertEquals("**********-----\n" +
                        "---********----\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n" +
                        "---------------\n",
                MyTicketService.getInstance().toString()
        );
        try {
            for (int seatId: seatIds) {
                MyTicketService.getInstance().reserveSeats(seatId, email);
            }
            Thread.sleep(2000);
            Assert.assertEquals(132, MyTicketService.getInstance().numSeatsAvailable());
            Assert.assertEquals("**********-----\n" +
                            "---********----\n" +
                            "---------------\n" +
                            "---------------\n" +
                            "---------------\n" +
                            "---------------\n" +
                            "---------------\n" +
                            "---------------\n" +
                            "---------------\n" +
                            "---------------\n",
                    MyTicketService.getInstance().toString()
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
