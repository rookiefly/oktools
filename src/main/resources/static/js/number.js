function convert(num) {
    let type = parseInt(document.getElementById('select_type').value);
    let decimal = num | parseInt(document.getElementById('input_number').value, type);
    document.getElementById('result').innerHTML = `<td>${decimal.toString(2)}</td><td>${decimal.toString(4)}</td><td>${decimal.toString(8)}</td><td>${decimal.toString(10)}</td><td>${decimal.toString(16)}</td><td>${decimal.toString(32)}</td>`;
}

document.getElementById('input_number').addEventListener('keydown', function (event) {
    if (event.keyCode === 13) {
        convert()
    }
});
convert(1024);