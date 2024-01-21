let scanner = new Instascan.Scanner({video: document.getElementById('video')});
Instascan.Camera.getCameras().then(function (cameras) {
    if (cameras.length > 0) {
        scanner.start(cameras[0]);
    } else {
        alert('Cameras found');
    }
}).catch(function (e) {
    console.error(e);
});

scanner.addListener("scan", function (qrcode) {
    // Chuyển người dùng đến trang controller khi quét thành công
    window.location.href =  `/ban-hang/them-gio-hang/${qrcode}`;
});



