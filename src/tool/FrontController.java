package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontController!");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            // ① リクエストされたURLのパスを取得
            String path = request.getServletPath(); // 例: "/LoginExecute.action"
            System.out.println("リクエストパス : " + path);

            // ② 拡張子とスラッシュを除去し、アクションクラス名を構築
            String className = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf(".action"));
            // 例: "LoginExecute"

            // ③ 複数のパッケージ候補を順に試す
            String[] basePackages = {
                "scoremanager.main.",
                "scoremanager."
            };

            Class<?> clazz = null;
            for (String base : basePackages) {
                try {
                    clazz = Class.forName(base + className + "Action");
                    break; // 成功したらループを抜ける
                } catch (ClassNotFoundException e) {
                    // 無視して次の候補へ
                }
            }

            if (clazz == null) {
                throw new ClassNotFoundException("アクションクラスが見つかりません: " + className + "Action");
            }

            // ④ クラスをインスタンス化して execute を呼び出す
            Action action = (Action) clazz.getDeclaredConstructor().newInstance();
            String url = action.execute(request, response);

            // ⑤ 指定されたURLへフォワード
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}