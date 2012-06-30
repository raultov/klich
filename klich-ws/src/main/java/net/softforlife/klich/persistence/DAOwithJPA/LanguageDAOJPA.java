package net.softforlife.klich.persistence.DAOwithJPA;

import net.softforlife.klich.model.Language;
import net.softforlife.klich.persistence.DAO.LanguageDAO;

import org.springframework.stereotype.Repository;

/**
 * La clase LanguageDAOJPA.
 */
@Repository
public class LanguageDAOJPA extends GenericDAOWithJPA<Language, Integer> implements LanguageDAO {

}