package kata;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private final Map<String, Play> plays;

    public StatementPrinter(Map<String, Play> plays) {
        this.plays = plays;
    }

    public String print(Invoice invoice) {
        StatementData statementData = new StatementData(invoice.customer, invoice.performances);
        return renderPlainText(statementData);
    }

    record StatementData(String customer, List<Performance> performances) {

    }

    private String renderPlainText(StatementData data) {
        var result = String.format("Statement for %s\n", data.customer());

        for (var perf : data.performances()) {
            result += String.format("  %s: %s (%s seats)\n", playFor(perf).name, usd(amountFor(perf)), perf.audience);
        }
        result += String.format("Amount owed is %s\n", usd(totalAmount(data)));
        result += String.format("You earned %s credits\n", totalVolumeCredits(data));
        return result;
    }

    private int totalAmount(StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += amountFor(perf);
        }
        return result;
    }

    private int totalVolumeCredits(StatementData data) {
        var volumeCredits = 0;
        for (var perf : data.performances()) {
            volumeCredits = volumeCredits + volumeCreditsFor(perf);
        }
        return volumeCredits;
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
