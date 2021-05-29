document.body.onload = function () {
    fetch('accounts', { method: 'GET' })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch((error) => {
            console.error('Error:', error);
        });
}

function processAccounts(accounts) {
    console.log(accounts);
}