Common.loadScript("/js/module/user/userService", () => {
    const MAIN_TARGET = $('#main');
    $('.btn-success').on('click', (e) => {
        if (!confirm("이대로 회원가입 하겠습니까?")) {
            return;
        }
        const userDto = {
            email: MAIN_TARGET.find("input#email").val() + "",
            name: MAIN_TARGET.find("input#name").val() + "",
            nickName: MAIN_TARGET.find("input#nickName").val() + "",
            password: MAIN_TARGET.find("input#password").val() + "",
            fromSite: MAIN_TARGET.find("").val() + ""
        };
        joinForm(userDto);
    });
    function joinForm(formData) {
        UserService.join(formData);
    }
});
//# sourceMappingURL=form.js.map