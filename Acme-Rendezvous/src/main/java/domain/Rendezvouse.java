
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Rendezvouse extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	name;
	private String	description;
	private Date	organisedMoment;
	private String	picture;
	private GPS		gps;
	private boolean	draftMode;
	private boolean	deleted;
	private boolean	forAdult;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getOrganisedMoment() {
		return this.organisedMoment;
	}

	public void setOrganisedMoment(Date organisedMoment) {
		this.organisedMoment = organisedMoment;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public GPS getGps() {
		return this.gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

	public boolean isDraftMode() {
		return this.draftMode;
	}

	public void setDraftMode(boolean draftMode) {
		this.draftMode = draftMode;
	}

	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isForAdult() {
		return this.forAdult;
	}

	public void setForAdult(boolean forAdult) {
		this.forAdult = forAdult;
	}


	// Relationships ----------------------------------------------------------
	private Collection<User>		assistants;
	private Collection<Rendezvouse>	similarRendezvouses;


	@Valid
	@NotNull
	@ManyToMany
	public Collection<User> getAssistants() {
		return this.assistants;
	}

	public void setAssistants(Collection<User> assistants) {
		this.assistants = assistants;
	}

	@NotNull
	@Valid
	@OneToMany
	public Collection<Rendezvouse> getSimilarRendezvouses() {
		return this.similarRendezvouses;
	}

	public void setSimilarRendezvouses(Collection<Rendezvouse> similarRendezvouses) {
		this.similarRendezvouses = similarRendezvouses;
	}

}
