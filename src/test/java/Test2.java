
import org.junit.Assert;
import org.junit.Test;
import ticket.MyTicketService;
import ticket.SeatHold;

public class Test2 {
    private String email = "mamezcua94@gmail.com";
    @Test
    public void hold4seatsAndReserveThem() {
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
        SeatHold sh = MyTicketService.getInstance().findAndHoldSeats(4, email);
        Assert.assertEquals("-----****------\n" +
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
        Assert.assertEquals(146, MyTicketService.getInstance().numSeatsAvailable());
        try {
            MyTicketService.getInstance().reserveSeats(sh.getId(), email );
            Thread.sleep(2000);
            Assert.assertEquals("-----****------\n" +
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
