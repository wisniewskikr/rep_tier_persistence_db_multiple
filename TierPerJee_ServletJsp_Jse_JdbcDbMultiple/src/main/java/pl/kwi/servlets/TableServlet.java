package pl.kwi.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@WebServlet("/table.do")
public class TableServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(TableServlet.class);
	private UserService userService;
	
	
	public TableServlet(){
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
			}else if("Create".equals(submit)){
				handleCreateButton(request, response);
				return;
			}else if("View".equals(submit)){
				handleViewButton(request, response);
				return;
			}else if("Edit".equals(submit)){
				handleEditButton(request, response);
				return;
			}else if("Delete".equals(submit)){
				handleDeleteButton(request, response);
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
			
			List<UserEntity> users = null;
			users = userService.getUsers();
			request.setAttribute("users", users);
			
		} catch (Exception e) {
			throw e;
		}		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/TableJsp.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleCreateButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("create.do?submit=Display");
		
	}
	
	private void handleViewButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		response.sendRedirect("view.do?submit=Display&id=" + id);
		
	}
	
	private void handleEditButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		response.sendRedirect("edit.do?submit=Display&id=" + id);
		
	}	
	
	private void handleDeleteButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		response.sendRedirect("delete.do?submit=Display&id=" + id);
		
	}
		
}
