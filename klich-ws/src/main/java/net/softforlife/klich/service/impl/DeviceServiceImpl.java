package net.softforlife.klich.service.impl;

import net.softforlife.klich.persistence.DAO.DeviceDAO;
import net.softforlife.klich.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {
	private DeviceDAO deviceDAO;
	
	
	
	

	/**
	 * @param deviceDAO the deviceDAO to set
	 */
	public void setDeviceDAO(DeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	/**
	 * @return the deviceDAO
	 */
	public DeviceDAO getDeviceDAO() {
		return deviceDAO;
	}
}
