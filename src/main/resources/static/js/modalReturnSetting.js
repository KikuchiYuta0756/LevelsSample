document.addEventListener('DOMContentLoaded', function() {
	function setFormAction(action) {
		document.getElementById('modalSubmitButton').onclick = function() {
			var form = document.getElementById('user-detail-form');
			if (action === 'remand') {
				form.action = '/admin/paidRequestDetail?remand';
			} else if (action === 'remove') {
				form.action = '/admin/paidRequestDetail?remove';
			}
			form.submit();
		};
	}
	// グローバルに`setFormAction`関数を公開
	window.setFormAction = setFormAction;
});
