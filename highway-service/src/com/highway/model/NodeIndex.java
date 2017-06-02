package com.highway.model;

/**
 * NodeIndex entity. @author MyEclipse Persistence Tools
 */
public class NodeIndex extends AbstractNodeIndex implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NodeIndex() {
	}

	/** full constructor */
	public NodeIndex(String nodeName, Integer RIndex, Integer AIndex) {
		super(nodeName, RIndex, AIndex);
	}

}
