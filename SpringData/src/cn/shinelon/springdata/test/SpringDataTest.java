package cn.shinelon.springdata.test;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.shinelon.springdata.Person;
import cn.shinelon.springdata.PersonRepsotory;
import cn.shinelon.springdata.PersonService;

class SpringDataTest {
	private ApplicationContext applicationContext=null;
	private PersonRepsotory personRepsotory=null;
	private PersonService personService=null;
	{
		applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepsotory=applicationContext.getBean(PersonRepsotory.class);
		personService=applicationContext.getBean(PersonService.class);
	}
	@Test
	void testDataSource() throws SQLException {
		DataSource dataSource=applicationContext.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	@Test
	public void testJpa() {
		
	}
	@Test
	public void TestPerson() {
		Person person=new Person();
		person=personRepsotory.getByLastName("shinelon");
		System.out.println(person.toString());
	}
	@Test
	public void TestByLastName() {
		List<Person> list=personRepsotory.getByLastNameStartingWithAndIdLessThan("m", 3);
		List<Person> list1=personRepsotory.getByLastNameEndingWithAndIdLessThan("m", 4);
		List<Person> list2=personRepsotory.getByEmailInOrBirthLessThan(
							Arrays.asList("46499@qq.com","56464@qq.com"),
							new Date());
		System.out.println(list);
		System.out.println(list1);
		System.out.println(list2.size());
	}
	//测试级联查询
	@Test
	public void TestByAddressId() {
		List<Person> list=personRepsotory.getByAddressIdGreaterThan(1);
		System.out.println(list);
	}
	//测试子查询
	@Test
	public void TestByIdMax() {
		Person person=personRepsotory.getMaxIdPerson();
		System.out.println(person);
	}
	//测试@Query传递参数
	@Test
	public void TestQueryParams() {
		List<Person> list=personRepsotory.testQueryParams("mm", "m");
		System.out.println(list);
	}
	//测试@Query传递参数2
		@Test
		public void TestQueryParams2() {
			List<Person> list=personRepsotory.testQueryParams2("m", "mm");
			System.out.println(list);
		}
	//测试使用原生的sql进行查询
		@Test
		public void TestNativeSql() {
			long count=personRepsotory.testNativeSql();
			System.out.println(count);
		}
	//测试修改字段
		@Test
		public void testUpdata() {
			personService.updateData("123456@qq.com", 2);
		}

}
