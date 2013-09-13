package com.tinydoit.dao;

import com.tinydoit.domain.User;

public interface InterfaceUserDao {
	// ͨ���û�����ȡ�û���Ϣ
	public User getUserByUsername(String Username);

	// ͨ���û�ID����û�
	public User getUserByID(String ID);

	// �����û�����������е�¼��֤
	public boolean LoginByUser(String Username, String Password);

	// ����û�
	public boolean addUser(String Username, String Password);

	// ͨ���û��������û�����
	public boolean updateUserPasswordByUsername(String Username, String Password);

	// ͨ���û���ɾ���û�
	public boolean deleteUserByUsername(String Username);
}
