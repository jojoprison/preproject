package crud.dao;

import crud.model.PersistentLogin;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
        implements PersistentTokenRepository {

    static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

    @Override
    public void createNewToken(PersistentRememberMeToken token) {

        logger.info("Creating Token for user : {}", token.getUsername());

        PersistentLogin persistentLogin = new PersistentLogin();

        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());

        persist(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {

        logger.info("Fetch Token if any for seriesId : {}", seriesId);

        try {

            Query<PersistentLogin> query = getSession().createQuery("FROM PersistentLogin WHERE series = :seriesId", PersistentLogin.class);
            query.setParameter("seriesId", seriesId);

            PersistentLogin persistentLogin = query.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());

        } catch (Exception e) {

            logger.info("Token not found...");

            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {

        logger.info("Removing Token if any for user : {}", username);

        Query<PersistentLogin> query = getSession().createQuery("FROM PersistentLogin WHERE username = :username", PersistentLogin.class);
        query.setParameter("username", username);

        PersistentLogin persistentLogin = query.uniqueResult();

        if (persistentLogin != null) {
            logger.info("rememberMe was selected");
            delete(persistentLogin);
        }
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {

        logger.info("Updating Token for seriesId : {}", seriesId);

        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);

        update(persistentLogin);
    }
}