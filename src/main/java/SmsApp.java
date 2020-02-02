import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.*;

public class SmsApp {
    public static void main(String[] args) {
        get("/", (req, res) -> "Hello");
    
        post("/sms", (req, res) -> {
        	String body2 = req.queryParams("Body");
            res.type("application/xml");
            Body body = new Body.Builder(body2).build();
            Message sms = new Message.Builder().body(body).build();
            MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
            return twiml.toXml();
        });
    }
}
