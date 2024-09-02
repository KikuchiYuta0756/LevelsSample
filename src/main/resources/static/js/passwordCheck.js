var password = document.getElementById("password");
var confirm_password = document.getElementById("confirm_password");

function validatePassword() {
	
	//パスワードフィールドと一致しない場合の処理
	if (password.value != confirm_password.value) {
		confirm_password.setCustomValidity("パスワードが一致しません");
	} else {
		confirm_password.setCustomValidity('');
	}
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
