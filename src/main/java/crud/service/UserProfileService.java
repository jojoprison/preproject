package crud.service;

import crud.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile getById(int id);

    UserProfile getByType(String type);

    List<UserProfile> getAll();
}