package com.umc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.umc.dao.UserRepo;

@Controller
public class HomeController {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	private EmailValidationImpl emailValidationImpl;
	
	
	@Autowired
	private SendEmailImpl sendemail;
	
	static String Email = "";
	private final int LOGIN = 1;
	private final int VIEW = 2;
	private final int UPDATE = 3;
	private final int CONTACT = 4;
	
	static int index = 0;
	
	@Value("${spring.email.username}")
	private String email_username;
	
	@Value("${spring.email.password}")
	private String email_password;
	
	@Value("${spring.email.host}")
	private String email_host;
	
	@RequestMapping("/")
	public String login()
	{
		return "index.jsp";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@Valid UserEntity ue, BindingResult bindingResult, ModelMap model, HttpServletRequest request,
	HttpServletResponse response)
	{
		if(ue.getEmail().equals("admin@admin.com") && ue.getPassword().equals("admin"))
		{
			model.addAttribute("list", fetchFromDB());
			return new ModelAndView("activity.jsp");
		}
		if(!emailValidationImpl.isValid(ue.getEmail()))
		{
			return new ModelAndView("login.jsp", "error", "Email is not valid!!");
		}
		
			if(repo.findByEmail(ue.getEmail()).size() == 0)
			{
				return new ModelAndView("login.jsp", "error", "User doesn't exist");
			}
			if(!repo.findByEmail(ue.getEmail()).get(0).getPassword().equals(ue.getPassword()))
			{
				return new ModelAndView("login.jsp", "error", "Password doesn't match!");
			}
		saveToDB(LOGIN, repo.findByEmail(ue.getEmail()).get(0).getUid());
		model.addAttribute("email", ue.getEmail());
		model.addAttribute("name", repo.findByEmail(ue.getEmail()).get(0).getUsername());
		model.addAttribute("address", repo.findByEmail(ue.getEmail()).get(0).getAddress());
		Email = ue.getEmail();
		HttpSession session=request.getSession();  
        session.setAttribute("email",ue.getEmail());  
		return new ModelAndView("loginSuccess.jsp", "user", "Welcome "+ 
				repo.findByEmail(ue.getEmail()).get(0).getUsername());
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();  
        session.invalidate(); 
        return "index.jsp";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView register(@Valid UserEntity ue, BindingResult bindingResult)
	{
		if(!emailValidationImpl.isValid(ue.getEmail()))
		{
			return new ModelAndView("register.jsp", "error", "Email is not valid!!");
		}
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("register.jsp", "error", "Name, Email, Password, Address, description field cannot be empty!!");
		}
		if(repo.findByEmail(ue.getEmail()).size() == 0)
		{
			repo.save(ue);
			return new ModelAndView("registerSuccess.jsp");
		}
		else
		{
			return new ModelAndView("error.jsp", "error", "Email Id already exsists");
		}
	}
	
	@RequestMapping(value = "/updateP", method = RequestMethod.POST)
	public ModelAndView updateProfile(@ModelAttribute("User") UserEntity ue, ModelMap model)
	{
		model.addAttribute("email", ue.getEmail());
		return new ModelAndView("update.jsp");
	}
	@RequestMapping(value = "/contact",  method = RequestMethod.POST)
	public ModelAndView contact(@ModelAttribute("User") UserEntity ue, 
		      ModelMap model, HttpServletRequest request,
		  	HttpServletResponse response)
	{
		System.out.println(repo.findByEmail(Email).get(0).getUid());
		saveToDB(CONTACT, repo.findByEmail(Email).get(0).getUid());
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("subject"));
		model.addAttribute("email", ue.getEmail());
		sendemail.send(email_username, request.getParameter("email"), "test", request.getParameter("subject"),
				email_password);
		return new ModelAndView("contact.jsp", "msg", "Message Sent!");
	}
	@RequestMapping(value = "/contactP",  method = RequestMethod.POST)
	public ModelAndView contactP(@ModelAttribute("User") UserEntity ue, 
		      ModelMap model, HttpServletRequest request,
		  	HttpServletResponse response)
	{
		model.addAttribute("email", Email);
		return new ModelAndView("contact.jsp");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("User") UserEntity ue, 
		      ModelMap model)
	{
		saveToDB(UPDATE, repo.findByEmail(Email).get(0).getUid());
		UserEntity uee = repo.findByEmail(ue.getEmail()).get(0);
		uee.setEmail(ue.getEmail());
		uee.setPassword(ue.getPassword());
		uee.setUsername(ue.getUsername());
		uee.setAddress(ue.getAddress());
		model.addAttribute("email", ue.getEmail());
		model.addAttribute("name", ue.getUsername());
		model.addAttribute("address", ue.getAddress());
		repo.save(uee);
		return new ModelAndView("loginSuccess.jsp");
	}
	
	@RequestMapping(value = "/useractivity", method = RequestMethod.POST)
	public ModelAndView useractivity(@ModelAttribute("User") UserEntity ue, 
		      ModelMap model)
	{
		UserEntity uee = repo.findByEmail(ue.getEmail()).get(0);
		uee.setEmail(ue.getEmail());
		uee.setPassword(ue.getPassword());
		uee.setUsername(ue.getUsername());
		uee.setAddress(ue.getAddress());
		model.addAttribute("email", ue.getEmail());
		model.addAttribute("name", ue.getUsername());
		model.addAttribute("address", ue.getAddress());
		repo.save(uee);
		return new ModelAndView("loginSuccess.jsp");
	}
	
	public void saveToDB(int activity_id, int uid)
	{
		fetchFromDB();
		index++;
		System.out.println("index value " + index);
		try {
			Class.forName("org.h2.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:h2:mem:userDB","sa","");
			PreparedStatement ps=con.prepareStatement(  
					"insert into user_activity values(?,?,?,?)");
			ps.setInt(1, index);
			ps.setString(2, ""+ new Date());
			ps.setInt(3, activity_id);
			ps.setInt(4, uid);
			ps.executeUpdate();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}  
	}
	public ArrayList<UserActivityResults> fetchFromDB()
	{
		ArrayList<UserActivityResults> array = new ArrayList<>();
		try {
			Class.forName("org.h2.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:h2:mem:userDB","sa","");
			PreparedStatement ps=con.prepareStatement(  
					"select MAX(ua.ua_id) as uid, ue.username, ue.email, a.name, count(a.name) as amount,MAX(ua.date) as first, MIN(ua.date) as last from user_activity ua INNER JOIN user_entity ue ON ua.uid = ue.id INNER JOIN activity a ON a.a_id = ua.activity_id group by a.name, ue.username order by uid");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				index = rs.getInt("uid");
				array.add(new UserActivityResults(rs.getString("username"), rs.getString("email"), rs.getString("name"),
						rs.getInt("amount"), rs.getString("first"), rs.getString("last")));
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		return array;  
	}
}
//select count(a.name) as amount, ue.username, a.name, MAX(ua.date) as first, MIN(ua.date) as last from user_activity ua INNER JOIN user_entity ue ON ua.uid = ue.id INNER JOIN activity a ON a.a_id = ua.activity_id group by a.name, ue.username;