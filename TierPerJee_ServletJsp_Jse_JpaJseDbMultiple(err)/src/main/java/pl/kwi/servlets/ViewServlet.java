package pl.kwi.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@WebServlet("/view.do")
public class ViewServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private UserService userService;
	

	public ViewServlet() {
		userService = new UserService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		try {
			
			String submit = request.getParameter("submit");
			
			if("Display".equals(submit)){
				displayPage(request, response);
				return;
			}else if("Back".equals(submit)){
				handleBackButton(request, response);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.do?submit=Display&errorMessage=" + e.getMessage());
			return;
		}	
		
	}
	
	private void displayPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		try {
			
			String id = request.getParameter("id");
			UserEntity entity = null;
			entity = userService.readUser(Long.valueOf(id));
			
			request.setAttribute("userName", entity.getNameEntity().getName());
			request.setAttribute("userSurname", entity.getSurnameEntity().getSurname());
			request.setAttribute("id", entity.getId());
			
		} catch (Exception e) {
			throw e;
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/ViewJsp.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleBackButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("table.do?submit=Display");
		
	}

}
