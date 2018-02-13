
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Reply extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	reply;


	@NotNull
	public String getReply() {
		return this.reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}


	//-----------------------------Relationships-----------------------------
	private User		user;
	private Question	question;


	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return this.question;
	}

	@Valid
	@ManyToOne(optional = false)
	public void setQuestion(Question question) {
		this.question = question;
	}

}
