package kata;

import com.github.larseckart.tcr.TestCommitRevertMainExtension;
import org.approvaltests.Approvals;
import org.approvaltests.core.Options;
import org.approvaltests.reporters.Junit5Reporter;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(TestCommitRevertMainExtension.class)
class StatementPrinterTest {

    @Test
    void golden_master() {
        var performances = List.of(
                new Performance("hamlet", 55),
                new Performance("hamlet", 30),
                new Performance("hamlet", 29),
                new Performance("hamlet", 31),
                new Performance("hamlet", 15),
                new Performance("as-like", 35),
                new Performance("as-like", 12),
                new Performance("as-like", 20),
                new Performance("othello", 40));
        var plays = Map.of(
                "hamlet", new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));
        Invoice invoice = new Invoice("BigCo", performances);

        String statement = new StatementPrinter(plays).print(invoice);

        Approvals.verify(statement, new Options(new Junit5Reporter()));
    }

    @Test
    void golden_master_html() {
        var performances = List.of(
                new Performance("hamlet", 55),
                new Performance("hamlet", 30),
                new Performance("hamlet", 29),
                new Performance("hamlet", 31),
                new Performance("hamlet", 15),
                new Performance("as-like", 35),
                new Performance("as-like", 12),
                new Performance("as-like", 20),
                new Performance("othello", 40));
        var plays = Map.of(
                "hamlet", new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));
        Invoice invoice = new Invoice("BigCo", performances);

        String statement = new HtmlStatementPrinter(plays).print(invoice);

        Approvals.verifyXml(statement, new Options(new Junit5Reporter()));
    }
}
