package com.argsj;

public class ArgsJOption {

	/**
	 * Short option
	 */
	private String shortOption;
	/**
	 * Long option
	 */
	private String longOption;
	/**
	 * Argument value passed on the command line
	 */
	private String argValue;
	/**
	 * Argument value to be of the defined type
	 */
	private Class<? extends Number> type;
	/**
	 * To determine if an option should have an argument
	 */
	private boolean hasArgument;

	/**
	 * Argument value of the option
	 * 
	 * @return
	 */
	public String getArgValue() {
		return argValue;
	}

	/**
	 * Set argument value
	 * 
	 * @param argValue
	 */
	void setArgValue(String argValue) {
		this.argValue = argValue;
	}

	/**
	 * Determine if argument is required
	 * 
	 * @return
	 */
	boolean hasArgument() {
		return hasArgument;
	}

	public ArgsJOption hasArgument(boolean hasArgument) {
		this.hasArgument = hasArgument;
		return this;
	}

	/**
	 * Argument value of the type (Class)
	 * 
	 * @return
	 */
	Class<? extends Number> getType() {
		return type;
	}

	/**
	 * Set the Class type of the argument value
	 * 
	 * @param type
	 * @return
	 */
	public ArgsJOption ofType(Class<? extends Number> type) {
		this.type = type;
		return this;
	}

	/**
	 * Check if argument value is of the mentioned type
	 */
	void checkArgValueAsType() {
		getArgValueAsType();
	}

	/**
	 * Parses the argument value to the required type
	 * 
	 * @return
	 */
	public Number getArgValueAsType() {
		if (type == null) {
			throw new OptionException("Argument type not declared for option: " + longOption);
		}
		String type = getType().toString();
		type = type.substring(type.lastIndexOf(".") + 1, type.length());
		Number number = null;
		try {
			switch (type) {

			case "Integer": {
				number = Integer.parseInt(getArgValue());
				break;
			}
			case "Double": {
				number = Double.parseDouble(getArgValue());
				break;
			}
			case "Long": {
				number = Long.parseLong(getArgValue());
				break;
			}
			case "Short": {
				number = Short.parseShort(getArgValue());
				break;
			}
			case "Byte": {
				number = Byte.parseByte(getArgValue());
				break;
			}
			case "Float": {
				number = Float.parseFloat(getArgValue());
				break;
			}
			default: {
				throw new OptionException("Not supported: " + getType().toString());
			}
			}
		} catch (NumberFormatException e) {
			throw new OptionException("Argument " + getArgValue() + " is not of type " + getType().toString());
		}
		return number;
	}

	/**
	 * Returns value as an int
	 * 
	 * @return
	 */
	public int getArgValueAsInt() {
		boolean internallySet = false;
		int intValue;
		if (getType() == null) {
			type = java.lang.Double.class;
			internallySet = true;
		}
		try {
			intValue = getArgValueAsType().intValue();
		} catch (RuntimeException e) {
			throw new OptionException("Cannot get value as int for option --" + getLongOption());
		}

		if (internallySet) {
			type = null;
		}
		return intValue;

	}

	/**
	 * Returns value as double
	 * 
	 * @return
	 */
	public double getArgValueAsDouble() {
		boolean internallySet = false;
		double doubleValue;
		if (getType() == null) {
			type = java.lang.Double.class;
			internallySet = true;
		}
		try {
			doubleValue = getArgValueAsType().doubleValue();
		} catch (RuntimeException e) {
			throw new OptionException("Cannot get value as double for option --" + getLongOption());
		}
		if (internallySet) {
			type = null;
		}
		return doubleValue;
	}

	/**
	 * Returns value as float
	 * 
	 * @return
	 */
	public float getArgValueAsFloat() {
		boolean internallySet = false;
		float floatValue;
		if (getType() == null) {
			type = java.lang.Double.class;
			internallySet = true;
		}
		try {
			floatValue = getArgValueAsType().floatValue();
		} catch (OptionException e) {
			throw new OptionException("Cannot get value as float for option --" + getLongOption());
		}
		if (internallySet) {
			type = null;
		}
		return floatValue;
	}

	/**
	 * Returns value as long
	 * 
	 * @return
	 */
	public long getArgValueAsLong() {
		boolean internallySet = false;
		long longValue;
		if (getType() == null) {
			type = java.lang.Double.class;
			internallySet = true;
		}
		try {
			longValue = getArgValueAsType().longValue();
		} catch (OptionException e) {
			throw new OptionException("Cannot get value as long for option --" + getLongOption());
		}
		if (internallySet) {
			type = null;
		}
		return longValue;
	}

	/**
	 * Constructor
	 * 
	 * @param shortOption
	 * @param longOption
	 */
	public ArgsJOption(String shortOption, String longOption) {
		this.shortOption = shortOption;
		this.longOption = longOption;
	}

	/**
	 * Short option
	 * 
	 * @return
	 */
	public String getShortOption() {
		return shortOption;
	}

	/**
	 * Long option
	 * 
	 * @return
	 */
	public String getLongOption() {
		return longOption;
	}

	@Override
	public String toString() {
		return "Short option: " + shortOption + ", Long option: " + longOption + ", Has argument: " + hasArgument
				+ ", Argument value: " + argValue + ", Type: " + type;
	}
}