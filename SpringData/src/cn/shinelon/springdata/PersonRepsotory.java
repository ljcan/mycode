package cn.shinelon.springdata;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
//���������������һ����ʵ���࣬һ��������������
/**
 * 1.Repository��һ���սӿڣ���һ����ǽӿ�
 * 2.�����Ƕ���Ľӿڼ̳���Repository�ӿڣ���ýӿڻᱻʶ��Ϊһ��Repository bean
 * ���뵽IOC�����У����������ڸýӿ��ж�������һ���淶�ķ���
 * 3.ʵ����Ҳ����ͨ��ע��@RepositoryDefinition������̳�Repository�ӿڽ�һ���ӿ�ʶ��Ϊһ��Repository bean
 * @author Shinelon
 */
/**
 * ��Repository�ӿ�����������
 * 1.������������ģ�����Ҫ����һ���Ĺ淶
 * 2.��ѯ������get |find |read��ͷ
 * 3.�漰��ѯ������ʱ�����������������ؼ�������
 * 4.Ҫע����ǣ����������Դ�д��ĸ��ͷ
 * 5.֧�ּ������ԣ�����ǰ���з������������ԣ�������ʹ�ã�����ʹ�ü�������
 * ����ʹ�ü������ԣ�������������ʱ����֮����"_"����     ����getByAddress_IdGreaterThan()
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
	//����name��������
	Person getByLastName(String lastName);
	//WHERE LAST_NAME LIKE ?% AND ID < ?
	List<Person> getByLastNameStartingWithAndIdLessThan(String lastname,Integer id);
	//WHERE LAST_NAME LIKE %? AND ID < ?
	List<Person> getByLastNameEndingWithAndIdLessThan(String lastname,Integer id);
	//WHERE EMAIL IN (?,?,?) OR BIRTH < ?
	List<Person> getByEmailInOrBirthLessThan(List<String> emails,Date birth);
	//WHERE a.id > ?		������ѯ
	List<Person> getByAddressIdGreaterThan(Integer id);
	//��ѯid�����Ǹ�person
	//ע�⣬����sql����е�Person�������ݿ��е������������Ƕ�Ӧ��ʵ�����ʵ����
	//ʹ��@Queryע�⣬�����Զ���JPQLʵ�ָ����Ĳ�ѯ
	@Query("select p from Person p where p.id=(select max(p2.id) from Person p2)")
	Person getMaxIdPerson();
	//Ϊ@Queryע�⴫�ݲ����ķ�ʽ1��ʹ��ռλ��
	@Query("select p from Person p where p.lastName=?1 and p.email like %?2")
	List<Person> testQueryParams(String lastName,String email);
	//Ϊ@Queryע�⴫�ݲ����ķ�ʽ2�����������ķ�ʽ
	@Query("select p from Person p where p.lastName=:name and p.email like %:email")
	List<Person> testQueryParams2(@Param("email") String email,@Param("name") String lastName);
	//����nativeQuery=true������ʹ��ԭ����sql�����в�ѯ
	@Query(value="select count(id) from jpa_person",nativeQuery=true)
	long testNativeSql();
	
	//����ͨ���Զ����JPQL�����update��delete������ע�⣺JPQL��֧��insert����
	//��ע��@Queryע���б�дJPQL��䣬������ʹ��@Modifying�������Σ���֪ͨSpringData����һ��update��delete����
	//update����delete������Ҫʹ�����񣬴�ʱ��Ҫ����service�㣬��service������������
	//��Ĭ������£�SpringData��ÿ�������������񣬵�����һ��ֻ���������ǲ�������޸Ĳ���
	//ͨ��id�޸�email
	@Modifying
	@Query("update Person p set p.email=:email where id=:id ")
	public void testUpdateTranactional(@Param("email") String email,@Param("id") Integer id);
}
