package kata;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class HtmlStatementPrinter {

    private final StatementCalculator statementCalculator;

    public HtmlStatementPrinter(Map<String, Play> plays) {
        this.statementCalculator = new StatementCalculator(plays);
    }

    public String print(Invoice invoice) {
        return renderHtml(statementCalculator.createStatementData(invoice));
    }

    private String renderHtml(StatementData data) {
        var result = String.format("<html><h1>Statement for %s</h1>", data.customer());

        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>";
        for (var perf : data.performances()) {
            result += "<tr><td>%s</td><td>%s</td>".formatted(perf.play().name, perf.audience());
            result += "<td>%s</td></tr>\n".formatted(usd(perf.amount()));
        }
        result += "</table>\n";
        result += "<p>Amount owed is <em>%s</em></p>\n".formatted(usd(data.totalAmount()));
        result += "<p>You earned <em>%s</em> credits</p>\n</html>".formatted(data.totalVolumeCredits());
        return result;
    }

    private String usd(int number) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(number / 100);
    }
}
