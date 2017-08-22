
import org.junit.Assert;
import org.junit.Test;
import ticket.MyTicketService;
import ticket.SeatHold;
import tools.Logger;

public class Test5 {
    private String email = "mamezcua94@gmail.com";

    @Test
    public void reserveAllSeats() {
        MyTicketService.getInstance().setup();
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
        SeatHold sh = MyTicketService.getInstance().findAndHoldSeats(150, email);
        Assert.assertEquals("***************\n" +
                        "***************\n" +
                        "***************\n" +
                        "***************\n" +
                        "***************\n" +
                        "***************\n" +
                        "***************\n" +
                        "***************\n" +
                        "***************\n" +
                        "***************\n",
                MyTicketService.getInstance().toString()
        );
        Assert.assertEquals(0, MyTicketService.getInstance().numSeatsAvailable());
        MyTicketService.getInstance().reserveSeats(sh.getId(), email);
        try {
            Thread.sleep(2000);
            Assert.assertEquals(0, MyTicketService.getInstance().numSeatsAvailable());
            Assert.assertEquals("***************\n" +
                            "***************\n" +
                            "***************\n" +
                            "***************\n" +
                            "***************\n" +
                            "***************\n" +
                            "***************\n" +
                            "***************\n" +
                            "***************\n" +
                            "***************\n",
                    MyTicketService.getInstance().toString()
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
