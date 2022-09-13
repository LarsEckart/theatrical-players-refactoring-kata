package kata;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private final Map<String, Play> plays;

    public StatementPrinter(Map<String, Play> plays) {
        this.plays = plays;
    }

    public String print(Invoice invoice) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        for (var perf : invoice.performances) {

            volumeCredits = volumeCredits + volumeCreditsFor(perf);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", playFor(perf).name, usd(amountFor(perf)), perf.audience);
            totalAmount += amountFor(perf);
        }
        result += String.format("Amount owed is %s\n", usd(totalAmount));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private String usd(int number) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(number / 100);
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int result = Math.max(aPerformance.audience - 30, 0);
        if ("comedy".equals(playFor(aPerformance).type)) {
            result += Math.floor(aPerformance.audience / 5);
        }
        return result;
    }

    private Play playFor(Performance perf) {
        return this.plays.get(perf.playID);
    }

    private int amountFor(Performance aPerformance) {
        var result = 0;

        switch (playFor(aPerformance).type) {
            case "tragedy":
                result = 40000;
                if (aPerformance.audience > 30) {
                    result += 1000 * (aPerformance.audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.audience > 20) {
                    result += 10000 + 500 * (aPerformance.audience - 20);
                }
                result += 300 * aPerformance.audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

}
