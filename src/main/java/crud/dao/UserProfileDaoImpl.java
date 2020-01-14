package crud.dao;

import crud.model.UserProfile;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Long, UserProfile> implements UserProfileDao {

    public UserProfile getById(long id) {
        return getByKey(id);
    }

    public UserProfile getByType(String type) {

        Query<UserProfile> query = getSession().createQuery("FROM UserProfile WHERE type = :type", UserProfile.class);
        query.setParameter("type", type);

        return query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserProfile> getAll() {

        return getSession().createQuery("FROM UserProfile user ORDER BY user.type").list();
    }
}