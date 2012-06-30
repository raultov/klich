package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Device;

public interface DeviceDAO extends GenericDAO<Device, Integer> {

	public int deleteDeviceById(long id);
	
	public List<Device> getAllDevices();
	
	public void update(Device t);
}
