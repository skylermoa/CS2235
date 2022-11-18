package MidProject;

public record Card(Rank rank, Suit suit) {

    public boolean isGreaterThan(Card a) {
        return this.rank.getValue() > a.rank.getValue();
    }

    public boolean isGreaterThan(Card a, Card b) {
        return this.rank.getValue() > a.rank.getValue()
                && this.rank.getValue() > b.rank.getValue();
    }

    public boolean isEqualTo(Card a) {
        return this.rank.getValue() == a.rank.getValue();
    }

    public boolean isEqualTo(Card a, Card b) {
        return this.rank.getValue() == a.rank.getValue()
                && this.rank.getValue() == b.rank.getValue();
    }

    public String toString() {
        return String.format("%s%s", rank, suit); // Show Card's Rank
    }
}