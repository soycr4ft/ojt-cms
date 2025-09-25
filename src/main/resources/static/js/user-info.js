function readURL(input) {
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById("preview").src = e.target.result;
            console.log("preview src:", e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function modifyInfo(event) {
    event.preventDefault();
    const formData = new FormData(document.getElementById("userForm"));
    for (let pair of formData.entries()) {
        console.log(pair[0] + ': ' + pair[1]);
    }

    $.ajax({
        url: '/user/info-modify',
        type: 'POST',
        processData: false,
        contentType: false,
        data: formData,
        success: function(result) {
            alert("수정 완료!");
        },
        error: function(err) {
            alert("수정 실패");
        }
    });
}

function removeProfile() {
    const fileInput = document.getElementById("profileFile");
    fileInput.value = "";

    const preview = document.getElementById("preview");
    if (preview) {
        preview.src = "/images/default_profile.png";
    }
}

$(function() {
    $(document).on('click', '#withdraw', function(e) {
        const userId = $(this).data('user-id');
        console.log(userId);
        if (confirm('정말 탈퇴하시겠습니까?')) {
            $.ajax({
                url: '/user/withdraw',
                type: 'PUT',
                async: true,
                dataType: 'text',
                data: { userId: userId },
                success: function(result) {
                    alert("탈퇴 완료");
                    location.href = '/';
                },
                error: function(err) {
                    alert("탈퇴 실패");
                }
            });
        }
    });
});
