
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	question;


	@NotBlank
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


	//-----------------------------Relationships-----------------------------
	private Rendezvouse	rendezvouse;
	private User		user;


	@Valid
	@ManyToOne(optional = false)
	public Rendezvouse getRendezvouse() {
		return this.rendezvouse;
	}

	public void setRendezvouse(Rendezvouse rendezvouse) {
		this.rendezvouse = rendezvouse;
	}

	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
