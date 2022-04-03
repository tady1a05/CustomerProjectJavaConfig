package com.toby.customerManagement.Controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toby.customerManagement.Model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@RequestMapping("/list")
	public String showCustomerList(Model model) {
		SessionFactory factory = null;

		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();

			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			List<Customer> customers = session.createQuery("Select c from Customer c").getResultList();
			
			session.getTransaction().commit();
			
			session.close();
			
			model.addAttribute("customers", customers);
		}finally {
			factory.close();
		}
		
		return "CustomerList";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "CustomerForm";
	}
	
	@RequestMapping("/processForm")
	public String addCustomer(@RequestParam("custId") String custId,@ModelAttribute("customer") Customer customer) {
		SessionFactory factory = null;
		System.out.println(custId);
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();

			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
			if(StringUtils.isEmpty(custId)) {
				session.save(customer);
			}else {
				Customer cust = session.get(Customer.class, custId);
				cust.setEmail(customer.getEmail());
				cust.setFirstName(customer.getFirstName());
				cust.setLastName(customer.getLastName());
				session.save(cust);
			}
			session.getTransaction().commit();
			
			session.close();
		}finally {
			factory.close();
		}
	
		return "redirect:list";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String queryCustomer(@RequestParam("custId") String custId,Model model) {
		SessionFactory factory = null;
		Customer customer = null;
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();

			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			customer = session.get(Customer.class, custId);
			
			session.getTransaction().commit();
			
			session.close();
		}finally {
			factory.close();
		}
		
		model.addAttribute("customer", customer);
		
		return "CustomerForm";
	}
	
	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("custId") String custId) {
		System.out.println("enter to deleteCustomer");
		SessionFactory factory = null;
		Customer customer = null;
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();

			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println(custId);
			
			customer = session.get(Customer.class, custId);
			
			session.delete(customer);
			
			session.getTransaction().commit();
			
			session.close();
		}finally {
			factory.close();
		}
				
		return "redirect:list";
	}

}
