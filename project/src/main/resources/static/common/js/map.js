const mapLib = {

    /**
     * 지도 로드 메서드
     * @param mapId: 지도를 출력할 요소 id이름
     * @param width: 지도 너비
     * @param height: 지도 높이
     * @param options - 옵션
     *                  - center: {lat: 위도, lng: 경도, ...} -> 필수항목
     *                  - zoom: 확대 정도(1~10) / 숫자가 작을수록 확대
     *                  - markerImage: 공통 마커 이미지 주소, 개별 마커 이미지가 있는 경우 그걸로 대체함
     *                  - marker: 배열 형태 [{lat: 위도, lng: 경도, info: html 데이터(인포윈도우), image: 이미지 주소 - 마커 이미지(공통 마커이미지, 개별마커이미지)}]
     */
    load(mapId, width = 300, height = 300, options){ //기본값 (300,300)
        const mapEl = document.getElementById(mapId);
        if(!mapEl || !options?.center) return;

        mapEl.style.width = `${width}px`;
        mapEl.style.height= `${height}px`; //스타일 속성으로 지도의 너비 높이 설정

        let { center, marker, markerImage } = options; //비구조할당
        /*
        *     const options = {
        center: {
            lat: 37.557756188912954,
            lng: 126.94062742683245,
        }
    }; 실제 구조 분해
        *
        * */

        //지도 가운데 좌표 처리 S
        //options -> 값 없을때 대비 optional 체이닝 연산 사용 - 값이 없어도 오류 나지 않도록
        const zoom = options?.zoom ?? 3; //기본값 3
        const position = new kakao.maps.LatLng(center.lat, center.lng); //좌표 객체에 위도 경도 값 넣음
        const map = new kakao.maps.Map(mapEl,{
            center: position, //가운데 지정
            level: zoom, //레벨 숫자가 높을수록 멀리보이고 숫자가 작으면 가깝게 보인다.
        });
        //지도 가운데 좌표 처리 E

        // 마커 출력 처리 S
        if (marker) {
            if (!Array.isArray(marker)) marker = [marker];

            const markers = marker.map(m => {
                const opt = { position: new kakao.maps.LatLng(m.lat, m.lng)};

                // 마커 이미지 처리
                // 마커 이미지가 있을 경우 쓰고 없을 경우 m.image
                const mi = markerImage ? markerImage : m.image;
                if (mi) {
                    const mImage = new kakao.maps.MarkerImage(mi, new kakao.maps.Size(64, 69), {offset: new kakao.maps.Point(27, 69)});
                    opt.image = mImage;
                }

                const _marker = new kakao.maps.Marker(opt);

                _marker.setMap(map);

                return _marker;
            });

        } // endif
        // 마커 출력 처리 E
    }
};