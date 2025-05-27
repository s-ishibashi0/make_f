package dao;

import java.sql.Connection;

import javax.sql.DataSource;

public class DAO {
	// 🔹 データソース（DataSource）を管理する static 変数
	// → 一度取得したデータソースを再利用することで、効率的に接続を管理
	static DataSource ds;

	/**
	 * 🔗 データベース接続を取得するメソッド
	 *
	 * 初回の呼び出し時に `DataSource` を取得し、それを使ってデータベース接続を返します。 2回目以降は、すでに取得済みの
	 * `DataSource` を利用して接続を確立します。
	 *
	 * @return データベースの `Connection` オブジェクト
	 * @throws Exception
	 *             データソースの取得や接続確立時にエラーが発生した場合
	 */
	public Connection getConnection() throws Exception {

		// ④ `DataSource` を利用して `Connection` を取得し、呼び出し元に返す
		return ds.getConnection();
	}
}