console.log("join");

//전체선택 체크 시 동의3개 선택됨
$("#all").click(function(e){
    let ch = $(this).prop("checked");
    //all 체크박스 선택 시 true / 선택 해제 시 false
    console.log("all : ", ch);
    
    //ch일 때 class check를 하기
    $(".check").prop("checked", ch);
});

//동의 클릭 여부 콘솔로 확인
$(".check").click(function(){
    //all이 선택되어 있으면 true
    $("#all").prop("checked", true)

    $(".check").each(function(index, item){
        let ch = $(item).prop("checked") 
        
        if(!ch){
            $("#all").prop("checked", false);
            console.log("all : ", ch);
            return false;
        }
    });
});


// 검증 ==========================================
//아이디, 비번, 비번확인, 이름, 이메일
let results = [false, false, false, false, false]

//아이디 - 2글자 이상
$("#intputId").blur(function(){
    let result = nullCheck($("inputId").val(), $("#idCheck"), "ID");
    results[0] = result;
});

//비번 - 2글자 이상
// $("#inputPw").blur(function(){
//     let result = nullCheck($("inputPw").val());

//     if(result){
//         $("pwCheck").html("사용 가능한 비밀번호입니다.")
//     }else {
//         $("pwCheck").html("두 글자 이상 입력하세요.");
//     }
// });
//--------
// $("inputPw").blur(function(){
//     let result = nullCheck($("inputPw").val(), $("#pwCheck"), "PW");
//     results[1] = result;
// });

// $("inputPw").change(function(){
//     $("inputPwCheck").val("");
//     results[2]=false;
//     $("pwReCheck").html("");
// });
//--------
$("intputPw").on({
    blur:function(){
        let result = nullCheck($("inputPw").val(), $("#pwCheck"), "PW");
         results[1] = result;
    },
    change:function(){
        $("inputPwCheck").val("");
        results[2]=false;
         $("pwReCheck").html("");
    }
})






//비번확인 - 2글자 이상, 비번과 동일
$("#inputPwCheck").blur(function(){
    let result = nullCheck($("inputPw").val(), $("inputPwCheck").val());

    if(result){
        $("pwReCheck").html("사용 가능한 비밀번호입니다.")
    }else {
        $("pwReCheck").html("두 글자 이상 입력하세요.");
    }
});

//이름 - 2글자 이상
$("#inputName").blur(function(){
    let result = nullCheck($("inputName").val());

    if(result){
        $("nameCheck").html("사용 가능한 비밀번호입니다.")
    }else {
        $("nameCheck").html("두 글자 이상 입력하세요.");
    }
});

//이메일 - 2글자 이상
$("#inputEmail").blur(function(){
    let result = nullCheck($("inputEmail").val());

    if(result){
        $("emCheck").html("사용 가능한 비밀번호입니다.")
    }else {
        $("emCheck").html("두 글자 이상 입력하세요.");
    }
});


//회원가입 버튼 클릭 시 각 조건 충족여부 확인
$("#joinBtn").click(function(){

    if(results.includes(false)){
        alert("필수 입력 값을 입력해 주세요.")
    }else {
        console.log("success");
        //$("joinForm").submit();
    }




    // let c = true;
    // $.each(results, function(index, item){
    //     //하나라도 false일 때
    //     if(!item){
    //         alert("필수 입력 값을 입력해 주세요.")
    //         c = false;
    //         return c;
    //     }    
    // })

    // //submit event 강제실행
    // //event명(매개변수X);
    // if(c){
    //     $("#joinForm").submit();
    // }
})