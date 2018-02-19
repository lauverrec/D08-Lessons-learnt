
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Comment;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CommentServiceTest extends AbstractTest {

	//Service under test----------------------------------------------------------	

	@Autowired
	private CommentService	commentService;

	@Autowired
	private UserService		userService;


	@Test
	public void testCreate() {
		super.authenticate("user1");
		User user1;
		Comment result;

		user1 = this.userService.findByPrincipal();
		result = this.commentService.create();

		Assert.isTrue(result.getUser().equals(user1));

		this.authenticate(null);
	}

	public void testSave() {
		this.authenticate("user2");
		this.authenticate(null);
	}

	@Test
	public void testDelete() {
		this.authenticate("admin");
		Comment comment;
		comment = this.commentService.findOne(this.getEntityId("comment5"));

		this.commentService.delete(comment);

		this.authenticate(null);

	}
	@Test
	public void testFindOne() {
		Comment result;
		result = this.commentService.findOne(this.getEntityId("comment1"));

		Assert.notNull(result);
	}

	@Test
	public void testFindAll() {
		Collection<Comment> result;
		result = this.commentService.findAll();

		Assert.notNull(result);
		Assert.notEmpty(result);

	}
}
