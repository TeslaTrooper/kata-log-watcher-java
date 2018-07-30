import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ferdinand.Szekeresch on 10.07.2017.
 */
public class Util {
	
	private List<Subscriber> subscribers = new ArrayList<>();
	private StringBuilder stackTrace = new StringBuilder();
	
	public Util() {
		subscribers.add(new Subscriber("Robert Glaser", "An error occured", "ExceptionCode 42"));
		subscribers.add(new Subscriber("Britta Glatt", "An error occured"));
		subscribers.add(new Subscriber("Michael Grün", "An error occured"));
		subscribers.add(new Subscriber("Fritz Schnitzel", "An error occured"));
		subscribers.add(new Subscriber("Antonio Materazzo", "An error occured"));
	}
	
	public void notify(Optional<String> line) {
		stackTrace.append(line.orElse(""));
		String logMessage = line.get();
		for (Subscriber subscriber : subscribers) {
            if(subscriber.isLineRelevant(logMessage)) {
            	String name = subscriber.getName();
            	name = name.toLowerCase();
            	name.replace("ü", "ue");
            	name.replace("ä", "ae");
            	name.replace("ö", "oe");
            	name.replace(" ", ".");
            	name = name + "@cas.de";
            	
            	if(subscriber.isStackTraceRelevant(logMessage)) {
            		logMessage += "; StackTrace:"+stackTrace.toString();
            		stackTrace = new StringBuilder();
            	}
            	writeEmail(name, logMessage);
            }
		}
	}
	
    private void writeEmail(final String address, final String msg) {
        System.out.println("Notifying " + address + ": " + msg);
    }
}
