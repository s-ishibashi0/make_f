package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DataChecker {
    // H2データベースをTCPモードで接続
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost:9092/~/team";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("=== データベースのテーブル一覧 ===");
            checkDatabase(stmt);

            System.out.println();

            // 各テーブルのデータを確認（必要に応じて追加）
            checkTable(stmt, "SCHOOL");
            checkTable(stmt, "CLASS_NUM");
            checkTable(stmt, "STUDENT");
            checkTable(stmt, "SUBJECT");
            checkTable(stmt, "TEACHER");
            checkTable(stmt, "TEST");

        } catch (SQLException e) {
            System.err.println("データベース接続エラー:");
            e.printStackTrace();
        }
    }

    private static void checkTable(Statement stmt, String tableName) {
        System.out.println("=== " + tableName + " テーブルのデータ ===");
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " | ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("テーブル " + tableName + " の読み込みに失敗しました: " + e.getMessage());
        }
        System.out.println();
    }

    private static void checkDatabase(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SHOW TABLES");
        while (rs.next()) {
            System.out.println("テーブル: " + rs.getString(1));
        }
        rs.close();
    }
}
