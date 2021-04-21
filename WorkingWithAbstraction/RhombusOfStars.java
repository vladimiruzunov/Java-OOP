import java.util.Scanner;

public class RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        printTop(n);
        printBottom(n);
    }

    private static void printTop(int n) {
        for (int r = 1; r <= n; r++) {
            for (int i = 0; i < n -r; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < r; i++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    private static void printBottom(int n) {
        for (int r = 1; r <= n; r++) {
            for (int i = 0; i < r; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < n - r; i++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}