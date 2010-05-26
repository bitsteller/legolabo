import lejos.pc.comm.*;

public class Server {
	public static void main(String[] args) throws Exception {
		NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		NXTInfo info = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_05", "00:16:53:02:A5:1F");
		nxtComm.open(info);
		
	}

}
