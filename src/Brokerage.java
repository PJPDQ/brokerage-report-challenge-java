import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Brokerage {
	private int txnid;
	private LocalDateTime dt;
	private int account;
	private String adviser;
	private float value;
	public Brokerage(String txnid, String timestamp, String account, String adviser, String value) {
		this.txnid = Integer.parseInt(txnid);
		this.dt = OffsetDateTime.parse(timestamp).toLocalDateTime();
		this.account = Integer.parseInt(account);
		this.adviser = adviser;
		this.value = Float.parseFloat(value);
	}
	
	public int getId() {
		return this.txnid;	
	}
	
	public LocalDateTime getDateTime() {
		return this.dt;
	}
	
	public int getAccount() {
		return this.account;
	}
	
	public String getAdviser() {
		return this.adviser;
	}
	
	public float getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.txnid) + " " + this.dt.toString() + " " + Integer.toString(this.account) + " " + this.adviser + " " + Float.toString(this.value);
	}
}
