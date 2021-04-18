package CardSuit;

public class Main {
    public static void main(String[] args) {

        CardRank[] cardSuits = CardRank.values();
        System.out.println("Card Suits:");
        for (CardRank cardSuit : cardSuits) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s"
                    ,cardSuit.ordinal(), cardSuit.name()));
        }
    }
}
