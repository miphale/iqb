package iqb.ora.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import iqb.ora.api.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OraApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	public void contextLoads() {		
	}
	
	@Test
	public void testAddPerson() {
		Person p = new Person();
		p.setFirstname("John");
		p.setSurname("Smith");
		//TODO A valid ID number is needed run successfully
		p.setIdNumber("9202204720082");
		
		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl()+"/person/new", p, Person.class);
		assertNotNull(postResponse);
		//TODO Uncomment below when the ID number is valid
		//assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testAddPerson_withInvalidIDNumber() {
		Person p = new Person();
		p.setFirstname("John");
		p.setSurname("Smith");
		p.setIdNumber("9202204720082");
		
		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl()+"/person/new", p, Person.class);
		assertNotNull(postResponse);
		assertNull(postResponse.getBody());
	}
	
	@Test
	public void testAddPersonInvalidData() {
		Person p = new Person();
		
		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl()+"/person/new", p, Person.class);
		assertNotNull(postResponse);
		assertNull(postResponse.getBody());
	}
	
	@Test
	public void testListPeople() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+"/person/all", HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}
}