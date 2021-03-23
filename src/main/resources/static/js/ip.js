function getIpInfo(btn) {
    document.getElementById('table_list').innerHTML = '';
    if (btn) btn.innerText = '查询中...';

    let param = document.getElementById('input_ip').value;
    if (!param) {
        return
    }

    fetch('/api/ip/' + param)
        .then(res => res.json())
        .then(function (res) {
            let table_list = '';
            table_list += '<table class="table striped fullwidth mt-2"><tbody>';
            let data = res['data'];
            for (let key in data) {
                let value = data[key];
                if (value !== null) {
                    table_list += `<tr><th>${key}</th><td>${value}</td></tr>`
                }
            }
            table_list += '</tbody></table>';
            document.getElementById('table_list').innerHTML = table_list;
        }).catch(e => alert(e))
        .finally(function () {
            if (btn) btn.innerText = '查询'
        });
}

getIpInfo();

document.getElementById('input_ip').addEventListener('keydown', function (event) {
    if (event.keyCode === 13) {
        getIpInfo()
    }
});