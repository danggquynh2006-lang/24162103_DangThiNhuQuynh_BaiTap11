package vn.iotstar.services;
import vn.iotstar.entity.User_24162103;

public interface IUserService_24162103 {
    User_24162103 login(String username, String password);
    boolean exists(String username);
    void register(User_24162103 user);
}