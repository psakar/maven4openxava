package ${package}.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Required;
import org.openxava.oxapp.model.type.InvoiceStatusType;

@Entity
public class Invoice {
	
	@Id
	@Column(length=5)
	@Required
	private String id;
	
	@Column
	@Required
	private InvoiceStatusType status;

	@Column
	@Required
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InvoiceStatusType getStatus() {
		return status;
	}

	public void setStatus(InvoiceStatusType status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@DescriptionsList
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer c) {
		this.customer = c;
	}

}
