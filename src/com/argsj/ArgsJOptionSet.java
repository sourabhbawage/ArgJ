package com.argsj;

import java.util.HashSet;
import java.util.Set;

public class ArgsJOptionSet {

	/**
	 * Set to hold all the short options.
	 */
	private Set<String> shortOptionsSet = new HashSet<String>();

	/**
	 * Set to hold all the long options.
	 */
	private Set<String> longOptionsSet = new HashSet<String>();

	/**
	 * Set of options.
	 */
	private Set<ArgsJOption> allOptions = new HashSet<ArgsJOption>();

	public void add(ArgsJOption option) {
		if (!shortOptionsSet.add(option.getShortOption())) {
			throw new RuntimeException(option.getShortOption() + " option already added");
		}
		if (!longOptionsSet.add(option.getLongOption())) {
			throw new RuntimeException(option.getLongOption() + " option already added");
		}

		allOptions.add(option);
	}

	/**
	 * To check if the option provided exists as a part of long option or short
	 * option.
	 * 
	 * @param option
	 * @return
	 */
	public boolean has(String option) {
		option = removeHyphens(option);
		if (shortOptionsSet.contains(option) || longOptionsSet.contains(option)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes the hyphens of the option.
	 * 
	 * @param option
	 * @return
	 */
	private String removeHyphens(String option) {

		if (option.startsWith("--")) {
			return option.substring(2, option.length());
		}
		if (option.startsWith("-")) {
			return option.substring(1, option.length());
		}
		return option;

	}

	/**
	 * Returns the ArgsJOption object based on the String option name (long or
	 * short)
	 * 
	 * @param option
	 * @return
	 */
	public ArgsJOption getOption(String option) {
		if (option.startsWith("--")) {
			return getWithLong(option);
		} else if (option.startsWith("-")) {
			return getWithShort(option);
		}
		return null;
	}

	/**
	 * Set of all the short options.
	 * 
	 * @return
	 */
	public Set<String> getShortOptionsSet() {
		return shortOptionsSet;
	}

	/**
	 * Set of all the long options.
	 * 
	 * @return
	 */
	public Set<String> getLongOptionsSet() {
		return longOptionsSet;
	}

	/**
	 * Set of all options
	 * 
	 * @return
	 */
	public Set<ArgsJOption> getAllOptions() {
		return allOptions;
	}

	/**
	 * Returns ArgsJOption based on String short option
	 * 
	 * @param optionString
	 * @return
	 */
	public ArgsJOption getWithLong(String optionString) {
		optionString = removeHyphens(optionString);
		for (ArgsJOption option : allOptions) {
			if (option.getLongOption().equals(optionString)) {
				return option;
			}
		}
		throw new OptionException("Undefined option: --" + optionString);
	}

	/**
	 * Returns ArgsJOption based on String long option
	 * 
	 * @param optionString
	 * @return
	 */
	public ArgsJOption getWithShort(String optionString) {
		optionString = removeHyphens(optionString);
		for (ArgsJOption option : allOptions) {
			if (option.getShortOption().equals(optionString)) {
				return option;
			}
		}
		throw new OptionException("Undefined option: -" + optionString);
	}
}