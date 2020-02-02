// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC155b9a3afe1f4581e79fb5a3a1c9958a";
    public static final String AUTH_TOKEN =
            "9f07891b8ba86f254a807f7e3db47bbf";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+17144735800"), // to
                        new PhoneNumber("+14094403190"), // from
                        "good mornign alexisno")
                .create();

        System.out.println(message.getSid());
    }
}