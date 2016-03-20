import com.argsj.ArgsJCommandLine;
import com.argsj.ArgsJOption;
import com.argsj.ArgsJOptionSet;

public class ArgsJTest {
	public static void main(String[] args) {

		ArgsJOptionSet options = new ArgsJOptionSet();
		options.add(new ArgsJOption("f", "file").hasArgument(true));
		options.add(new ArgsJOption("l", "log").hasArgument(true));
		options.add(new ArgsJOption("r", "rate").hasArgument(true).ofType(Double.class));
		options = ArgsJCommandLine.parse(options, args);

		for (ArgsJOption currentOption : options.getAllOptions()) {
			if (currentOption.getLongOption().equals("log")) {
				System.out.println("Log file: " + currentOption.getArgValue());
				
			} else if (currentOption.getLongOption().equals("file")) {
				System.out.println("File name: " + currentOption.getArgValue());
			} else if (currentOption.getLongOption().equals("rate")) {
				double rate = (Double) currentOption.getArgValueAsType();
				System.out.println("Rate: " + rate);
				System.out.println(currentOption.getArgValueAsType());
			}
		}
	}
}
