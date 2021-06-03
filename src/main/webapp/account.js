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
    let accountBalance = await getAccountBalance(getCookieValue("accountid"));
    document.getElementById("account-name").innerHTML = accountName;
    document.getElementById("account-balance").innerHTML = "Balance $" + accountBalance;
    document.getElementById("account-holders").innerHTML = "Account Holders: " + accountHolders.join(" ")
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

async function transaction(event) {
    let type = document.getElementById("transaction-selector").value;
    let amount = parseFloat(document.getElementById("amount").value);
    let id = getCookieValue('accountid');
    let destinationId = null;
    if (type == "TRANSFER") {
        destinationId = await getAccountId(document.getElementById('destination').value);
    }
    destinationId = parseInt(destinationId) || 0;
    let data = { amount: amount, transactionType: type, accountId: id, destinationId: destinationId };

    fetch('transaction?id=' + id,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(response => location.reload())
        .then(data => location.reload());
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

async function getAccountBalance(id) {
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
            return account.balance;
        }
    }
    return "N/A";
}

async function getAccountId(name) {
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
    return accountData[name].id;
}

const getCookieValue = (name) => (
    document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)')?.pop() || ''
)