function modifyPassword(event) {
	event.preventDefault();
  fetch('/user/modify-password', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({
      userId: document.getElementById('userId').value,
      password: document.getElementById('currentPassword').value,
	  newPassword: document.getElementById('newPassword').value,
	  confirmPassword: document.getElementById('confirmPassword').value
    })
  })
  .then(res => res.json())
  .then(data => {
    if (data.success) {
		alert("비밀번호 변경이 완료되었습니다. 다시 로그인해주세요.");
		location.href="/";
    } else {
		alert("비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
      document.getElementById('errorMessage').innerText = data.message;
    }
  })
}