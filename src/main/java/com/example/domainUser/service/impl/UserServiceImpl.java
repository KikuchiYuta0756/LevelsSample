package com.example.domainUser.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domainUser.model.DepartmentEntity;
import com.example.domainUser.model.PaidEntity;
import com.example.domainUser.model.RoleEntity;
import com.example.domainUser.model.UserMapperEntity;
import com.example.domainUser.service.UserService;
import com.example.repository.UserMapperRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapperRepository usermapper;

	@Autowired
	private PasswordEncoder encoder;

	/** ユーザー登録 */
	@Override
	public void userCreate(UserMapperEntity user) {

		// パスワード暗号化
		String rawPassword = user.getPassword();
		String encryptPassword = encoder.encode(rawPassword);

		// ユーザーエンティティに暗号化されたパスワードを設定
		user.setPassword(encryptPassword);
		System.out.println("UserServiceImplのuserは" + user);

		usermapper.insertOne(user);
	}

	/** ユーザー取得（有効) */
	@Override
	public List<UserMapperEntity> findValidationUsers(UserMapperEntity user) {
		return usermapper.findValidationUsers(user);
	}

	/** ユーザー取得（無効) */
	@Override
	public List<UserMapperEntity> findNotValidationUsers(UserMapperEntity user) {
		return usermapper.findNotValidationUsers(user);
	}

	/** ユーザー取得(１件：パスワードなし) */
	@Override
	public UserMapperEntity getUserOne(String loginId) {
		return usermapper.findOne(loginId);
	}

	/** ユーザー情報取得(パスワードあり) */
	@Override
	public UserMapperEntity getFindUserOne(String loginId) {
		return usermapper.findUserOne(loginId);
	}

	/** ユーザー更新（1件） */
	@Override
	public void updateUserOne(String loginId, String password, String userName, String userNamekana, String mailAddress,
			Integer departmentId, Integer roleId, Integer validation, Integer authority, Date hire) {
		String encryptPassword = null;
		if (password != null) {
			encryptPassword = encoder.encode(password);
		}
		usermapper.updateOne(loginId, encryptPassword, userName, userNamekana, mailAddress, departmentId, roleId,
				validation, authority, hire);
	}

	/** ログインユーザパスワード更新（1件） */
	@Override
	public void updatePasswordOne(String loginId, String password) {
		String encryptPassword = encoder.encode(password);
		usermapper.updatePasswordOne(loginId, encryptPassword);
	}

	/** ユーザー削除（1件） */
	@Override
	public void deleteUserOne(String loginId) {
		usermapper.deleteOne(loginId);
	}

	/** 部署の取得 */
	@Override
	public List<DepartmentEntity> getAllDepartment() {
		return usermapper.findAll();
	}

	/** 役職の取得 */
	@Override
	public List<RoleEntity> getAllRole() {
		return usermapper.findAll2();
	}

	/** 打刻画面用 出退勤フラグの取得 */
	@Override
	public UserMapperEntity getWorkFlg(String loginId) {
		return usermapper.findWorkFlg(loginId);
	}

	/** 打刻画面用 出退勤フラグの更新（退勤ボタンを活性化） */
	@Override
	public void getWorkFlgLeaving(String loginId) {
		usermapper.updateWorkFlgLeaving(loginId);
	}

	/** 打刻画面用 出退勤フラグの更新（退勤ボタンを活性化） */
	@Override
	public void getWorkFlgAttendance(String loginId) {
		usermapper.updateWorkFlgAttendance(loginId);
	}

	/** 有給テーブルに新規ログインIDを作成 */
	public void userPaidCreate(UserMapperEntity user) {
		usermapper.insertUserPaidCreate(user);
	}

	/** 従業員に有給を付与する */
	public void giveFirstPaidDays() {
		usermapper.giveFirstPaidDays();
	}

	/** 次回以降の従業員に有給を付与する */
	public void updateGivePaidDays() {
		usermapper.updateGivePaidDays();
	};

	// 有給日数を取得
	public PaidEntity getPaidDays(String loginId) {
		return usermapper.getPaidDays(loginId);
	}

}
