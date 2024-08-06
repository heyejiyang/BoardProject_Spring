/**
 * 파일 업로드, 삭제, 조회 공통 기능
 */

const fileManager = {
    /**
     * 파일 업로드
     */
    upload(files, gid, location){ //선택한 파일 객체, gid, location

    },
    /**
     * 파일 삭제
     */
    delete(){

    },
    /**
     * 파일 조회
     */
    search(){

    }
};

//이벤트처리
window.addEventListener("DOMContentLoaded",function(){
    //업로드 버튼 class name: fileUploads 이벤트 처리 S
    const fileUploads = document.getElementsByClassName("fileUploads");
    const fileEl = document.createElement("input");

    fileEl.type  = 'file';
    //파일 여러개 선택하는 경우
    fileEl.multiple = true;

    for(const el of fileUploads){
        el.addEventListener("click",function (){
            fileEl.value = ""; //선택된 값 초기화
            delete fileEl.gid;
            delete fileEl.location;

            const dataset = this.dataset;
            fileEl.gid = dataset.gid;
            if(dataset.location) fileEl.location = dataset.location;
            fileEl.click();
        });
    }
    //파일 업로드 버튼 이벤트 처리 E

    //파일 업로드 처리
    fileEl.addEventListener("change",function (e) {
        const files = e.target.files;
        fileManager.upload(files, fileEl.gid, fileEl.location)
    });

});