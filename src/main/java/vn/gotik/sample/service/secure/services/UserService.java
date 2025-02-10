package vn.gotik.sample.service.secure.services;

public interface UserService {

    void createUser(String username, String password);

    void deleteUser(String username);

    void updateUser(String username, String password);

    void getUser(String username);

    void getUsers();
}
