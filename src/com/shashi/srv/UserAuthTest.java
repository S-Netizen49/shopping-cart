package com.shashi.srv;

import org.junit.Test;

import com.shashi.beans.UserBean;
import com.shashi.service.impl.UserServiceImpl;

import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAuthTest {
	

    @Test
    public void testCustomerLogin_Successful() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        UserServiceImpl userService = mock(UserServiceImpl.class);

        when(request.getParameter("username")).thenReturn("guest@gmail.com");
        when(request.getParameter("password")).thenReturn("guest");
        when(request.getParameter("usertype")).thenReturn("customer");
        when(request.getSession()).thenReturn(session); 
        when(request.getRequestDispatcher("userHome.jsp")).thenReturn(rd);
        when(userService.isValidCredential("guest@gmail.com", "guest")).thenReturn("valid");
        when(userService.getUserDetails("guest@gmail.com", "guest")).thenReturn(new UserBean());

        // Act
        LoginSrv servlet = new LoginSrv();
        servlet.doGet(request, response);

        // Assert
        verify(session).setAttribute(eq("username"), eq("guest@gmail.com"));
        verify(session).setAttribute(eq("password"), eq("guest"));
        verify(session).setAttribute(eq("usertype"), eq("customer"));
        verify(rd).forward(request, response);
    }
    
    @Test
    public void testCustomerLogin_Failed() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        UserServiceImpl userService = mock(UserServiceImpl.class);
        
        when(request.getParameter("username")).thenReturn("guest@gmail.com");
        when(request.getParameter("password")).thenReturn("wrong_password");
        when(request.getParameter("usertype")).thenReturn("customer");
        when(userService.isValidCredential("guest@gmail.com", "wrong_password")).thenReturn("Invalid credentials");
        when(request.getRequestDispatcher("login.jsp?message=Login Denied! Incorrect Username or Password"))
        .thenReturn(rd);
        // Act
        LoginSrv servlet = new LoginSrv();
        servlet.doGet(request, response);

        // Assert
        verify(rd).forward(request, response);
    }
    

    @Test
    public void testAdminLoginSuccess() throws Exception {
    	// ARRANGE
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(request.getParameter("username")).thenReturn("admin@gmail.com");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getParameter("usertype")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("adminViewProduct.jsp")).thenReturn(rd);

        // ACT
        LoginSrv servlet = new LoginSrv();
        servlet.doGet(request, response);

        // ASSERT
        verify(session).setAttribute("username", "admin@gmail.com");
        verify(session).setAttribute("password", "admin");
        verify(session).setAttribute("usertype", "admin");
        verify(rd).forward(request, response);
    }
    
    @Test
    public void testAdminLoginFailure() throws Exception {
    	// ARRANGE
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(request.getParameter("username")).thenReturn("admin@gmail.com");
        when(request.getParameter("password")).thenReturn("invalidpassword");
        when(request.getParameter("usertype")).thenReturn("admin");
        when(request.getRequestDispatcher("login.jsp?message=Login Denied! Invalid Username or password.")).thenReturn(rd);

        // ACT
        LoginSrv servlet = new LoginSrv();
        servlet.doGet(request, response);

        // ASSERT
        verify(rd).include(request, response);
    }
}
