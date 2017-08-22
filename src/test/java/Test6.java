
import org.junit.Assert;
import org.junit.Test;
import ticket.MyTicketService;
import ticket.SeatHold;
import tools.Logger;

public class Test6 {
    private String email = "mamezcua94@gmail.com";

    @Test
    public void reserveImpossibleSeats() {
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
        MyTicketService.getInstance().findAndHoldSeats(151, email);
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
        Assert.assertEquals(150, MyTicketService.getInstance().numSeatsAvailable());
        try {
            Thread.sleep(2000);
            Assert.assertEquals(150, MyTicketService.getInstance().numSeatsAvailable());
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
