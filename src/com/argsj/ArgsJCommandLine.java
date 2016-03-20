package com.argsj;

import com.argsj.exception.ArgumentRequiredException;

public class ArgsJCommandLine {

	/**
	 * Parse the argument values and the constraints applied to the options
	 * 
	 * @param options
	 * @param args
	 * @return
	 */
	public static ArgsJOptionSet parse(ArgsJOptionSet options, String args[]) {
		for (int i = 0; i < args.length; i++) {
			String argumentOption = args[i];
			if (argumentOption.startsWith("-") || argumentOption.startsWith("--")) {
				ArgsJOption opt = options.getOption(argumentOption);
				if (opt != null) {
					if (opt.hasArgument()) {
						try {
							if (!(args[i + 1].startsWith("--") || args[i + 1].startsWith("-"))) {
								opt.setArgValue(args[i + 1]);
							} else {
								throw new ArgumentRequiredException("Argument required for: " + argumentOption);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							throw new ArgumentRequiredException("Argument required for: " + argumentOption);
						}
					}
					if (opt.getType() != null) {
						opt.checkArgValueAsType();
					}
				}
			}
		}
		return options;
	}
}
