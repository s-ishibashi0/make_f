package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/*" })
public class EncodingFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		///////////////////////////////////////////////////
		// 【前処理】 サーブレットやJSPの呼び出し前に行う処理を書きます //
		///////////////////////////////////////////////////
		// ① リクエストの文字エンコーディングを UTF-8 に設定
		request.setCharacterEncoding("UTF-8");
		// ② レスポンスのコンテンツタイプを UTF-8 に設定
		response.setContentType("text/html; charset=UTF-8");
		// ③ フィルタの前処理（ログ出力）
		System.out.println("フィルタの前処理");

		///////////////////////////////////////////////////
		// 【本処理】次のフィルタまたはサーブレットの実行 //
		///////////////////////////////////////////////////
		// ④ 次のフィルタまたはサーブレットを実行
		chain.doFilter(request, response);

		///////////////////////////////////////////////////
		// 【後処理】サーブレットやJSPの呼び出し後に行う処理を書きます //
		///////////////////////////////////////////////////
		// ⑤ フィルタの後処理（ログ出力）
		System.out.println("フィルタの後処理");
	}

	/**
	 * フィルタの初期化メソッド（今回は特に処理なし）。
	 *
	 * @param filterConfig
	 *            フィルタ設定情報
	 */
	@Override
	public void init(FilterConfig filterConfig) {
	}

	/**
	 * フィルタの破棄メソッド（今回は特に処理なし）。
	 */
	@Override
	public void destroy() {
	}
}
