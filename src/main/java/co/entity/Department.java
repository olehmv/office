package co.entity;

public enum Department {
	CREDIT("credite"),IT("it"),HR("hr");
	
	
	 private final String value;

	Department(final String type) {
        value = type;
    }

    @Override
    public String toString() {
        return value;
    }

}
