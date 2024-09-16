document.addEventListener('DOMContentLoaded', function() {
    // モーダルを開く関数
    function openModal(action) {
        var modal = document.getElementById("customModal");
        var submitButton = document.getElementById("modalSubmitButton");

        modal.style.display = "block";

        submitButton.onclick = function() {
            var form = document.getElementById('user-detail-form');
            if (action === 'remand') {
                form.action = '/admin/paidRequestDetail?remand';
            } else if (action === 'remove') {
                form.action = '/admin/paidRequestDetail?remove';
            }
            form.submit();
        };
    }

    // モーダルを閉じる関数
    function closeModal() {
        var modal = document.getElementById("customModal");
        modal.style.display = "none";
    }

    // 閉じるボタンのクリックイベント
    document.getElementsByClassName("close-btn")[0].onclick = closeModal;
    document.getElementById("modalCloseButton").onclick = closeModal;

    // モーダル外をクリックしたときに閉じる処理
    window.onclick = function(event) {
        var modal = document.getElementById("customModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    // グローバルに`openModal`関数を公開
    window.openModal = openModal;
});
