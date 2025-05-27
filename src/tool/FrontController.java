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

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("FrontController!");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            // ① リクエストされたURLのパスを取得
            String path = request.getServletPath(); // e.g., "/LoginExecute.action"
            System.out.println("リクエストパス : " + path);

            // ② 拡張子とスラッシュを除去し、アクションクラス名を構築
            String className = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf(".action"));
            // e.g., "LoginExecute"

            String fullClassName = "scoremanager." + className + "Action";
            System.out.println("アクションクラス名 : " + fullClassName);

            // ③ クラスを動的にロードしてインスタンス生成
            Action action = (Action) Class.forName(fullClassName).getDeclaredConstructor().newInstance();

            // ④ アクションクラスの execute() を呼び出してURL取得
            String url = action.execute(request, response);

            // ⑤ 指定されたURLへフォワード
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
