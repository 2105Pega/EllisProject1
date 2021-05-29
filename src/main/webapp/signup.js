
document.body.onload = function () {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })

    const passwordConfirm = document.getElementById("password-confirm");

    passwordConfirm.addEventListener("input", function (event) {
        let password = document.getElementById("password-input").value;
        if (event.currentTarget.value === password) {
            passwordConfirm.setCustomValidity("");
        } else {
            passwordConfirm.setCustomValidity("passwords do not match");
        }
    });
}