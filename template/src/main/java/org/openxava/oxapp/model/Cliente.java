package org.openxava.oxapp.model;

import org.openxava.annotations.Required;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author Javier Paniza
 */

@Entity
public class Cliente {
	
	@Id @Column(length=5) @Required   
	private String id;
	
	@Column(length=40)
	@Required
	private String email;

	@Column(length=40)
	@Required
	private String first;

	@Column(length=40)
	@Required
	private String last;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
}
