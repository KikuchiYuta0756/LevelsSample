document.addEventListener('DOMContentLoaded', function() {
    // CSV出力ボタンの処理
    document.getElementById("popupButton").addEventListener("click", function(event) {
        event.preventDefault(); // フォームのデフォルトの送信防止

        document.getElementById("overlay").style.display = "block";
        document.getElementById("popup").style.display = "block";

        setTimeout(function() {
            document.getElementById("overlay").style.display = "none";
            document.getElementById("popup").style.display = "none";
        }, 2000); // 2秒後にポップアップを自動的に閉じる
    });

    // ポップアップの閉じるボタンの処理
    document.getElementById("closeButton").addEventListener("click", function() {
        document.getElementById("overlay").style.display = "none";
        document.getElementById("popup").style.display = "none";
    });

    // CSVフォームの送信処理
    document.getElementById('csvForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var data = document.getElementById('clockListDate').value;
        generateCSV(data);
    });

    function generateCSV(data) {
        // Ajaxやサーブレットを使ってサーバーにデータを送信し、CSVファイルを生成する処理を記述
    }
});
