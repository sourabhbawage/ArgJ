public class ArgJTest {
	public static void main(String[] args) {

		ArgJOptionSet optionSet = new ArgJOptionSet();
		optionSet.add(new ArgJOption("f", "file").hasArgument(true));
		optionSet.add(new ArgJOption("l", "log").hasArgument(true));
		optionSet.add(new ArgJOption("r", "rate").hasArgument(true));
		optionSet = ArgJCommandLine.parse(optionSet, args);

		for (ArgJOption currentOption : optionSet.getAllOptions()) {
			if (currentOption.getLongOption().equals("log")) {
				System.out.println("Log file: " + currentOption.getArgValue());
			} else if (currentOption.getLongOption().equals("file")) {
				System.out.println("File name: " + currentOption.getArgValue());
			} else if (currentOption.getLongOption().equals("rate")) {
				//double rate = (Double) currentOption.getArgValueAsType();
				//System.out.println("Rate: " + rate);
				System.out.println("Int: "+currentOption.getArgValueAsInt());
				System.out.println("Double: "+currentOption.getArgValueAsDouble());
				System.out.println("Long: "+currentOption.getArgValueAsLong());
				System.out.println("Flaot: "+currentOption.getArgValueAsFloat());
			}
		}
	}
}
