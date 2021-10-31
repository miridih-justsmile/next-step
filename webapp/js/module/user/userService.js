var UserService;
(function (UserService) {
    class User {
        constructor(userDto) {
            this.userDto = userDto;
        }
        join() {
            $.ajax({
                method: "POST",
                url: "/api/user/create",
                contentType: "application/json",
                data: this.userDto
            }).always((data) => {
                console.log(data);
            });
        }
    }
    function join(user) {
        return new User(user).join();
    }
    UserService.join = join;
})(UserService || (UserService = {}));
//# sourceMappingURL=userService.js.map