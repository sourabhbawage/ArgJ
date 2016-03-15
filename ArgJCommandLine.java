import java.util.Iterator;

public class ArgJCommandLine {

	public static ArgJOptionSet parse(ArgJOptionSet options, String args[]) {
		final String longOption = "--";
		final String shortOption = "-";
		for (int i = 0; i < args.length; i++) {
			String argumentOption = args[i];
			if (argumentOption.startsWith(longOption) || argumentOption.startsWith(shortOption)) {
				ArgJOption opt = options.getOption(argumentOption);
				opt.setProvided(true);
				if (opt != null) {
					if (opt.hasArgument()) {
						try {
							if (!(args[i + 1].startsWith(longOption) || args[i + 1].startsWith(shortOption))) {
								opt.setArgValue(args[i + 1]);
							} else {
								throw new RuntimeException("Argument required for: " + argumentOption);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							throw new RuntimeException("Argument required for: " + argumentOption);
						}
					}
					if (opt.getType() != null) {
						opt.checkArgValueAsType();
					}
				}
			}
		}
		return removeOptions(options);
	}

	private static ArgJOptionSet removeOptions(ArgJOptionSet options) {
		Iterator<ArgJOption> iterator = options.getAllOptions().iterator();
		while (iterator.hasNext()) {
			ArgJOption option = iterator.next();
			if (!option.isProvided()) {
				iterator.remove();
			}
		}
		return options;
	}
}
