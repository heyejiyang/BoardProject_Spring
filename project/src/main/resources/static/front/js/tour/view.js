const items = [
    [126.94062742683245, 37.557756188912954],
    [126.94120499658828, 37.557287959390024],
    [126.94069261563956, 37.561184514897825] //위도 경도 반대로 됨ㅎ
];

window.addEventListener("DOMContentLoaded", function() {
    const mapEl = document.getElementById("map");
    mapEl.style.width = "1000px";
    mapEl.style.height = "600px";

    // 처음 로딩될때 좌표 나오도록 설정
    const map = new kakao.maps.Map(mapEl, {
        center: new kakao.maps.LatLng(items[0][1], items[0][0]),
        level: 5,
    });
    const markers = items.map(pos => {
        const position = new kakao.maps.LatLng(pos[1], pos[0]);
        return new kakao.maps.Marker({position});
    });

    markers.forEach(marker => marker.setMap(map));
    // 지도 클릭시 좌표 정보
    kakao.maps.event.addListener(map, 'click', function(e) {
        const latLng = e.latLng;
        console.log(latLng);
        //const marker = new kakao.maps.Marker({
        //    position: latLng
        //});

        //marker.setMap(map);
    });
    const iwContent = '<h1>정보!</h1>';
    const iwPos = new kakao.maps.LatLng(items[0][1] + 0.02, items[0][0]);
    
    const infoWindow = new kakao.maps.InfoWindow({
        position: iwPos,
        content: iwContent
    });

    infoWindow.open(map,markers[0]);

    const removeEls = document.getElementsByClassName("remove");
    for (let i = 0; i < removeEls.length; i++) {
        removeEls[i].addEventListener("click", function() {
            markers[i].setMap(null);
        });
    }

   // let map;

    /*
    //현재 위치
    navigator.geolocation.getCurrentPosition((pos) => {
        const { latitude, longitude } = pos.coords;

        const mapOption = {
            center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
        };

        map = new kakao.maps.Map(mapEl, mapOption); // 지도를 생성

        // 마커가 표시될 위치
        const markerPos = new kakao.maps.LatLng(latitude, longitude);

        // 마커를 생성
        const marker = new kakao.maps.Marker({
            position: markerPos
        });

        // 마커가 지도 위에 표시되도록 설정 (현재 위치)
        marker.setMap(map);
        mapProcess(map);
    });
    */

    // 지도 클릭시 좌표 정보
    /*
    if (map) {
        kakao.maps.event.addListener(map, 'click', function(e) {
            console.log(e);
        });

    } // endif
    */
    /*
    function mapProcess(map) {
        // 지도 클릭시 좌표 정보
        kakao.maps.event.addListener(map, 'click', function(e) {
           const latLng = e.latLng;
           const marker = new kakao.maps.Marker({
               position: latLng
           });
           marker.setMap(map); //클릭시 마커 여러개 표시
        });
    }
    */
});