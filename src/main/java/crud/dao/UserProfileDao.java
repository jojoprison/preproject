package crud.dao;

import crud.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

    UserProfile getById(long id);

    UserProfile getByType(String type);

    List<UserProfile> getAll();
}