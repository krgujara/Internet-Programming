import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class HeadClient{

  public static void main(String[] args) throws IOException {
	 System.out.println("Head Client");
    if ((args.length < 1) || (args.length > 2))  // Test for correct # of args
    {
    	
      throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");
    }
    
    String server = args[0];       // Server name or IP address
       int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 7;
    // Create socket that is connected to server on specified port
    Socket socket = new Socket(server, servPort);
    System.out.println("Connected to server...");
    Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
    
    out.write("HEAD /file2.html HTTP/1.1\r\n");
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
    
    socket.close();  // Close the socket and its streams
  }
}