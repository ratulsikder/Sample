package shaon.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class First
 */
@WebServlet("/First")
public class First extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public First() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String una = request.getParameter("u_name");
		String pwd = request.getParameter("p_word");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "servletdatabase",
					"12345");

			PreparedStatement ps = con
					.prepareStatement("select * from info  where STU_USERNAME=? and STU_PASSWORD=?");

			ps.setString(1, una);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			// if (un.equals("shaon") && pw1.equals("12345"))
			if (rs.next()) {
				pw.println("<h1>Succesful</h1>");	
						RequestDispatcher dr = request.getRequestDispatcher("/success");
				dr.forward(request, response);
			}

			else {
				pw.println("<h1>Login Failed</h1>");
				RequestDispatcher dr = request.getRequestDispatcher("/homepage.html");
				dr.include(request, response);

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
