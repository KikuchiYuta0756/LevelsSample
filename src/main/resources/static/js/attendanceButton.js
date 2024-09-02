document.addEventListener("DOMContentLoaded", function() {

// HTMLから値を取得
var workFlgAttendanceValue = document.getElementById("workFlg").value;

// 出勤ボタン　値が10ならボタンを活性化、値が20なら非活性化
if (workFlgAttendanceValue === "10") {
	document.getElementById("attendanceButton").disabled = false;
} else if (workFlgAttendanceValue === "20") {
	document.getElementById("attendanceButton").disabled = true;
}

// HTMLから値を取得
var workFlgLeavingValue = document.getElementById("workFlg").value;

// 退勤ボタン　値が20ならボタンを活性化、値が10なら非活性化
if (workFlgLeavingValue === "20") {
	document.getElementById("leavingButton").disabled = false;
} else if (workFlgLeavingValue === "10") {
	document.getElementById("leavingButton").disabled = true;
}
});