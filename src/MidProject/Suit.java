package MidProject;

public enum Suit {
    HEARTS("Hearts", "\u2665"),
    CLUBS("Clubs", "\u2663"),
    DIAMONDS("Diamonds", "\u2666"),
    SPADES("Spades", "\u2660"),
    NONE("None", "\uD83C\uDCCF");

    private final String name;
    private final String symbol;

    Suit(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.symbol;
    }
}
