package net.softforlife.klich.enumeration;

import net.softforlife.klich.model.Status;

/**
 * The Enum CLAVE_GEOPOINT.
 * 
 * @author rtovar
 */
public enum CLAVE_GEOPOINT implements GenericEnum<Status> {
	GPS(1, "GPS", false, false),
	NETWORK(2, "NET", false, false),
	ANTENNAS(3, "ANT", false, false),
	SHYHOOK(4, "SHY", false, false);
	
	/** El id. */
	private int id;

	/** El code. */
	private String code;

	/** El internal. */
	private boolean internal;

	/** El final state. */
	private boolean finalState;
	
	/**
	 * Constructor de la clase CLAVE_PERSONA.
	 * 
	 * @param id
	 *            the id
	 * @param lkey
	 *            the lkey
	 * @param internal
	 *            the internal
	 * @param finalState
	 *            the final state
	 */
	private CLAVE_GEOPOINT(int id, String lkey, boolean internal,
			boolean finalState) {
		this.setId(id);
		this.setCode(lkey);
		this.setInternal(internal);
		this.setFinalState(finalState);
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public Status getObject() {
		return new Status(id, code, internal, finalState);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param internal the internal to set
	 */
	public void setInternal(boolean internal) {
		this.internal = internal;
	}

	/**
	 * @return the internal
	 */
	public boolean isInternal() {
		return internal;
	}

	/**
	 * @param finalState the finalState to set
	 */
	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}

	/**
	 * @return the finalState
	 */
	public boolean isFinalState() {
		return finalState;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return code;
	}
	
	public static Status parse(String s) {
		Status status = null;
		Integer statusId = Integer.parseInt(s);
		
		switch(statusId) {
			case 1:
				status = new Status(1, "GPS", false, false);
			break;
			case 2:
				status = new Status(2, "NET", false, false);
			break;	
			case 3:
				status = new Status(3, "ANT", false, false);
			break;	
			case 4:
				status = new Status(4, "SHY", false, false);
			break;	
		}
		
		return status;
	}
	
}
