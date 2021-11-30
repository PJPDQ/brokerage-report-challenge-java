import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Summary {
	private ArrayList<Brokerage> brokers = new ArrayList<Brokerage>();
	private HashMap<String, ArrayList<Brokerage>> summary = new HashMap<String, ArrayList<Brokerage>>();
	
	public Summary(ArrayList<Brokerage> reports) {
		this.brokers = reports;
		this.summary = this.getAllSummary();
	}
	
	private ArrayList<String> getAllAdviser() {
		HashSet<String> advisers = new HashSet<String>();
		for (Brokerage report: this.brokers) {
			advisers.add(report.getAdviser());
		}
		return new ArrayList<>(advisers);
	}
	
	private HashMap<String, ArrayList<Brokerage>> getAllSummary() {
		HashMap<String, ArrayList<Brokerage>> summary = new HashMap<String, ArrayList<Brokerage>>();
		for (String adviser: this.getAllAdviser()) {
			for (int j = 0; j < this.brokers.size(); j++) {
				if (adviser == this.brokers.get(j).getAdviser()) {
					if (!summary.containsKey(adviser)) {
						ArrayList<Brokerage> ads = new ArrayList<Brokerage>();
						ads.add(this.brokers.get(j));
						summary.put(adviser, ads);
					} else {
						summary.get(adviser).add(this.brokers.get(j));		
					}
				}
			}
		}
		return summary;
	}
	
	private float getSumValue(String ad) {
		float sum = 0;
		for (Brokerage report: this.brokers) {
			if (report.getAdviser().equalsIgnoreCase(ad)) {
				sum += report.getValue();
			}
		}
		return sum;
	}
	
	private LocalDateTime earliestDateAdviser(String adviser) {
		LocalDateTime earliest = LocalDateTime.MAX;
		for (Brokerage b: this.brokers) {
			if ( b.getAdviser().equalsIgnoreCase(adviser) && 
					b.getDateTime().isBefore(earliest) ) {
				earliest = b.getDateTime();
			}
		}
		return earliest;
	}
	
	private LocalDateTime latestDateAdviser(String adviser) {
		LocalDateTime latest = LocalDateTime.MIN;
		
		for (Brokerage b: this.brokers) {
			if ( b.getAdviser().equalsIgnoreCase(adviser) && 
					b.getDateTime().isAfter(latest) ) {
				latest = b.getDateTime();
			}
		}
		return latest;
	}
	
	private String pad(String s, int n) {
		return String.format("%" + n + "s", s);
	}
	
	public void summaryReport() {
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd MMM yyyy");
		for (Entry<String, ArrayList<Brokerage>> entry: this.summary.entrySet()) {
			String key = entry.getKey();
			float sum = this.getSumValue(key);
			LocalDateTime early = this.earliestDateAdviser(key);
			LocalDateTime late = this.latestDateAdviser(key);
			String earliest = early.format(format1);
			String latest = late.format(format1);
			String strSum = String.format("%,.2f", sum);
			String pad = this.pad(strSum, 10);
			System.out.printf("%s\n", key);
			System.out.printf("%s\n", pad);
			System.out.printf("%s -> %s\n", earliest, latest);
			System.out.println("\n");
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String> readFile = new ArrayList<String>();
		String file = ".\\testcase\\testcases.csv";
		ReadFile rf = new ReadFile();
		readFile = rf.read(file);

		ArrayList<Brokerage> reports = new ArrayList<Brokerage>();
		for (int i = 1; i < readFile.size(); i++) {
			String[] arrOfStr = readFile.get(i).split(",");
			Brokerage report = new Brokerage(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4]);
			reports.add(report);
		}
		
		Summary sum = new Summary(reports);
		
		sum.summaryReport();
		
	}

}
