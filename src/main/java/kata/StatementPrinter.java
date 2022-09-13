package kata;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private final StatementCalculator statementCalculator;

    public StatementPrinter(Map<String, Play> plays) {
        this.statementCalculator = new StatementCalculator(plays);
    }

    public String print(Invoice invoice) {
        return renderPlainText(statementCalculator.createStatementData(invoice));
    }

    private String renderPlainText(StatementData data) {
        var result = String.format("Statement for %s\n", data.customer());

        for (var perf : data.performances()) {
            result += String.format("  %s: %s (%s seats)\n", perf.play().name, usd(perf.amount()), perf.audience());
        }
        result += String.format("Amount owed is %s\n", usd(data.totalAmount()));
        result += String.format("You earned %s credits\n", data.totalVolumeCredits());
        return result;
    }

    private String usd(int number) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(number / 100);
    }
}
