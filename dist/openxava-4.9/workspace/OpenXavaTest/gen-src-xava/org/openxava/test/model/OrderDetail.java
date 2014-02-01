
// File generated by OpenXava: Wed Sep 11 11:56:50 CEST 2013
// Archivo generado por OpenXava: Wed Sep 11 11:56:50 CEST 2013

// WARNING: NO EDIT
// OJO: NO EDITAR
// Component: Order		Aggregate/Agregado: OrderDetail

package org.openxava.test.model;

import java.util.*;
import java.math.*;
import java.rmi.RemoteException;
import org.openxava.component.MetaComponent;
import org.openxava.model.meta.MetaModel;
import org.openxava.util.*;

/**
 * 
 * @author MCarmen Gimeno
 */
public class OrderDetail implements java.io.Serializable, org.openxava.test.model.IOrderDetail {	

	// Constructor
	public OrderDetail() {
		initMembers();
	} 

	private void initMembers() { 
		setId(null); 
		setQuantity(0); 	
	} 
	
	// Properties/Propiedades 	
	/**
	 * 
	 * 
	 */
	public java.math.BigDecimal getAmount() { 		
		try {			
			org.openxava.test.calculators.OrderDetailAmountCalculator amountCalculator= (org.openxava.test.calculators.OrderDetailAmountCalculator)
				getMetaModel().getMetaProperty("amount").getMetaCalculator().createCalculator();  	
			
			amountCalculator.setProductNumber(getProduct().getNumber());  	
			
			amountCalculator.setQuantity(getQuantity()); 
			return (java.math.BigDecimal) amountCalculator.calculate();
		}
		catch (NullPointerException ex) {
			// Usually for multilevel property access with null references 
			return null; 			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.calculate_value_error", "Amount", "OrderDetail", ex.getLocalizedMessage()));
		} 		
	}
	public void setAmount(java.math.BigDecimal newAmount) {
		// for it is in value object
		// para que aparezca en los value objects
	} 
	private static org.openxava.converters.IConverter quantityConverter;
	private org.openxava.converters.IConverter getQuantityConverter() {
		if (quantityConverter == null) {
			try {
				quantityConverter = (org.openxava.converters.IConverter) 
					getMetaModel().getMapping().getConverter("quantity");
			}
			catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(XavaResources.getString("generator.create_converter_error", "quantity"));
			}
			
		}	
		return quantityConverter;
	} 
	private java.lang.Integer quantity;
	private java.lang.Integer get_Quantity() {
		return quantity;
	}
	private void set_Quantity(java.lang.Integer newQuantity) {
		this.quantity = newQuantity;
	} 	
	
	/**
	 * 
	 * 
	 */
	public int getQuantity() {
		try {
			return ((Integer) getQuantityConverter().toJava(get_Quantity())).intValue();
		}
		catch (org.openxava.converters.ConversionException ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.conversion_error", "Quantity", "OrderDetail", "int"));
		}
	}
	
	/**
	 * 
	 */
	public void setQuantity(int newQuantity) {
		try { 
			set_Quantity((java.lang.Integer) getQuantityConverter().toDB(new Integer(newQuantity)));
		}
		catch (org.openxava.converters.ConversionException ex) {
			ex.printStackTrace();
			throw new RuntimeException(XavaResources.getString("generator.conversion_error", "Quantity", "OrderDetail", "int"));
		}		
	} 
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String newId) {
		this.id = newId;
	} 

	// References/Referencias 

	private org.openxava.test.model.IProduct2 product; 	
	public org.openxava.test.model.IProduct2 getProduct() {
		if (product != null) {
			// Because not-found='ignore' annul lazy initialization, we simulate it
			try {
				product.toString();
			}
			catch (Exception ex) {
				return null;
			}
		}	
		return product;
	}
	public void setProduct(org.openxava.test.model.IProduct2 newProduct2) {
		if (newProduct2 != null && !(newProduct2 instanceof org.openxava.test.model.Product2)) {
			throw new IllegalArgumentException(XavaResources.getString("ejb_to_pojo_illegal")); 
		}
		this.product = newProduct2; 
	} 

	private org.openxava.test.model.IOrder order; 	
	public org.openxava.test.model.IOrder getOrder() {
		if (order != null) {
			// Because not-found='ignore' annul lazy initialization, we simulate it
			try {
				order.toString();
			}
			catch (Exception ex) {
				return null;
			}
		}	
		return order;
	}
	public void setOrder(org.openxava.test.model.IOrder newOrder) {
		if (newOrder != null && !(newOrder instanceof org.openxava.test.model.Order)) {
			throw new IllegalArgumentException(XavaResources.getString("ejb_to_pojo_illegal")); 
		}
		this.order = newOrder; 
	} 

	// Colecciones/Collections 

	// Methods/Metodos 	

	// User defined finders/Buscadores definidos por el usuario 


	private static MetaModel metaModel;
	public MetaModel getMetaModel() throws XavaException {
		if (metaModel == null) { 
			metaModel = MetaComponent.get("Order").getMetaAggregate("OrderDetail"); 	
		}
		return metaModel;
	}
	
	public String toString() {		
		try {
			return getMetaModel().toString(this);
		}
		catch (XavaException ex) {
			System.err.println(XavaResources.getString("toString_warning", "OrderDetail"));
			return super.toString();
		}		
	}

	public boolean equals(Object other) {		
		if (other == null) return false;
		return toString().equals(other.toString());
	}
	
	public int hashCode() {		
		return toString().hashCode();
	}
	
}