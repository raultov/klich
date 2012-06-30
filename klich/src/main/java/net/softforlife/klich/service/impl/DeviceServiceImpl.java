package net.softforlife.klich.service.impl;

import java.util.List;

import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.persistence.DAO.DeviceDAO;
import net.softforlife.klich.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {
	private DeviceDAO deviceDAO;
	
	@Override
	public List<Device> getDevicesByUser(Tuser user) {
		return deviceDAO.getDevicesByUser(user);
	}	

	@Override
	public Device getDeviceById(Long deviceId) {
		return deviceDAO.loadById(deviceId);
	}
	

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
