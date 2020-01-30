package kata;

import java.util.List;
import java.util.Map;

class App {

    public static void main(String[] args) {
        List<Performance> performances = List.of(
                new Performance("hamlet", 10),
                new Performance("as-like", 15),
                new Performance("othello", 20));

        Map<String, Play> plays = Map.of(
                "hamlet", new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));

        Invoice bigCo = new Invoice("BigCo", performances);
        String statement = new StatementPrinter().print(bigCo, plays);
        System.out.println(statement);
    }
}
