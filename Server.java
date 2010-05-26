import lejos.pc.comm.*;

public class Server {
	public static void main(String[] args) throws Exception {
		NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		//NXTInfo[] nxtInfo = nxtComm.search("IPS_05",NXTCommFactory.BLUETOOTH);
		
		/*for (NXTInfo info:nxtInfo) {
			System.out.println(info.name + ": " + info.deviceAddress);
		}*/
		
		NXTInfo nxtInfo = new NXTInfo(NXTCommFactory.BLUETOOTH, "IPS_05", "00:16:53:02:A5:1F");
		
	}

}
