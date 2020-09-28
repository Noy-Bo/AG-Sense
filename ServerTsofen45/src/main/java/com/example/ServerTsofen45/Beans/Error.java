package com.example.ServerTsofen45.Beans;


import java.beans.Transient;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import Enums.Severity;


@Entity
@Table(name = "errors")
public class Error {

	
	private int Code;
	private String message;
	private Severity severity;
	
	
	public void setCode(int code) {
		Code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	
	@Column
	@Id
	public int getCode() {
		return Code;
	}
	@Column
	public String getMessage() {
		return message;
	}
	@Column
	public Severity getSeverity() {
		return severity;
	}
	@Override
	public String toString() {
		return "Error [Code=" + Code + ", message=" + message + "]";
	}
	
	
	
	
	
	
	
}