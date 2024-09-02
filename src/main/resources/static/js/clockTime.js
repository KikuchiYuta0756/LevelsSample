document.addEventListener("DOMContentLoaded", function() {
  function clock() {
    var viewClockElement = document.getElementById("view_clock");
    if (viewClockElement) {
      viewClockElement.innerHTML = getNow();
    }

    var realtimeClockElement = document.getElementById("RealtimeClock");
    var realtimeDayElement = document.getElementById("RealtimeDay");
    if (realtimeClockElement && realtimeDayElement) {
      var now = new Date();
      var year = now.getFullYear();
      var mon = now.getMonth() + 1;
      var day = now.getDate();
      var hour = now.getHours();
      var min = now.getMinutes();
      var sec = now.getSeconds();

      // 出力用
      var s = year + "年" + mon + "月" + day + "日";
      var d = hour + "時" + min + "分" + sec + "秒";

      realtimeClockElement.innerHTML = s;
      realtimeDayElement.innerHTML = d;
    }
  }

  // 0.5秒毎にclock()を実行
  setInterval(clock, 500);
});
