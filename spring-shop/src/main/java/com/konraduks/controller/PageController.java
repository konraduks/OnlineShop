package com.konraduks.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.konraduks.cart.Utils;
import com.konraduks.model.CartElement;
import com.konraduks.model.Category;
import com.konraduks.model.OrderTab;
import com.konraduks.model.Product;
import com.konraduks.model.User;
import com.konraduks.model.User_role;
import com.konraduks.repository.CategoryRepository;
import com.konraduks.repository.OrderRepository;
import com.konraduks.repository.ProductRepository;
import com.konraduks.repository.UserRepository;
import com.konraduks.repository.UserRoleRepository;



//http://websystique.com/spring-security/spring-security-4-method-security-using-preauthorize-postauthorize-secured-el/
//http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
@Controller
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PageController extends WebSecurityConfigurerAdapter{	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");*/
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
				"select username, password, enabled from user where username=?")
		.authoritiesByUsernameQuery(
				"select username, role from user_role where username=?");
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
      http.authorizeRequests()
        //.antMatchers("/", "/home").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
      	.antMatchers("/").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
      	.antMatchers("/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
      	.antMatchers("/cart").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
      	.antMatchers("/myshopping").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
      	.antMatchers("/add").access("hasRole('ADMIN')")
      	.antMatchers("/editProduct").access("hasRole('ADMIN')")
      	.antMatchers("/categories").access("hasRole('ADMIN')")
      	.antMatchers("/orders").access("hasRole('ADMIN')")
        .and().formLogin().loginPage("/login")
        .usernameParameter("ssoId").passwordParameter("password")
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
	
	@RequestMapping("/")
	public String index(){	
		//System.out.println("START");
        return "index";
	}
	

	@RequestMapping("/hello")
	public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name){		
		model.addAttribute("name", name);
        return "hello";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {		
		model.addAttribute("product", new Product());
		
		List<Category> categories = categoryRepository.findAll();	
		//System.out.println("Ilosc: " + products.size());
		model.addAttribute("categories", categories);
		
		return "productAdd";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute ("product") Product product, @RequestParam(value = "catProd", defaultValue = "") String category, BindingResult result, SessionStatus status) {		
		
		/*System.out.println(category);
		System.out.println(product.toString());*/
		
		if (result.hasErrors()) {
			return "productForm";
		}
		
		product.setCategory(categoryRepository.findByName(category));
		
		status.setComplete();
		productRepository.save(product);
		
		return "redirect:/";
	}
	
	//@RequestMapping("/list")
	@RequestMapping(value = "list", method = RequestMethod.GET)	
	public String list(Model model) {					
		List<Product> products = productRepository.findAll();	
		//System.out.println("Ilosc: " + products.size());
		model.addAttribute("products", products);
		return "productList";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String updateCart(HttpServletRequest request, Model model,
			@RequestParam(value = "id", defaultValue = "") Long id,
            @RequestParam(value = "quantity", defaultValue = "") int quantity) {
		System.out.println("Otrzymano id: " + id + " ,ilosc: " + quantity);
		Utils.getCartInSession(request).addProduct(productRepository.findById(id), quantity);;
		//return list(model);
		return "redirect:/list";
	}
	
	/*@RequestMapping({ "/productList" })
    public String listProductHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResult<ProductInfo> result = productDAO.queryProducts(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationProducts", result);
        return "productList";
    }*/
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value = "registry", method = RequestMethod.GET)	
	public String registry(Model model){
		model.addAttribute("user", new User());		
		return "registry";
	}
	
	@RequestMapping(value = "registry", method = RequestMethod.POST)	
	public String registry(@Valid @ModelAttribute ("user") User user, BindingResult result, SessionStatus status){
		
		if (result.hasErrors()) {
			return "userForm";
		}
		
		status.setComplete();
		userRepository.save(user);
		User_role ur = new User_role();
		ur.setUsername(user.getUsername());
		ur.setRole("ROLE_USER");
		userRoleRepository.save(ur);
		
		return "redirect:/";
	}
	
	@RequestMapping("/Access_Denied")
	public String Access_Denied(){
		return "accessDenied";
	}
	
	@RequestMapping(value = "cart", method = RequestMethod.GET)	
	public String Cart(HttpServletRequest request, Model model){
		//System.out.println("Uzytkownik: " + request.getUserPrincipal());
		System.out.println("Uzytkownik: " + userRepository.findByUsername(request.getUserPrincipal().getName()));
		List<CartElement> cartElements = Utils.getCartInSession(request).getCartElements();
		model.addAttribute("cart", cartElements);
		return "shoppingCart";
	}
	
	@RequestMapping(value = "cart", method = RequestMethod.POST)
	public String deleteProductFromCart(@RequestParam(value = "id", defaultValue = "") Long id, HttpServletRequest request, Model model){
//		System.out.println("delete product: " + product.toString());
		System.out.println("delete product: " + id + " " + productRepository.findById(id).toString());
		Utils.getCartInSession(request).removeProduct(productRepository.findById(id));
		return Cart(request, model);
	}
	
	@RequestMapping(value = "submitOrder", method = RequestMethod.POST)	
	public String SubmitOrder(HttpServletRequest request){
		List<CartElement> cartElements = Utils.getCartInSession(request).getCartElements();
		if(cartElements != null){
			Utils.removeCartInSession(request);
			OrderTab order = new OrderTab();
			order.setProducts(cartElements);
			order.setUser(userRepository.findByUsername(request.getUserPrincipal().getName()));
			orderRepository.save(order);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam(value = "id", defaultValue = "") Long id, Model model){
		System.out.println("delete product: " + id);
		productRepository.delete(id);
		return list(model);
	}
	
	@RequestMapping(value = "editProduct", method = RequestMethod.GET)
	public String editProduct(@RequestParam(value = "id", defaultValue = "") Long id, Model model){
		System.out.println("edit product: " + id);
		System.out.println("edit product: " + productRepository.findById(id).toString());
		model.addAttribute("product", productRepository.findById(id));
		//return list(model);
		return "productEdit";
	}
	
	@RequestMapping(value = "editProduct", method = RequestMethod.POST)
	public String editProductPost(@Valid @ModelAttribute ("product") Product product, @RequestParam(value = "id", defaultValue = "") Long id, Model model){
		System.out.println("edit product post: " + product.toString());
		System.out.println("edit product post id: " + id);
		//productRepository.setProductById(product.getName(), product.getPrice(), id);
		Product p = productRepository.findById(id);
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		productRepository.save(p);
		//return list(model);
		return list(model);
	}
	
	@RequestMapping(value = "categories", method = RequestMethod.GET)
	public String categories(Model model){
		
		model.addAttribute("category", new Category());
		List<Category> categories = categoryRepository.findAll();	
		//System.out.println("Ilosc: " + products.size());
		model.addAttribute("categories", categories);
		
		List<Product> productsWithoutCat = productRepository.getWithoutCategory();
		//System.out.println(productsWithoutCat.toString());
		model.addAttribute("productsWithoutCat", productsWithoutCat);
		
		List<Product> allproductswithCat = productRepository.getWithCategory();
		model.addAttribute("allproductswithCat", allproductswithCat);
		
		return "categories";
	}
	
	@RequestMapping(value = "categories", method = RequestMethod.POST)
	public String addCategory(@Valid @ModelAttribute ("product") Category category, Model model){
		System.out.println("Dodaj kategorie: " + category.toString());
		categoryRepository.save(category);
		return "redirect:/categories";
	}
	
	@RequestMapping(value = "deleteCategory", method = RequestMethod.POST)
	public String delcategory(@RequestParam(value = "catId", defaultValue = "") Long catId, Model model){
		//Category cat = categoryRepository.findById(catId);
		System.out.println("Kategoria do usuniecia: " + catId);
		List<Product> products = productRepository.findByCategory(categoryRepository.findById(catId));
		/*if(!products.isEmpty()){
			productRepository.delete(products);
		}*/
		System.out.println("del catgory1");
		for(Product pro : products){
			pro.setCategory(null);
			productRepository.save(pro);
		}
		System.out.println("del catgory2");
		categoryRepository.delete(catId);
		System.out.println(products.toString());
		return "redirect:/categories";
	}
	
	@RequestMapping(value = "setCategory", method = RequestMethod.POST)
	public String setCategoryForProduct(@RequestParam(value = "catId", defaultValue = "") Long catId, @RequestParam(value = "choosenCat", defaultValue = "") String category, Model model){
		
		Product p = productRepository.findById(catId);
		p.setCategory(categoryRepository.findByName(category));
		productRepository.save(p);
		//System.out.println("Do ustawienia: produkt: " + catId + " kategoria: " + category);
		return "redirect:/categories";
	}
	
	@RequestMapping(value = "myshopping", method = RequestMethod.GET)
	public String myShopping(Model model, HttpServletRequest request){
		//request.getUserPrincipal().getName()
		//System.out.println(request.getUserPrincipal().getName());
		List<OrderTab> orders = orderRepository.findByUser(userRepository.findByUsername(request.getUserPrincipal().getName()));
		System.out.println(orders.toString());
		model.addAttribute("orders", orders);
		return "myShopping";
	}
	
	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public String orderStatus(Model model){
		List<OrderTab> orders1 = orderRepository.findByStatus(1);
		List<OrderTab> orders2 = orderRepository.findByStatus(2);
		List<OrderTab> orders3 = orderRepository.findByStatus(3);
		/*System.out.println(orders1.toString());
		System.out.println(orders2.toString());*/
		model.addAttribute("orders1", orders1);
		model.addAttribute("orders2", orders2);
		model.addAttribute("orders3", orders3);
		return "orders";
	}
	
	@RequestMapping(value = "orders", method = RequestMethod.POST)
	public String changeOrderStatus(@RequestParam(value = "id", defaultValue = "") Long id, @RequestParam(value = "status", defaultValue = "") int status, Model model){
		//System.out.println("id: " + id + " status: " + status);
		OrderTab ot = orderRepository.findById(id);
		ot.setStatus(status);
		orderRepository.save(ot);
		return "redirect:/orders";
	}
	
}
