import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "ACe2ace5ecbc47778a06fb34c8598b98c6";
    public static final String AUTH_TOKEN =
            "a2d150381dca1bb47e221fd0acdb58ba";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+17144735800"), // to
                        new PhoneNumber("+18509192460"), // from
                        "hello there")
                .create();

        System.out.println(message.getSid());
    }
}