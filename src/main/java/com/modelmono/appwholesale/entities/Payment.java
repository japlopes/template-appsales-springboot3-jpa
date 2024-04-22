package com.modelmono.appwholesale.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.modelmono.appwholesale.entities.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPayment;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer paymentStatus;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	private Order order;

	public Payment() {
	}

	public Payment(Long idPayment, Instant moment,PaymentStatus paymentStatus, Order order) {
		this.idPayment = idPayment;
		this.moment = moment;
		setPaymentStatus(paymentStatus);
		this.order = order;
	}

	public Long getIdPayment() {
		return idPayment;
	}

	public Instant getMoment() {
		return moment;
	}

	public Order getOrder() {
		return order;
	}
	
	public PaymentStatus getPaymentStatus(){
		return PaymentStatus.valueOf(paymentStatus);
		
	}
	
	public void setPaymentStatus(PaymentStatus paymentStatus){
		if(paymentStatus != null) {
			this.paymentStatus = paymentStatus.getCode();
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idPayment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(idPayment, other.idPayment);
	}
	
}
