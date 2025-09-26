document.addEventListener("DOMContentLoaded", function () {
  const btnAdd = document.getElementById("btnAddUniv");
  const container = document.getElementById("university-container");
  const template = document.getElementById("university-template");

  btnAdd.addEventListener("click", () => {
    // 템플릿 복사
    const clone = template.cloneNode(true);
    clone.classList.remove("d-none");
    clone.removeAttribute("id"); 
    container.appendChild(clone);

    // 삭제 버튼 이벤트 연결
	const removeBtn = clone.querySelector("button.btn-danger");
	removeBtn.addEventListener("click", () => {
	  clone.remove();
	});
  });
});
