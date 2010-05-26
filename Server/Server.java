import lejos.pc.comm.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws Exception {
		NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_05", "00:16:53:02:A5:1F");
		nxtComm.open(info);
		
		InputStream is = nxtComm.getInputStream();
		OutputStream os = nxtComm.getOutputStream();
		
		DataInputStream in = new DataInputStream(is);
		DataOutputStream out = new DataOutputStream(os);
		
		out.writeChars("test.");
		out.flush();
		out.close();
	}

}
