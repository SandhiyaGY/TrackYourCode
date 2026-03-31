function showLogin() {
    document.getElementById("login").style.display = "block";
    document.getElementById("signup").style.display = "none";

    document.querySelectorAll(".tabs button")[0].classList.add("active");
    document.querySelectorAll(".tabs button")[1].classList.remove("active");
}

function showSignup() {
    document.getElementById("login").style.display = "none";
    document.getElementById("signup").style.display = "block";

    document.querySelectorAll(".tabs button")[1].classList.add("active");
    document.querySelectorAll(".tabs button")[0].classList.remove("active");
}

/* Show Password */
function togglePassword(id) {
    let input = document.getElementById(id);
    input.type = (input.type === "password") ? "text" : "password";
}