async function login() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    console.log(`Logging in user: ${username}`);

    try {
        if (!username || !password) {
            Swal.fire({
                icon: 'error',
                title: '오류',
                text: 'ID와 PW를 입력하세요.',
            });
            return;
        }

        Swal.fire({
            title: '로그인 중...',
            allowOutsideClick: false,
            didOpen: () => {
                Swal.showLoading();
            }
        });

        const response = await fetch("/api/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name: username, password: password })
        });

        console.log(`Login response:`, response);

        if (!response.ok) {
            const errorResponse = await response.json();
            throw new Error(errorResponse.message);
        }

        const data = await response.json();
        console.log(`Login successful:`, data);

        Swal.fire({
            icon: 'success',
            title: '로그인 성공',
        }).then(() => {
            window.location.href = '/';
        });

    } catch (error) {
        console.error("Error during login:", error);
        Swal.fire({
            icon: 'error',
            title: '로그인 실패',
            text: error.message,
        });
    }
}
