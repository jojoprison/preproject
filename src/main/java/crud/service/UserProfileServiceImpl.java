package crud.service;

import crud.dao.UserProfileDao;
import crud.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    UserProfileDao dao;

    public UserProfile getById(int id) {
        return dao.getById(id);
    }

    public UserProfile getByType(String type){
        return dao.getByType(type);
    }

    public List<UserProfile> getAll() {
        return dao.getAll();
    }
}