
const commonLib = {
    /**
     * ajax 요청 공통 기능
     * @param url
     * @param method
     * @param data
     * @param headers
     * @param responseType : 응답 데이터 타입(text - text형태로, 그 외는 json형태로 응답)
     */
    //method는 기본값 설정
    ajaxLoad(url, method ="GET", data, headers, responseType){ //headers는 필수는 아니고 있을때만 추가
        if(!url){
            return;
        }

        const csrfToken = document.querySelector("meta[name='csrf_token']")?.content?.trim();
        //optional 체이닝 - null이거나 undefined 일때 오류 없이 그냥 끝나도록..
        const csrfHeader = document.querySelector("meta[name='csrf_header']")?.content?.trim();
        let rootUrl = document.querySelector("meta[name='rootUrl']")?.content?.trim() ?? '';
        rootUrl = rootUrl === '/' ? '' : rootUrl;

        url = location.protocol + "//" + location.host + rootUrl + url;

        method = method.toUpperCase();

        if(method === 'GET'){
            data = null;
        }

        if (data && !(data instanceof FormData) && typeof data !== 'string' && data instanceof Object) {
            data = JSON.stringify(data);
        }

        if(csrfHeader && csrfToken){
            headers = headers ?? {};
            headers[csrfHeader] = csrfToken;
        }

        const options = {
            method
        };
        if(data) options.body = data;
        if(headers) options.headers = headers;

        return new Promise((resolve, reject) => {
            fetch(url, options)
                .then(res => responseType === 'text' ? res.text() : res.json())// res.json() - JSON/ res.text() 텍스트
                //JSON으로 고정
                .then(data => resolve(data))//성공시 응답 데이터
                .catch(err => reject(err)); //실패시
        });
    }
};