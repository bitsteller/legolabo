import java.util.*;

public class Good {
	public enum Goods {KOHLE,STAHL,HOLZ,EISEN,WAREN}
	
	public Good.Goods type;
	public int amount;
	
	public Good(Good.Goods type, int amount) {
		this.type = type;
		this.amount = amount;
	}
}
