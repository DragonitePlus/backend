package org.example.backend.service.doctor;

import java.util.List;
import org.example.backend.entity.doctor.Doctor;

public interface DoctorService {

  Doctor selectById(String doctorId);

  List<Doctor> selectAll();

  String insert(Doctor doctor);

  boolean update(Doctor doctor);

  boolean delete(String doctorId);

  boolean banAccount(String doctorId);

  boolean activeAccount(String doctorId);

  boolean updatePassword(String doctorId, String newPassword);

  String generateRegisterCode(String email);

  String generateRegisterCode(String email,char c);

  boolean registerDoctor(Doctor doctor);

  boolean validatePassword(String doctorId, String password);

  boolean validateRegisterCode(String email, String registerCode);

  String loginByEmail(String email, String password);

  String loginByUsername(String username, String password);

  String isUsernameExist(String username);

  String isUsernameExist(String doctorId, String username);

  String isEmailExist(String email);

  String getAvatarBase64(String doctorId);

  String getDoctorAvatar(String doctorId);

  int selectDoctorCount();

  int selectMyDoctorCount(String adminId);

  Doctor selectDoctorByEmail(String email);

  List<Doctor> selectDoctorByCondition(String queryString, String adminId, int currentPage, int pageSize);
}
