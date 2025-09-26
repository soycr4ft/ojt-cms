document.addEventListener("DOMContentLoaded", function () {
	const btnAdd = document.getElementById("btnAddUniv");
	const container = document.getElementById("university-container");
	const template = document.getElementById("university-template");
	const hsStatus = document.getElementById("highschoolStatus");

	// ---------- [ 대학 추가 버튼 제어 ] ----------
	function toggleUnivButton() {
	  const value = hsStatus.value;
	  if (value === "졸업" || value === "졸업예정") {
	    btnAdd.disabled = false;
	    btnAdd.classList.remove("btn-secondary");
	    btnAdd.classList.add("btn-primary");
	  } else {
	    btnAdd.disabled = true;
	    btnAdd.classList.remove("btn-primary");
	    btnAdd.classList.add("btn-secondary");
	    container.innerHTML = ""; // ⚠️ 대학 입력값도 초기화
	  }
	}

	// 초기 상태
	toggleUnivButton();
	hsStatus.addEventListener("change", toggleUnivButton);
	// ---------- [ 대학 추가 버튼 클릭 ] ----------
	btnAdd.addEventListener("click", () => {
	  const clone = template.cloneNode(true);
	  clone.classList.remove("d-none");
	  clone.removeAttribute("id");
	  container.appendChild(clone);

	  // 삭제 버튼 이벤트 연결
	  const removeBtn = clone.querySelector("button.btn-danger");
	  removeBtn.addEventListener("click", () => {
	    if (confirm("정말 삭제하시겠습니까? 삭제된 내역은 복구할 수 없습니다.")) {
	      clone.remove();
	    }
	  });
	});
	
	// ---------- [ 학교 자동완성 (NEIS API) ] ----------
	const citySelect = document.querySelector("#citySelect");
	const schoolInput = document.querySelector("#schoolInput");
	const list = document.getElementById("highSchoolList");

	async function loadSchools() {
	  const city = citySelect.value;
	  const name = schoolInput.value.trim();
	  if (name.length < 2) return; // 두 글자 이상일 때만 검색

	  let url = `/api/school/high?city=${city}&name=${encodeURIComponent(name)}`;

	  try {
	    const res = await fetch(url);
	    if (!res.ok) throw new Error("HTTP " + res.status);
	    const data = await res.json();

	    // datalist 초기화
	    list.innerHTML = "";

	    if (!Array.isArray(data) || data.length === 0) return;

	    data.forEach((school) => {
	      const option = document.createElement("option");
	      option.value = school.SCHUL_NM || school.name;
	      list.appendChild(option);
	    });
	  } catch (err) {
	    console.error("학교 데이터 로드 실패:", err);
	  }
	}

	citySelect.addEventListener("change", () => {
	  schoolInput.value = "";
	  list.innerHTML = "";
	});

	schoolInput.addEventListener("input", loadSchools);
		
});



/*	document.addEventListener("DOMContentLoaded", () => {
	  const citySelect = document.querySelector("#citySelect");
	  const schoolInput = document.querySelector("#schoolInput");
	  const list = document.getElementById("highSchoolList");

	  async function loadSchools() {
	    const city = citySelect.value;
	    const name = schoolInput.value.trim();
	    if (name.length < 2) return; // 두 글자 이상일 때만 검색

	    let url = `/api/school/high?city=${city}&name=${encodeURIComponent(name)}`;

	    try {
	      const res = await fetch(url);
	      if (!res.ok) throw new Error("HTTP " + res.status);
	      const data = await res.json();

	//      console.log("API 응답:", data); // ← 응답 확인용
	
	      // datalist 초기화
	      list.innerHTML = "";

	      // 응답 배열이 비었을 경우
	      if (!Array.isArray(data) || data.length === 0) return;

	      // datalist 채우기
	      data.forEach(school => {
	        const option = document.createElement("option");
	        // 백엔드에서 내려주는 키 확인 (SCHUL_NM or name)
	        option.value = school.SCHUL_NM || school.name;
	        list.appendChild(option);
	      });

	    } catch (err) {
	      console.error("학교 데이터 로드 실패:", err);
	    }
	  }

	  // 이벤트 연결
	  citySelect.addEventListener("change", () => {
	    schoolInput.value = "";
	    list.innerHTML = "";
	  });

	  schoolInput.addEventListener("input", loadSchools);
	});*/