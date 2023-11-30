package com.shashi.srv;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.UserBean;
import com.shashi.service.impl.UserServiceImpl;

public class UserRegisterTest {

	@Mock
	private RegisterSrv servlet;
	
    @InjectMocks
    private UserServiceImpl userService;
	 
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	 

	    @Test
	    public void testRegisterWithValidInfo() throws Exception {
	        HttpServletRequest request = mock(HttpServletRequest.class);
	        HttpServletResponse response = mock(HttpServletResponse.class);
	        RequestDispatcher rd = mock(RequestDispatcher.class);
	        UserServiceImpl userService = mock(UserServiceImpl.class);
	        // Arrange 
	        when(request.getParameter("username")).thenReturn("testUser6");
	        when(request.getParameter("mobile")).thenReturn("1234567890");
	        when(request.getParameter("email")).thenReturn("test0@example.com");
	        when(request.getParameter("address")).thenReturn("123 Main St");
	        when(request.getParameter("pincode")).thenReturn("12345");
	        when(request.getParameter("password")).thenReturn("password");
	        when(request.getParameter("confirmPassword")).thenReturn("password");
	        when(userService.registerUser(any(UserBean.class))).thenReturn("Success");
	        when(request.getRequestDispatcher("register.jsp?message=User Registered Successfully!")).thenReturn(rd);

	        // Act
	        RegisterSrv servlet = new RegisterSrv();
	        servlet.doGet(request, response);

	        // Assert
	        verify(response).setContentType("text/html");
	        verify(rd).forward(request, response);
	    }
	 
    @Test
    public void testRegisterWithAlreadyRegisteredEmailAddress() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        UserServiceImpl userService = mock(UserServiceImpl.class);
        // Arrange 
        when(request.getParameter("username")).thenReturn("testUser1");
        when(request.getParameter("mobile")).thenReturn("1234567890");
        when(request.getParameter("email")).thenReturn("test1@example.com");
        when(request.getParameter("address")).thenReturn("123 Main St");
        when(request.getParameter("pincode")).thenReturn("12345");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("confirmPassword")).thenReturn("password");
        when(userService.registerUser(any(UserBean.class))).thenReturn("Success");
        when(request.getRequestDispatcher("register.jsp?message=Email Id Already Registered!")).thenReturn(rd);

        // Act
        RegisterSrv servlet = new RegisterSrv();
        servlet.doGet(request, response);

        // Assert
        verify(response).setContentType("text/html");
        verify(rd).forward(request, response);
    }

    @Test
    public void testRegisterWithNonMatchingPassword() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);
        // Arrange 
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("mobile")).thenReturn("1234567890");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("address")).thenReturn("123 Main St");
        when(request.getParameter("pincode")).thenReturn("12345");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("confirmPassword")).thenReturn("mismatchedPassword");
        when(request.getRequestDispatcher("register.jsp?message=Password not matching!")).thenReturn(rd);

        // Act
        RegisterSrv servlet = new RegisterSrv();
        servlet.doGet(request, response);
        // Assert
        verify(response).setContentType("text/html");
        verify(rd).forward(request, response);
    }
  
}
