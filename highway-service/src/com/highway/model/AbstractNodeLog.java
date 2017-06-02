package com.highway.model;

/**
 * AbstractNodeLog entity provides the base persistence definition of the
 * NodeLog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNodeLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String time;
	private String TSurvnodename;
	private String result;

	// Constructors

	/** default constructor */
	public AbstractNodeLog() {
	}

	/** full constructor */
	public AbstractNodeLog(String time, String TSurvnodename, String result) {
		this.time = time;
		this.TSurvnodename = TSurvnodename;
		this.result = result;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTSurvnodename() {
		return this.TSurvnodename;
	}

	public void setTSurvnodename(String TSurvnodename) {
		this.TSurvnodename = TSurvnodename;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}