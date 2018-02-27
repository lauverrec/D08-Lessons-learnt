
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Administrator;
import domain.Rendezvouse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	//	// Service under test ---------------------------------
	@Autowired
	private AdministratorService	administratorService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		Administrator administrator;
		administrator = this.administratorService.create();
		Assert.notNull(administrator);
	}

	@Test
	public void testSave() {
		Administrator administrator;
		administrator = this.administratorService.create();

		administrator.setName("name");
		administrator.setSurname("surname");
		administrator.setEmailAddress("email@gmail.com");
		administrator.setPhoneNumber("31333");
		administrator.setPostalAddress("address");

		administrator = this.administratorService.save(administrator);
		Assert.notNull(administrator.getId());

	}

	@Test
	public void testFindAll() {
		Collection<Administrator> administrators;
		administrators = this.administratorService.findAll();
		Assert.notEmpty(administrators);
		Assert.notNull(administrators);
	}

	@Test
	public void testFindOne() {
		Administrator administrator;
		administrator = this.administratorService.findOne(super.getEntityId("administrator"));
		Assert.notNull(administrator);
	}

	@Test
	public void testFindAvgStddevOfTheNumOfRendezvouseCreatedPerUser() {
		Double[] res;
		res = this.administratorService.findAvgStddevOfTheNumOfRendezvouseCreatedPerUser();
		Assert.notNull(res);
	}

	@Test
	public void findRatioUsersWithRendezvousesAndNotRendezvouses() {
		Double result;
		result = this.administratorService.findRatioUsersWithRendezvousesAndNotRendezvouses();
		Assert.notNull(result);
	}

	@Test
	public void findAvgStddevOfTheNumOfAssistansPerRendezvouse() {
		Double result;
		result = this.administratorService.findAvgStddevOfTheNumOfAssistansPerRendezvouse();
		Assert.notNull(result);
	}

	@Test
	public void findAvgStddevOfTheNumOfRendezvouseAssitedPerUser() {
		Double[] res;
		res = this.administratorService.findAvgStddevOfTheNumOfRendezvouseAssitedPerUser();
		Assert.notNull(res);
	}

	@Test
	public void findTop10RendezvouseWithRSVPd() {
		Collection<Rendezvouse> res;
		res = this.administratorService.findTop10RendezvouseWithRSVPd();
		Assert.notNull(res);
	}

	@Test
	public void findAvgStddevOfTheNumOfAnnouncementsPerRendezvous() {
		Double[] res;
		res = this.administratorService.findAvgStddevOfTheNumOfAnnouncementsPerRendezvous();
		Assert.notNull(res);
	}

	@Test
	public void findRendezvousesWithMore75PerCent() {
		Collection<Rendezvouse> res;
		res = this.administratorService.findRendezvousesWithMore75PerCent();
		Assert.notNull(res);
	}

	@Test
	public void findRendezvousesWithAreLinked() {
		Collection<Rendezvouse> res;
		res = this.administratorService.findRendezvousesWithAreLinked();
		Assert.notNull(res);
	}

	@Test
	public void findAvgStddevOfTheNumOfQuestionsPerRendezvous() {
		Double[] res;
		res = this.administratorService.findAvgStddevOfTheNumOfQuestionsPerRendezvous();
		Assert.notNull(res);
	}

	@Test
	public void findAvgStddevOfTheNumOfAnswerToQuestionsPerRendezvous() {
		Double[] res;
		res = this.administratorService.findAvgStddevOfTheNumOfAnswerToQuestionsPerRendezvous();
		Assert.notNull(res);
	}

	@Test
	public void findAvgStddevOfTheNumOfRepliesPerComment() {
		Double[] res;
		res = this.administratorService.findAvgStddevOfTheNumOfRepliesPerComment();
		Assert.notNull(res);
	}

}
