import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Server {

	public static ArrayList<Transporter> transporters = new ArrayList<Transporter>();;

	public static void main(String[] args) throws Exception {
		String command = "";
		while(!command.equals("exit")) {
			connectTransporters();
			
			for (Transporter t: Server.transporters) {
				System.out.print(t.name + " ");
			}
			System.out.println();
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Name: ");
			String name = sc.next();
			System.out.println("Command: ");
			command = sc.next();
			
			for (Transporter t: Server.transporters) {
				if (t.name.equals(name)) {
					//t.sendCommand(command.toCharArray()[0]);
					t.cmdq.add(0,command.toCharArray()[0]);
				}
			}
		}
	}
	
	public static void connectTransporters() throws Exception {
		NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		
		NXTInfo[] infos = new NXTInfo[3];
		infos[0] = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_05", "00:16:53:02:A5:1F");
		infos[1] = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT_07", "00:16:53:04:ED:8C");
		infos[2] = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT_34", "00:16:53:05:94:8F");
		
		for (NXTInfo info:infos) {
			if (!isConnected(info.name)) {
				try {
					nxtComm.open(info);

					InputStream is = nxtComm.getInputStream();
					OutputStream os = nxtComm.getOutputStream();

					DataInputStream in = new DataInputStream(is);
					DataOutputStream out = new DataOutputStream(os);


					Transporter t = new Transporter(info.name, in, out);
					Server.transporters.add(t);
					System.out.println("Verbindung zu " + info.name + " aufgebaut."); 
				}
				catch (Exception e){
	
				}
			}
		}
	}
	
	public static boolean isConnected(String name) {
		for (Transporter t: Server.transporters) {
			if (t.name.equals(name)) {
				return true;
			}
		}
		return false;
	}
}
