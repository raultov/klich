package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Tuser;

public interface DeviceDAO extends GenericDAO<Device, Long> {

	public int deleteDeviceById(long id);
	
	public List<Device> getAllDevices();
	
	public void update(Device t);
	
	public List<Device> getDevicesByUser(Tuser userId);
}
