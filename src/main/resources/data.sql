/*ユーザーマスタ*/
INSERT INTO m_user(
login_id
, password
, user_name
, user_name_kana
, mail_address
, department_id
, role_id
, gender
, validation
)VALUES
('0000010000', 'password', 'システム管理者', 'システムカンリシャ', 'system@co.jp', '1', '1', '1', '1'),
('0000020000', 'password', 'ユーザー１', 'ユーザーイチ', 'user@co.jp', '2', '2', '2', '2');

/*部署マスタ*/
INSERT INTO m_department(
department_id, department_name
)VALUES
(1,'システム管理部'),
(2,'営業部');

/*役職マスタ*/
INSERT INTO m_role(
role_id
, role_name
)VALUES
(1,'Admin'),
(2,'一般ユーザー');
