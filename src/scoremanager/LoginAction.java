package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction implements Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ログイン画面へ遷移するだけ
        return "scoremanager/Login.jsp";
    }
}