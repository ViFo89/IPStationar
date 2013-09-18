import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Connection {
	
	 private Socket socket;
     private PrintWriter out;
     private BufferedReader in;
     
     private String host;
     private int port;
     
	
	public Connection(String host, int port){
		socket = null;
		out = null;
		in = null;
		this.host = host;
		this.port = port;
		
	}
	
	public boolean connect(){
		try {
            socket = new Socket(host, port);
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"ISO-8859-1" ), true);
            in = new BufferedReader(new InputStreamReader(
                                        socket.getInputStream()));
            
            out.println("Hej");
        } catch (UnknownHostException e) {
            System.err.println("Unknown host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO error");
            System.exit(1);
        }

		return true;
		
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
}
