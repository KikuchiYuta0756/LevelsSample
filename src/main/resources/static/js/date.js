setInterval('clock()', 500); //0.5秒毎にclock()を実行

function clock() {
	var now = new Date();
	var year = now.getFullYear();
	var mon = now.getMonth();
	var day = now.getDate();
	var hour = now.getHours();
	var min = now.getMinutes();
	var sec = now.getSeconds();

	//出力用
	var s = year + "年" + mon + "月" + day + "日";
	document.getElementById("RealtimeClock").innerHTML = s;
	var d = hour + "時" + min + "分" + sec + "秒";
	document.getElementById("RealtimeDay").innerHTML = d;
}