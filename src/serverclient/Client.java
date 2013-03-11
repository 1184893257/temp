package serverclient;

import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) {
		try {
			Socket socket=new Socket("222.25.189.65",2000);
			PrintWriter out=new PrintWriter(socket.getOutputStream());
			BufferedReader sin=new BufferedReader(
					new InputStreamReader(System.in));
			BufferedReader in=new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String s;
			do{
				s=sin.readLine();
				out.println(s);
				out.flush();
				System.out.println(in.readLine());
			}while(!(s.equals("bye")));
			System.out.println("closing");
			sin.close();
			out.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ConnectException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
