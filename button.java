import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buttonServlet")
public class ServletExample extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonId = request.getParameter("buttonId");
        int result = Integer.parseInt(buttonId) * 100;
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(result));
    }
}
