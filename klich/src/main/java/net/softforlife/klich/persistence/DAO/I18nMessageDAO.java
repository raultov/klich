package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.I18nMessage;

/**
 * La clase AnswerTypeDAO.
 */
public interface I18nMessageDAO extends GenericDAO<I18nMessage, Integer> {

    List<I18nMessage> getI18nMessageList (String lkey);



}
