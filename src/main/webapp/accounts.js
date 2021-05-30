document.body.onload = function () {
    navbar();
    
    fetch('accounts', { method: 'GET' })
        .then(response => response.json())
        .then(data => processAccounts(data))
        .catch((error) => {
            console.error('Error:', error);
        });
}

function processAccounts(accounts) {
    console.log(accounts);
    let tabledata = "";
    for (const value in accounts) {
        var account = accounts[value];
        tabledata = tabledata + "<tr><td>" +
            account.name + "</td><td>$" +
            account.balance + "</td><td>" +
            account.status + "</td></tr>";
    }
    document.getElementById("accounts-table").innerHTML = tabledata;

}