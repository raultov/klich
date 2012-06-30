package net.softforlife.klich.service;

import java.util.List;

import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Tuser;

import org.springframework.transaction.annotation.Transactional;

@Transactional  
public interface DeviceService {
	public List<Device> getDevicesByUser(Tuser user);
	
	public Device getDeviceById(Long deviceId);
};


