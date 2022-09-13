package kata;

import java.util.List;

record StatementData(String customer, List<MyPerformance> performances) {

    public int totalAmount() {
        return this.performances().stream().mapToInt(MyPerformance::amount).sum();
    }

    public int totalVolumeCredits() {
        return this.performances().stream().mapToInt(MyPerformance::volumeCredits).sum();
    }
}
