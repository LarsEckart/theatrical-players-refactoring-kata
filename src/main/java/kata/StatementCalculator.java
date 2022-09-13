package kata;

import java.util.Map;

public class StatementCalculator {

    private final Map<String, Play> plays;

    public StatementCalculator(Map<String, Play> plays) {
        this.plays = plays;
    }

    public StatementData createStatementData(Invoice invoice) {
        return new StatementData(invoice.customer, invoice.performances.stream().map(this::enrichPerformance).toList());
    }

    private MyPerformance enrichPerformance(Performance performance) {
        return new MyPerformance(
                playFor(performance),
                performance.audience,
                amountFor(performance),
                volumeCreditsFor(performance));
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
}
