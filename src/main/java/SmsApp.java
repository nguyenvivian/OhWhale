import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SmsApp {
	public static String message = "Get ready to start the game!";
	public static String user;
	public static String body2;
    public static void main(String[] args) throws IOException {
		Board gameBoard = new Board(7, 7, 3);

    	FileInputStream serviceAccount =
    			  new FileInputStream("/Users/viviannguyen/Downloads/ohwhale-42e1d-firebase-adminsdk-p32ko-1a7dabc695.json");

    			FirebaseOptions options = new FirebaseOptions.Builder()
    			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    			  .setDatabaseUrl("https://ohwhale-42e1d.firebaseio.com")
    			  .build();

    			FirebaseApp.initializeApp(options);
      	DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    	
    	
    	get("/", (req, res) -> "Hello");
        
        post("/sms", (req, res) -> {
        	body2 = req.queryParams("Body");
        	user = req.queryParams("From");
        	DatabaseReference users = ref.child(user);
        	DatabaseReference userdataCommand = users.child("Body");
        	userdataCommand.setValueAsync(body2);
        	DatabaseReference userdataBoard = users.child("Board");
        	userdataCommand.setValueAsync(gameBoard);
            res.type("application/xml");
            
            ref.addChildEventListener(new ChildEventListener() {
    			@Override
    			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
    				// TODO Auto-generated method stub
    				message = "Please enter a number of spills > 0 and less than or equal to 7:";	
    			}

    			@Override
    			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
    				// TODO Auto-generated method stub
    				message = "ref changed";	

    			}

    			@Override
    			public void onChildRemoved(DataSnapshot snapshot) {
    				// TODO Auto-generated method stub
    				
    			}

    			@Override
    			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
    				// TODO Auto-generated method stub
    				message = "Moved";	

    			}

    			@Override
    			public void onCancelled(DatabaseError error) {
    				// TODO Auto-generated method stub
    				
    			}
        	});
            userdataCommand.addChildEventListener(new ChildEventListener() {

				@Override
				public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
					// TODO Auto-generated method stub
    				message = "userdatacommand changed";	

				}

				@Override
				public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
					// TODO Auto-generated method stub
					message = (String) snapshot.getValue();
				}

				@Override
				public void onChildRemoved(DataSnapshot snapshot) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onCancelled(DatabaseError error) {
					// TODO Auto-generated method stub
					
				}
        		
        	});
            userdataBoard.addChildEventListener(new ChildEventListener() {

				@Override
				public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
					// TODO Auto-generated method stub
					message = "boardchanged";
				}

				@Override
				public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
					// TODO Auto-generated method stub
    				message = "child changed";	

				}

				@Override
				public void onChildRemoved(DataSnapshot snapshot) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onCancelled(DatabaseError error) {
					// TODO Auto-generated method stub
					
				}
            
            });

//            
//            
//            
//            if(body2.compareTo("hello") == 0) {
//            	message = "hello alexisno";
//            }
//            else {
//            	message = "bye alexisno";
//            }
            Body body = new Body.Builder(message).build();
            Message sms = new Message.Builder().body(body).build();
            MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
            return twiml.toXml();
        });


        
    }
    
    
}
