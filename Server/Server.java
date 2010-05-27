import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Server {

	public static ArrayList<Transporter> transporters = new ArrayList<Transporter>();;

	public static void main(String[] args) throws Exception {
		connectTransporters();
	}
	
	public static void connectTransporters() throws Exception {
		NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		
		NXTInfo[] infos = new NXTInfo[3];
		infos[0] = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_05", "00:16:53:02:A5:1F");
		infos[1] = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT_07", "00:16:53:04:ED:8C");
		infos[2] = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT_34", "00:16:53:05:94:8F");
		
		for (NXTInfo info:infos) {
			try {
				nxtComm.open(info);

				InputStream is = nxtComm.getInputStream();
				OutputStream os = nxtComm.getOutputStream();

				DataInputStream in = new DataInputStream(is);
				DataOutputStream out = new DataOutputStream(os);


				Transporter t = new Transporter(info.name, in, out);
				Server.transporters.add(t);
			}
			catch (Exception e){
	
			}
		}
	}
	
	
	

}
