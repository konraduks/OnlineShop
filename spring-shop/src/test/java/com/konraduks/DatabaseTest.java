package com.konraduks;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.konraduks.model.Category;
import com.konraduks.model.Product;
import com.konraduks.model.User;
import com.konraduks.repository.CategoryRepository;
import com.konraduks.repository.OrderRepository;
import com.konraduks.repository.ProductRepository;
import com.konraduks.repository.UserRepository;
import com.konraduks.repository.UserRoleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DatabaseTest {	

	@Autowired
	DataSource dataSource;
	
	@Autowired
    private TestEntityManager entityManager;
	
	/*@MockBean
    private GeneralSettingsProperties generalSettingsProperties;*/

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
    
    @Test
    public void findByUsernameShouldReturnUser() {
        this.entityManager.persist(new User("sboot", "123"));
    	//this.repository.save(new User("sboot", "123"));
        User user = this.userRepository.findByUsername("sboot");
        
        assertThat(user.getUsername()).isEqualTo("sboot");
        assertThat(user.getPassword()).isEqualTo("123");
    }
    
    @Test
    public void findByNameShouldReturnCategory() {
    	Category cat = new Category();
    	cat.setName("CatTest");
        this.entityManager.persist(cat);
    	//this.repository.save(new User("sboot", "123"));
        Category category = this.categoryRepository.findByName("CatTest");
        
        assertThat(category.getName()).isEqualTo("CatTest");        
    }
    
    @Test
    public void findProduct() {
    	Product prod = new Product();
    	prod.setName("ProdTest");
    	prod.setPrice(21.09);
    	this.entityManager.persist(prod);
    	List<Product> products = productRepository.findAll();
    	
    	for(Product p : products){
    		if(p.getName().equals("ProdTest")){
    			assertThat(p.getPrice()).isEqualTo(21.09);
    		}
    	}
    }
    
}
