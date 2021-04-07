pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdn.bootcdn.net/ajax/libs/pdf.js/2.7.570/pdf.worker.min.js';

const preview = document.getElementById('preview');
const page_num = document.getElementById('page_num');
const out_type = document.getElementById('out_type');

let pdfFile, pdf, pageNum, context = preview.getContext('2d');

out_type.querySelectorAll('.button').forEach(function (btn) {
    btn.onclick = function () {
        out_type.querySelector('.primary').classList.remove('primary');
        btn.classList.add('primary');
    }
});

function loadPDF(file) {
    pdfFile = file;
    file_name.innerHTML = file.name;

    let reader = new FileReader();
    reader.onload = (e) => showPDF(e.target.result);
    reader.readAsDataURL(file);
}

function showPDF(url) {
    let loadingTask = pdfjsLib.getDocument(url);
    loadingTask.promise.then(function (doc) {
        pdf = doc;
        pageNum = 1;
        preview.hidden = false;
        readerPage()
    }, function (reason) {
        alert(reason)
    });
}

function prevPage() {
    if (pageNum <= 1) {
        return;
    }
    pageNum--;
    readerPage()
}

function nextPage() {
    if (pageNum >= pdf.numPages) {
        return;
    }
    pageNum++;
    readerPage()
}

function readerPage(callback) {
    pdf.getPage(pageNum).then(function (page) {
        let scale = 1.5;
        let viewport = page.getViewport({scale: scale});

        preview.height = viewport.height;
        preview.width = viewport.width;

        let renderContext = {
            canvasContext: context,
            viewport: viewport
        };
        page.render(renderContext).promise.then(callback);
    });
    page_num.innerText = `页码 : ${pageNum} / ${pdf.numPages}`;
}

function save() {
    let a = document.createElement('a');
    let event = new MouseEvent('click');
    let type = out_type.querySelector('.primary').innerText.toLowerCase();
    a.download = pdfFile.name + '-' + pageNum + '.' + type;
    a.href = preview.toDataURL(type === 'png' ? 'image/png' : 'image/jpeg');
    a.dispatchEvent(event)
}

function saveAll() {
    pageNum = 1;
    savePage()
}

function savePage() {
    if (pageNum > pdf.numPages) {
        alert('全部保存成功');
        return
    }

    readerPage(function () {
        save();
        pageNum++;
        savePage();
    });
}