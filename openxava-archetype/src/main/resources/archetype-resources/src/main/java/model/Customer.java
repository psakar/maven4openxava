package ${package}.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.openxava.annotations.Required;

@Entity
public class Customer {
	
	@Id
	@Column(length=5)
	@Required
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

	@OneToMany(mappedBy="customer")
	private List<Invoice> invoices;

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> pupils) {
		this.invoices = pupils;
	}

	@Transient
	public String getDescription() {
		return first+" "+last;
	}

}
