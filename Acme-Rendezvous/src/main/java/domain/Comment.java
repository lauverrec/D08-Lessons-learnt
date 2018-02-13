
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date	writtenMoment;
	private String	text;
	private String	picture;


	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getWrittenMoment() {
		return this.writtenMoment;
	}

	public void setWrittenMoment(Date writtenMoment) {
		this.writtenMoment = writtenMoment;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Comment>	answers;
	private Comment				respondedTo;
	private Rendezvouse			rendezvouse;
	private User				user;


	@Valid
	@NotNull
	@OneToMany(mappedBy = "respondedTo")
	public Collection<Comment> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Collection<Comment> answers) {
		this.answers = answers;
	}

	@Valid
	@ManyToOne(optional = false)
	public Comment getRespondedTo() {
		return this.respondedTo;
	}

	public void setRespondedTo(Comment respondedTo) {
		this.respondedTo = respondedTo;
	}

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