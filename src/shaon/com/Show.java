package shaon.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();
		String st_name = request.getParameter("st_name");
		String f_name = request.getParameter("f_name");
		String m_name = request.getParameter("m_name");
		String dept = request.getParameter("dept");
		String id = request.getParameter("id");

		int ag = Integer.parseInt(request.getParameter("age"));
		String gen = request.getParameter("gen");
		String dob = request.getParameter("dob");
		String dis = request.getParameter("dis");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "servletdatabase", "12345");

			PreparedStatement ps = con
					.prepareStatement("insert into info values(?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, st_name);
			ps.setString(2, f_name);
			ps.setString(3, m_name);
			ps.setString(4, dept);
			ps.setString(5, id);
			ps.setInt(6, ag);
			ps.setString(7, gen);
			ps.setString(8, dob);
			ps.setString(9, dis);
			ps.setString(10, username);
			ps.setString(11, password);

			ps.execute();
			pw.println("<font color=red size=5>Registration Succesful</font> ");
			RequestDispatcher rd = request.getRequestDispatcher("/homepage.html");
			rd.include(request, response);
		}

		catch (Exception ex) {
			System.out.println(ex);
		}
		// pw.println("Student Name:      "+st_name);
		// pw.println("Fathers Name:      "+f_name);
		// pw.println("Mothers Name:      "+m_name);
		// pw.println("Department Name:   "+dept);
		// pw.println("ID No:             "+id);
		// pw.println("Age:               "+ag);
		// pw.println("Gender:            "+gen);
		// pw.println("Date of birth:     "+dob);
		// pw.println("District:          "+dis);

	}

}
