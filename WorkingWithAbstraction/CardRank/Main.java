package CardRank;


public class Main {
    public static void main(String[] args) {
        CardRank[] cardRank = CardRank.values();
        System.out.println("Card Ranks:");
        for (CardRank rank : cardRank) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s"
                    ,rank.ordinal(), rank.name()));

        }

        }

    }


