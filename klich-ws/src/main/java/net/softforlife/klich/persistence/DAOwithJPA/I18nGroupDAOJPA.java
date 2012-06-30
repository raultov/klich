package net.softforlife.klich.persistence.DAOwithJPA;

import net.softforlife.klich.model.I18nGroup;
import net.softforlife.klich.persistence.DAO.I18nGroupDAO;

import org.springframework.stereotype.Repository;

@Repository
public class I18nGroupDAOJPA extends GenericDAOWithJPA<I18nGroup, Integer> implements
		I18nGroupDAO {

}