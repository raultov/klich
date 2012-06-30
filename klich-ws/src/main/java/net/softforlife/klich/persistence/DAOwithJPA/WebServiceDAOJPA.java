package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.persistence.DAO.WebServiceDAO;

public class WebServiceDAOJPA extends GenericDAOWithJPA<Device, Integer> implements WebServiceDAO {

	@Override
	public Device getDevice(Long idDevice) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        params.put("idDevice", idDevice);
        @SuppressWarnings("unchecked")
		List<Device> devices = getJpaTemplate().findByNamedParams("SELECT d FROM Device d WHERE d.deviceId = :idDevice", params);

        if (devices.size() == 0) {
            return null;
        }

        return devices.get(0);
	}

	@Override
	public List<Device> getListDevicesUser(Tuser userId) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        params.put("userId", userId.getUserId());
        StringBuilder strQuery = new StringBuilder();
        strQuery.append("select d from Device d where d.userId.userId = :userId");
        strQuery.append(" ORDER BY d.deviceId");
        
        @SuppressWarnings("unchecked")
		List<Device> devices = getJpaTemplate().findByNamedParams(strQuery.toString(), params);

        return devices;
	}


	@Override
	public List<Track> getTracksToUnloadDevice() {
        @SuppressWarnings("unchecked")
		List<Track> ltrack1 = getJpaTemplate().find("SELECT t FROM Track t");

        return ltrack1;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getListOrderById() {
        return this.getJpaTemplate().find("select d from Device t order by deviceId");
	}

}
