package net.engineeringdigest.journalApp.enums;

public enum Sentiment {
    Happy("Happy"),
    Sad("Sad"),
    Angry("Angry");

    private final String value;
    Sentiment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
