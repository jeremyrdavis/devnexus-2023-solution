package io.arrogantprogrammer;

public record GreetingRecord(Long id, String text) {

    public GreetingRecord(String text) {
        this(null, text);
    }
}
