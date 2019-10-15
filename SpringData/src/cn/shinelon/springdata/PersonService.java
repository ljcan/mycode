package cn.shinelon.springdata;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired
	public PersonRepsotory personRepsotory;
	
	@Transactional
	public void updateData(String email,Integer id) {
		personRepsotory.testUpdateTranactional(email, id);
	}
}
