
import org.junit.Assert;
import org.junit.Test;
import ticket.MyTicketService;
import ticket.SeatHold;
import tools.Logger;

public class Test7 {
    private String email1 = "mamezcua94@gmail.com";
    private String email2 = "mau.amezcua@hotmail.com";

    @Test
    public void twoClientsReserveOne() {
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


        SeatHold sh1 = MyTicketService.getInstance().findAndHoldSeats(8, email1);

        Assert.assertEquals("---********----\n" +
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
        Assert.assertEquals(142, MyTicketService.getInstance().numSeatsAvailable());

        SeatHold sh2 = MyTicketService.getInstance().findAndHoldSeats(2, email2);

        Assert.assertEquals("**-********----\n" +
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

        try {
            Thread.sleep(2000);

            MyTicketService.getInstance().reserveSeats(sh2.getId(), email2);

            Assert.assertEquals(148, MyTicketService.getInstance().numSeatsAvailable());
            Assert.assertEquals("**-------------\n" +
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
