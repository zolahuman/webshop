package model.services;

import model.User;
import model.dao.userDAO;
import model.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userServices {
    private userDAO userDao = new userDAO();

    // Register a new user
    public boolean registerUser(UserDTO userDTO) throws SQLException {
        User user = convertToEntity(userDTO);
        return userDao.addUser(user);
    }

    public boolean addUser(UserDTO userDTO) throws SQLException {
        User user = convertToEntity(userDTO);
        return userDao.addUser(user);
    }

    // Login logic
    public UserDTO login(String username, String password) throws SQLException {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return convertToDTO(user);
        } else {
            throw new SQLException("Invalid username or password");
        }
    }

    // Get user by username
    public UserDTO getUserByUsername(String username) throws SQLException {
        User user = userDao.getUserByUsername(username);
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user :users ){
            usersDTO.add(convertToDTO(user));
        }
        return usersDTO;
    }

    public boolean updateUser(UserDTO userDTO) throws SQLException {
        return userDao.updateUser(convertToEntity(userDTO));
    }

    // Convert Entity to DTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }

    // Convert DTO to Entity
    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }


}