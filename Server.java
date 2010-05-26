import lejos.pc.comm.*;

public class Server {
	public static void main(String[] args) throws Exception {
		NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		NXTInfo[] nxtInfo = nxtComm.search("NXT",NXTCommFactory.BLUETOOTH);
		
		for (NXTInfo info:nxtInfo) {
			System.out.println(info.name + ": " + info.deviceAddress);
		} 
		
	}

}
