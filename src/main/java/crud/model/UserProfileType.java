package crud.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable {

    USER("USER"),
    ADMIN("ADMIN");

    String userProfileType;

    UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserRoleType() {
        return userProfileType;
    }
}