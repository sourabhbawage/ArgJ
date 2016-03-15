import java.util.HashSet;
import java.util.Set;

class ArgJOptionSet {

	private Set<String> shortOptionsSet = new HashSet<String>();

	private Set<String> longOptionsSet = new HashSet<String>();

	private Set<ArgJOption> allOptions = new HashSet<ArgJOption>();

	public void add(ArgJOption option) {
		if (!shortOptionsSet.add(option.getShortOption())) {
			throw new OptionException(option.getShortOption() + " option already added");
		}
		if (!longOptionsSet.add(option.getLongOption())) {
			throw new OptionException(option.getLongOption() + " option already added");
		}

		allOptions.add(option);
	}

	public void remove(ArgJOption option) {
		shortOptionsSet.remove(option.getShortOption());
		longOptionsSet.remove(option.getLongOption());
		allOptions.remove(option);
	}

	public boolean has(String option) {
		option = removeHyphens(option);
		if (shortOptionsSet.contains(option) || longOptionsSet.contains(option)) {
			return true;
		} else {
			return false;
		}
	}

	private String removeHyphens(String option) {

		if (option.startsWith("--")) {
			return option.substring(2, option.length());
		}
		if (option.startsWith("-")) {
			return option.substring(1, option.length());
		}
		return option;

	}

	public ArgJOption getOption(String option) {
		if (option.startsWith("--")) {
			return getWithLong(option);
		} else if (option.startsWith("-")) {
			return getWithShort(option);
		}
		return null;
	}

	public Set<String> getShortOptionsSet() {
		return shortOptionsSet;
	}

	public Set<String> getLongOptionsSet() {
		return longOptionsSet;
	}

	public Set<ArgJOption> getAllOptions() {
		return allOptions;
	}

	public void setAllOptions(Set<ArgJOption> allOptions) {
		this.allOptions = allOptions;
	}

	public ArgJOption getWithLong(String optionString) {
		optionString = removeHyphens(optionString);
		for (ArgJOption option : allOptions) {
			if (option.getLongOption().equals(optionString)) {
				return option;
			}
		}
		throw new OptionException("Undefined option: --" + optionString);
	}

	public ArgJOption getWithShort(String optionString) {
		optionString = removeHyphens(optionString);
		for (ArgJOption option : allOptions) {
			if (option.getShortOption().equals(optionString)) {
				return option;
			}
		}
		throw new OptionException("Undefined option: -" + optionString);
	}
}