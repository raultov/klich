package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.List;

import net.softforlife.klich.model.Device;
import net.softforlife.klich.persistence.DAO.DeviceDAO;


public class DeviceDAOJPA extends GenericDAOWithJPA<Device, Integer> implements DeviceDAO {

	@Override
	public int deleteDeviceById(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Device> getAllDevices() {
		// TODO Auto-generated method stub
		return null;
	}


}
