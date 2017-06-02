package com.highway.model;

/**
 * NodeLog entity. @author MyEclipse Persistence Tools
 */
public class NodeLog extends AbstractNodeLog implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public NodeLog() {
	}

	/** full constructor */
	public NodeLog(String time, String TSurvnodename, String result) {
		super(time, TSurvnodename, result);
	}

}
