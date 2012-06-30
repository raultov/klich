package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.persistence.DAO.DeviceDAO;


public class DeviceDAOJPA extends GenericDAOWithJPA<Device, Long> implements DeviceDAO {

	@Override
	public int deleteDeviceById(long id) {
		String sql = "DELETE from Device d WHERE d.DEVICE_ID = :dID";
		Query query = getTransactionalEntityManager().createQuery(sql);
		query.setParameter("dID", id);
		query.executeUpdate();
		
		return 0;
	}

	@Override
	public List<Device> getAllDevices() {
		String queryString = "SELECT d FROM Device d";
		@SuppressWarnings("unchecked")
		List<Device> res = getJpaTemplate().find(queryString);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getDevicesByUser(Tuser user) {
		List<Device> listDevices;
		Map<String, Object> params = new HashMap<String, Object>(1);
		
		String queryString = "SELECT d FROM Device d WHERE d.userId = :uId order by d.deviceId";
		params.put("uId", user);
		listDevices = getJpaTemplate().findByNamedParams(queryString, params);
		
		return listDevices;
	}


}
