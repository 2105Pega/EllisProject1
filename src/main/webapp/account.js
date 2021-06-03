var accountData = null;

document.body.onload = function () {
    navbar();
    var urlParams = new URLSearchParams(window.location.search);
    let id = urlParams.get('id');
    document.cookie = "accountid=" + id;
    fetch('account?id=' + id, { method: 'GET' })
        .then(response => response.json())
        .then(data => processData(data))
        .catch((error) => {
            console.error('Error:', error);
        });
}

async function processData(jsonObject) {
    let accountHolders = jsonObject.accountHolders;
    let accountName = await getAccountName(getCookieValue("accountid"));
    document.getElementById("account-name").innerHTML = accountName;
    let transactions = jsonObject.transactions;
    let tabledata = "";
    for (let i = 0; i < transactions.length; i++) {
        let transaction = transactions[i];
        transaction.destinationId = await getAccountName(transaction.destinationId);
        tabledata = tabledata + "<tr><td>$" +
            transaction.amount + "</td><td>" +
            transaction.transactionType + "</td><td>" +
            transaction.destinationId + "</td></tr>";
    }
    document.getElementById("transaction-table").innerHTML = tabledata;

}

function transaction(event) {
    let type = document.getElementById("transaction-selector").value;
    /*
    switch (type) {
        case "WITHDRAW":
            type = 0;
            break;
        case "DEPOSIT":
            type = 1;
            break;
        case "TRANSFER":
            type = 2;
    };*/
    let amount = parseFloat(document.getElementById("amount").value);
    let id = getCookieValue('accountid');
    let destinationId = document.getElementById('destination').value;
    destinationId = parseInt(destinationId) || 0;
    let data = { amount: amount, transactionType: type, accountId: id, destinationId: destinationId };

    fetch('transaction?id=' + id,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
}

function select() {
    if (document.getElementById("transaction-selector").value == "TRANSFER") {
        document.getElementById('destination').style.display = 'inline';
        document.getElementById('destination-label').style.display = 'inline';
    } else {
        document.getElementById('destination').style.display = 'none';
        document.getElementById('destination-label').style.display = 'none';
    }
}

async function getAccountName(id) {
    if (!accountData) {
        accountData = await fetch('accounts', { method: 'GET' })
            .then(response => response.json())
            .then(data => {
                return data;
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
    for (const name in accountData) {
        account = accountData[name];
        if (account.id == id) {
            return account.name;
        }
    }
    return "N/A";
}

const getCookieValue = (name) => (
    document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)')?.pop() || ''
)