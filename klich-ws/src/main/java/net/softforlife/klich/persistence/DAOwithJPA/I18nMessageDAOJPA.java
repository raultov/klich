package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.softforlife.klich.model.I18nMessage;
import net.softforlife.klich.persistence.DAO.I18nMessageDAO;

import org.springframework.stereotype.Repository;

/**
 * La clase AnswerTypeDAOJPA.
 */
@Repository
public class I18nMessageDAOJPA extends GenericDAOWithJPA<I18nMessage, Integer> implements
        I18nMessageDAO {

    @Override
    public List<I18nMessage> getI18nMessageList(String lkey) {
        StringBuilder strQuery = new StringBuilder();
        strQuery.append("select msg from  I18nMessage msg ");
        strQuery.append (" where msg.lkey = :lkey ");


        Map<String, Object> params = new HashMap<String, Object>(0);
        params.put("lkey",lkey);

        @SuppressWarnings("unchecked")
		List<I18nMessage> result = getJpaTemplate().findByNamedParams(
                strQuery.toString(), params);

        return result;
    }
}