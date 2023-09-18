/*ユーザマスタ*/
 CREATE TABLE IF NOT EXISTS m_user(
 login_id VARCHAR(10) PRIMARY KEY,
password VARCHAR(50),
user_name VARCHAR(50),
user_name_kana VARCHAR(50),
mail_address VARCHAR(50),
department_id INT,
role_id INT,
gender INT,
validation INT
);
 
 /*部署マスタ*/
 CREATE TABLE IF NOT EXISTS m_department(
 department_id INT PRIMARY KEY,
department_name VARCHAR(50)
 );

 /*役職マスタ*/
 CREATE TABLE IF NOT EXISTS m_role(
 role_id INT PRIMARY KEY,
role_name VARCHAR(50)
 );
