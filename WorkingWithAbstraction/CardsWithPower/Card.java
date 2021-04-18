package CardsWithPower;

public class Card {
    private CardRank rank;
    private CardSuit suit;
    private int power;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        calculatePower();
    }

    private void calculatePower() {
        this.power = rank.getValue() + suit.getValue();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d",
                this.rank, this.suit,this.power );
    }
}
