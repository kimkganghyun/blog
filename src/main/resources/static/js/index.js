document.addEventListener("DOMContentLoaded", function() {
    checkUserSession();
});

async function checkUserSession() {
    const token = getCookie("token");
    if (token) {
        // 서버에 사용자 정보를 요청하거나 쿠키를 통해 사용자 정보를 가져옴
        const response = await fetch("/api/users/me", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (response.ok) {
            const user = await response.json();
            showUserMenu(user);
        } else {
            console.error("Failed to fetch user session");
        }
    }
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function showUserMenu(user) {
    document.getElementById("authButtons").style.display = "none";
    document.getElementById("userMenu").style.display = "block";
    document.getElementById("usernameDisplay").textContent = user.name;
}

async function logout() {
    try {
        const response = await fetch("/api/users/logout", {
            method: "POST"
        });

        console.log(`Logout response:`, response);

        if (!response.ok) {
            throw new Error("로그아웃 실패");
        }

        Swal.fire({
            icon: 'success',
            title: '로그아웃 성공',
        }).then(() => {
            window.location.href = '/login';
        });

    } catch (error) {
        console.error("Error during logout:", error);
        Swal.fire({
            icon: 'error',
            title: '로그아웃 실패',
            text: error.message,
        });
    }
}
