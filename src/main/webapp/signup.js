
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

    const submitButton = document.getElementById("submit");

    submitButton.addEventListener("onclick", function (event) {
        //let request = new XMLHttpRequest();
        //request.onreadystatechange = function () {
        //    if (this.readyState == 4) {
        //        //do stuff
        //    }
        //};

        let username = document.getElementById("username-input").value
        const data = {
            username: document.getElementById("username-input").value,
            password: document.getElementById("password-input").value
        };

        fetch('/signup', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => console.log(data))
            .catch((error) => {
                console.error(error);
            })
    });
}