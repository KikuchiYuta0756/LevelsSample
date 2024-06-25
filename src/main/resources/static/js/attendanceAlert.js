//打刻忘れのアラートポップアップ表示
//現在時刻を取得
const nowTime = new Date();

//出勤提示の設定
const fixedTime = new Date();
fixedTime.setHours(9, 0, 0, 0);

// 自動アラート表示用のタイマー設定
setTimeout(() => {
	// 現在時刻が午前9時以降かつ出勤ボタンがまだ押されていない場合にアラートを表示
	if (nowTime => fixedTime) {
		var workFlgLeavingValue = document.getElementById("workFlg").value;
		if (workFlgLeavingValue === "10") {
			alert('出勤時刻を忘れています。今すぐ打刻してください');
		}
	}
}, fixedTime - nowTime);
