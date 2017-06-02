package com.highway.model;

/**
 * NodeMapping entity. @author MyEclipse Persistence Tools
 */
public class NodeMapping extends AbstractNodeMapping implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NodeMapping() {
	}

	/** minimal constructor */
	public NodeMapping(String TSurvnodename, String queryCode, String openCode,
			String closeCode, String lockCode, String unlockCode) {
		super(TSurvnodename, queryCode, openCode, closeCode, lockCode,
				unlockCode);
	}

	/** full constructor */
	public NodeMapping(String TSurvnodename, String type, String queryCode,
			String openCode, String closeCode, String lockCode,
			String unlockCode) {
		super(TSurvnodename, type, queryCode, openCode, closeCode, lockCode,
				unlockCode);
	}

}
