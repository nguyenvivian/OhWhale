import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.*;

import java.io.IOException;
import java.util.HashMap;

public class SmsApp {
	public static String message = "Get ready to start the game!";
	public static String user;
	public static String requestbody;
    public static void main(String[] args) throws IOException {
    	HashMap<String, Board> users = new HashMap<String, Board>();
    	
    	get("/", (req, res) -> "Hello");
        
        post("/sms", (req, res) -> {
        	requestbody = req.queryParams("Body");
        	user = req.queryParams("From");
        	if (!users.containsKey(user)) {
        		users.put(user, new Board(7,7,8));
        		message = "Get ready to help Gale the Whale 🐳 clean up the oil spill! Enter any character to begin.";
        	}
        	else {
        		Board gameboard = users.get(user);
        		if (gameboard.isGameOver() && gameboard.isPlayerAlive()) {
        			message = "Congratulations you cleaned up the oil spill! Looks like everything whale be okay. Please consider donating to the Pacific Whale Foundation: https://www.pacificwhale.org/";
        		}
        		else if(gameboard.isGameOver() && !gameboard.isPlayerAlive()) {
        			message = "You lost (sometimes life can be over-whale-ming)! Fortunately for you, you have a second chance at saving whales if you consider donating to the Pacific Whale Foundation: https://www.pacificwhale.org/";
        		}
        		else {
	        		gameboard.moveAllCharacters(requestbody.toUpperCase());
	        		message = gameboard.toString() + "What should Gale the Whale do?\n\n"
	        				+ "U: Move Up\n" + "D: Move Down\n" + "L: Move Left\n"
	        				+ "R: Move Right\n" + "S: Sponge!\n";
	        		users.put(user, gameboard); 
        		}		
        	}      
        	
            res.type("application/xml");
            
            Body body = new Body.Builder(message).build();
            Message sms = new Message.Builder().body(body).build();
            MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
            return twiml.toXml();	
        });
    }
}
