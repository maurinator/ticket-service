
import org.junit.Assert;
import org.junit.Test;
import ticket.MyTicketService;

public class Test1 {
    private String email = "mamezcua94@gmail.com";

    @Test
    public void hold4seatsAndWaitTillExpire() {
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
        MyTicketService.getInstance().findAndHoldSeats(4, email);
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
            Thread.sleep(2000);
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
