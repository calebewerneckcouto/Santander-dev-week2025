package com.cwcdev.dio.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity(name = "tb_account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String number;

	private String agency;

	@Column(precision = 13, scale = 2) // scale quantidade de numeros colocados....
	private BigDecimal balance;
	@Column(name="additional_limit" ,precision = 13, scale = 2) // scale quantidade de numeros colocados....
	private BigDecimal limit;
	
	@Version
    private Integer version;

	public Account() {

	}

	public Account(Long id, String number, String agency, BigDecimal balance, BigDecimal limit) {
		super();
		this.id = id;
		this.number = number;
		this.agency = agency;
		this.balance = balance;
		this.limit = limit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", agency=" + agency + ", balance=" + balance + ", limit="
				+ limit + "]";
	}

}
