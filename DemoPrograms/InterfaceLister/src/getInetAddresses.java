import java.net.*;
import java.util.*;
public class getInetAddresses {

	public static void main(String[] args) {
		try {
			NetworkInterface ni = NetworkInterface.getByName("lo0");
			if (ni == null)
			{
				System.out.println("No such interface");
			}
			else
			{
				System.out.println(ni);
				Enumeration addresses = ni.getInetAddresses();
				while(addresses.hasMoreElements())
				{
					System.out.println(addresses.nextElement());
					
				}
			}
		} catch (SocketException e) {
			// TODO: handle exception
			System.out.println("Couldnt list sockets");
		}
	}
}
