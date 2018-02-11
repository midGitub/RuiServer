import java.util.List;

import com.game.GameApplication;
import com.game.logic.Person;
import com.game.logic.PersonRepository;
import com.game.logic.player.dao.PlayerEntityDao;
import com.game.logic.player.entity.PlayerEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameApplication.class)
public class SpringbootJpaApplicationTests {

	//    @Autowired
//    private ConfigBean propsConfig;
	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PlayerEntityDao playerEntityDao;

//	@Autowired
//	private SpringdataDaoSupport<PlayerEntity> springdataDaoSupport;

	@Test
	public void queryPlayerEntity() {
		for (int i = 1; i< 5; i++) {
			PlayerEntity playerEntity = playerEntityDao.findOne((long)i);
//			playerEntity.setId(i);
			playerEntity.setName("ni:"+i*200);
			playerEntity.setCreateTime(i*300);
			playerEntityDao.deleteData(playerEntity);
		}
	}

	@Test
	public void runPlayerEntity() {
		for (int i = 1; i< 10; i++) {
			PlayerEntity playerEntity = new PlayerEntity();
			playerEntity.setId(i);
			playerEntity.setName("ni:"+i);
			playerEntity.setCreateTime(i*100);
//			springdataDaoSupport.saveData(playerEntity);
		}
	}
	
	@Test
	public void savePerson() {
		Person person = new Person("zsy", 1, "shenzhen");
		person.setId(1L);
		personRepository.insertData(person);
	}
	
	@Test
	public void findByAddress() {
		List<Person> list = personRepository.findByAddress("shenzhen");
		for(Person p:list) {
			System.out.println(p);
		}
	}
	
	@Test
	public void findByNameAndAddress(){
		Person person = personRepository.findByNameAndAddress("zxb", "shenzhen");
		System.out.println(person);
	}
	
	@Test
	public void withNameAndAddressQuery(){
		Person person = personRepository.withNameAndAddressQuery("zsy", "shenzhen");
		System.out.println(person);
	}
	
	@Test
	public void withNameAndAddressNamedQuery(){
		Person person = personRepository.withNameAndAddressNamedQuery("zsy", "shenzhen");
		System.out.println(person);
	}

	@Test
	public void testDisplayPropsValue() {
//        String name = propsConfig.getName();
//        String autor = propsConfig.getAutor();
//        String hello = propsConfig.getHello();
//
//
//        System.out.println("name -> " + name);
//        System.out.println("autor -> " + autor);
//        System.out.println("hello -> " + hello);

	}
}
