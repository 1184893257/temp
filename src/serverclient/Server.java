package serverclient;

import java.io.*;
import java.net.*;

public class Server implements Runnable{
	private static final int MaxClients = 6;
	Socket sock;
	
	public Server(Socket sock){
		this.sock=sock;
	}
	
	public static void main(String[] args) {
		try {
			ServerSocket server=new ServerSocket(2000);
			int i;
			for(i=0;i<MaxClients;++i){
				Socket socket=server.accept();
				Thread t=new Thread(new Server(socket));
				t.start();
			}
			server.close();
		}catch(BindException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void run() {
		try {
			BufferedReader in=new BufferedReader(
				new InputStreamReader(sock.getInputStream()));
			PrintWriter out=new PrintWriter(sock.getOutputStream());
			BufferedReader sin=new BufferedReader(
					new InputStreamReader(System.in));
			String s;
			while(!(s=in.readLine()).equals("bye")){
				System.out.println(s);
				out.println(sin.readLine());
				out.flush();
			}
			System.out.println("closing");
			out.close();
			sin.close();
			in.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
