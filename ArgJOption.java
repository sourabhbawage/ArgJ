class ArgJOption {

	private String shortOption;
	private String longOption;
	private String argValue;
	private Class<? extends Number> type;
	private boolean hasArgument;
	private boolean isProvided;

	public String getArgValue() {
		return argValue;
	}

	public void setArgValue(String argValue) {
		this.argValue = argValue;
	}

	public boolean hasArgument() {
		return hasArgument;
	}

	public ArgJOption hasArgument(boolean hasArgument) {
		this.hasArgument = hasArgument;
		return this;
	}

	public boolean isProvided() {
		return isProvided;
	}

	public void setProvided(boolean isProvided) {
		this.isProvided = isProvided;
	}

	public Class<? extends Number> getType() {
		return type;
	}

	public ArgJOption ofType(Class<? extends Number> type) {
		this.type = type;
		return this;
	}

	public void checkArgValueAsType() {
		getArgValueAsType();
	}

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

	public ArgJOption(String shortOption, String longOption) {
		this.shortOption = shortOption;
		this.longOption = longOption;
	}

	public String getShortOption() {
		return shortOption;
	}

	public void setShortOption(String shortOption) {
		this.shortOption = shortOption;
	}

	public String getLongOption() {
		return longOption;
	}

	public void setLongOption(String longOption) {
		this.longOption = longOption;
	}

	@Override
	public String toString() {
		return "short option: " + shortOption + ", long option: " + longOption + ", has argument: " + hasArgument
				+ ", argument value: " + argValue;
	}
}