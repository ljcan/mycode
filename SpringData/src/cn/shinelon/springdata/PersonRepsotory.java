package cn.shinelon.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
//这个两个泛型类型一个是实体类，一个是主键的类型
/**
 * 1.Repository是一个空接口，即一个标记接口
 * 2.若我们定义的接口继承了Repository接口，则该接口会被识别为一个Repository bean
 * 纳入到IOC容器中，进而可以在该接口中定义满足一定规范的方法
 * 3.实际上也可以通过注解@RepositoryDefinition来替代继承Repository接口将一个接口识别为一个Repository bean
 * @author Shinelon
 */
/**
 * 在Repository接口中声明方法
 * 1.不是随便声明的，而需要符合一定的规范
 * 2.查询方法以get |find |read开头
 * 3.涉及查询条件的时候，条件属性用条件关键字连接
 * 4.要注意的是，条件属性以大写字母开头
 * 5.支持级联属性，若当前类有符合条件的属性，则优先使用，而不使用级联属性
 * 若想使用级联属性，则在声明方法时属性之间用"_"连接     例：getByAddress_IdGreaterThan()
 * Hibernate: 
    select
        person0_.id as id1_1_,
        person0_.address_id as address_6_1_,
        person0_.add_id as add_id2_1_,
        person0_.birth as birth3_1_,
        person0_.email as email4_1_,
        person0_.last_name as last_nam5_1_ 
    from
        jpa_person person0_ 
    where
        person0_.add_id>?
 * @author Shinelon
 *
 */
//@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)
public interface PersonRepsotory extends Repository<Person, Integer>{
	//根据name查找数据
	Person getByLastName(String lastName);
	//WHERE LAST_NAME LIKE ?% AND ID < ?
	List<Person> getByLastNameStartingWithAndIdLessThan(String lastname,Integer id);
	//WHERE LAST_NAME LIKE %? AND ID < ?
	List<Person> getByLastNameEndingWithAndIdLessThan(String lastname,Integer id);
	//WHERE EMAIL IN (?,?,?) OR BIRTH < ?
	List<Person> getByEmailInOrBirthLessThan(List<String> emails,Date birth);
	//WHERE a.id > ?		级联查询
	List<Person> getByAddressIdGreaterThan(Integer id);
	//查询id最大的那个person
	//注意，下面sql语句中的Person不是数据库中的属性名，而是对应的实体类的实体名
	//使用@Query注解，可以自定义JPQL实现更灵活的查询
	@Query("select p from Person p where p.id=(select max(p2.id) from Person p2)")
	Person getMaxIdPerson();
	//为@Query注解传递参数的方式1：使用占位符
	@Query("select p from Person p where p.lastName=?1 and p.email like %?2")
	List<Person> testQueryParams(String lastName,String email);
	//为@Query注解传递参数的方式2：命名参数的方式
	@Query("select p from Person p where p.lastName=:name and p.email like %:email")
	List<Person> testQueryParams2(@Param("email") String email,@Param("name") String lastName);
	//设置nativeQuery=true即可以使用原生的sql语句进行查询
	@Query(value="select count(id) from jpa_person",nativeQuery=true)
	long testNativeSql();
	
	//可以通过自定义的JPQL来完成update和delete操作，注意：JPQL不支持insert操作
	//在注解@Query注解中编写JPQL语句，但必须使用@Modifying进行修饰，以通知SpringData这是一个update或delete操作
	//update或者delete操作需要使用事务，此时需要定义service层，在service层进行事务操作
	//在默认情况下，SpringData的每个方法上有事务，但都是一个只读事务，他们不能完成修改操作
	//通过id修改email
	@Modifying
	@Query("update Person p set p.email=:email where id=:id ")
	public void testUpdateTranactional(@Param("email") String email,@Param("id") Integer id);
}
