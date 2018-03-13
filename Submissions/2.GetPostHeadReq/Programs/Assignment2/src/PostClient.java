import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class PostClient{

  public static void main(String[] args) throws IOException {
	System.out.println("Post Client");  
    if ((args.length < 1) || (args.length >4))  // Test for correct # of args
    {
    	
      throw new IllegalArgumentException("Parameter(s): <Server> <UName> <Pwd>[<Port>]");
    }
    
    String server = args[0];       // Server name or IP address
    String username = args[1];
    String password = args[2];
    
    int servPort = (args.length == 4) ? Integer.parseInt(args[3]) : 7;
    // Create socket that is connected to server on specified port
    Socket socket = new Socket(server, servPort);
    System.out.println("Connected to server...");
    Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
    String data = "username:"+username + "&password:"+password;
    out.write("POST /authorize.txt HTTP/1.1\r\n");
    out.write("Content-Length:" + data.length()+ "\r\n");
    out.write("Content-Type: text/plain\r\n");
    out.write("\r\n");
    out.write(data);
    out.write("\r\n");
    out.flush();
    
 
    
    // read the response
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
    // read the headers
    String s ;
    while(!(s = in.readLine()).equals(""))
    {
    		System.out.println(s);
    }
    System.out.println("\n");
    
    // read the message
    String s1 = null ;
    while((s1 =in.readLine())!=null)
    {
    		System.out.println(s1);
    }  
    
     socket.close();  // Close the socket and its streams
  }
}