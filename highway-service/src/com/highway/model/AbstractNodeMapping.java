package com.highway.model;

/**
 * AbstractNodeMapping entity provides the base persistence definition of the
 * NodeMapping entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNodeMapping implements java.io.Serializable {

	// Fields

	private String TSurvnodename;
	private String type;
	private String queryCode;
	private String openCode;
	private String closeCode;
	private String lockCode;
	private String unlockCode;

	// Constructors

	/** default constructor */
	public AbstractNodeMapping() {
	}

	/** minimal constructor */
	public AbstractNodeMapping(String TSurvnodename, String queryCode,
			String openCode, String closeCode, String lockCode,
			String unlockCode) {
		this.TSurvnodename = TSurvnodename;
		this.queryCode = queryCode;
		this.openCode = openCode;
		this.closeCode = closeCode;
		this.lockCode = lockCode;
		this.unlockCode = unlockCode;
	}

	/** full constructor */
	public AbstractNodeMapping(String TSurvnodename, String type,
			String queryCode, String openCode, String closeCode,
			String lockCode, String unlockCode) {
		this.TSurvnodename = TSurvnodename;
		this.type = type;
		this.queryCode = queryCode;
		this.openCode = openCode;
		this.closeCode = closeCode;
		this.lockCode = lockCode;
		this.unlockCode = unlockCode;
	}

	// Property accessors

	public String getTSurvnodename() {
		return this.TSurvnodename;
	}

	public void setTSurvnodename(String TSurvnodename) {
		this.TSurvnodename = TSurvnodename;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQueryCode() {
		return this.queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getOpenCode() {
		return this.openCode;
	}

	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}

	public String getCloseCode() {
		return this.closeCode;
	}

	public void setCloseCode(String closeCode) {
		this.closeCode = closeCode;
	}

	public String getLockCode() {
		return this.lockCode;
	}

	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}

	public String getUnlockCode() {
		return this.unlockCode;
	}

	public void setUnlockCode(String unlockCode) {
		this.unlockCode = unlockCode;
	}

}