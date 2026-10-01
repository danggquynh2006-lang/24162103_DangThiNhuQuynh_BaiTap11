package vn.iotstar.services.impl;
import vn.iotstar.dao.UserDao_24162103;
import vn.iotstar.entity.User_24162103;
import vn.iotstar.services.IUserService_24162103;

public class UserServiceImpl_24162103 implements IUserService_24162103 {
    private UserDao_24162103 userDao = new UserDao_24162103();

    @Override
    public User_24162103 login(String username, String password) {
        User_24162103 user = userDao.findById(username);
        if (user != null && user.isActive() && user.getPassword() != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public boolean exists(String username) {
        return userDao.findById(username) != null;
    }

    @Override
    public void register(User_24162103 user) {
        userDao.insert(user);
    }
}