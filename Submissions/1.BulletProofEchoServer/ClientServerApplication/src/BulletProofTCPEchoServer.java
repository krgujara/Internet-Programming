import java.net.*;  // for Socket, ServerSocket, and InetAddress
import java.util.Arrays;
import java.io.*;   // for IOException and Input/OutputStream

public class BulletProofTCPEchoServer {

	  public static void main(String[] args) throws IOException {

	    if (args.length != 1)  // Test for correct # of args
	      throw new IllegalArgumentException("Parameter(s): <Port>");

	    System.out.println("Server");
	    int servPort = Integer.parseInt(args[0]);

	    // Create a server socket to accept client connection requests
	    ServerSocket servSock = new ServerSocket(servPort);
	  
	    while (true) { // Run forever, accepting and servicing connections
	      Socket clntSock = servSock.accept();     // Get client connection
	      Thread t = new ClientHandler(clntSock);  // to allow multiple clients connect to the server at the same time
	      t.start();
	    }
	    /* NOT REACHED */
	  }
}
class ClientHandler extends Thread 
{
	 InputStream dis;
	 OutputStream dos;
	 final Socket clntSock;
	// Constructor
    public ClientHandler(Socket s) 
    {
        this.clntSock = s;
		try {
			this.dis = clntSock.getInputStream();
			this.dos = clntSock.getOutputStream();

		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    @Override
    public void run() 
    {
   
	    int recvMsgSize;   // Size of received message
	    byte[] receiveBuf = new byte[128];  // Receive buffer, made to accept 64 bytes of data at a time

	      System.out.println("Client : " +clntSock.getPort());
	      SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
	      System.out.println("Handling client at " + clientAddress);
	      
	   try {
		   String prevString = "", newString = "";
		   int count  = 0;
		   int maxCount = 5;
	      // Receive until client closes connection, indicated by -1 return
	      while ((recvMsgSize = dis.read(receiveBuf)) != -1)
	      {
	    	  	newString = new String(receiveBuf);
	    	  	System.out.println("Received : " + newString);
	    	  	if (prevString.equals(newString))
	    	  	{
	    	  		System.out.println("Increasing the count");
	    	  		count++;
	    	  		if (count >= maxCount)
	    	  		{
	    	  			// client has tried to send the same string more than 5 times
	    	  			System.out.println("Duplicate Data");
		    	  		clntSock.close();
	    	  		}
	    	  	}
	    		prevString = newString;
	    	  	
	    	  	if (recvMsgSize >= 64)  // if the packet exceeds 64 Bytes, just send first 64 Bytes and close this bad client
	    	  	{
	    	  		dos.write(receiveBuf, 0, 64);
	    	  		System.out.println("Max Packet Size Exceeded");
	    	  		clntSock.close();
	    	  	}
	    	  	else
	    	  	{
	    	  		dos.write(receiveBuf, 0, recvMsgSize);	   // if the packet is within (64 Bytes, then echo it) 	   
	    	  	}
	      }
	      
	   }
	   catch(InterruptedIOException e)
	   {
		   System.err.println("Remote Client Timed out");	
	   }
	   catch (IOException e) 
	   {
			e.printStackTrace();
	   }		
	   finally
	   {
		    try {
				clntSock.close();
			
			  // Close the socket.  We are done with this client!  
				dos.close();
		      	dis.close();
		    } catch (IOException e) {
				e.printStackTrace();
			}
	   }
	   
    }
}

