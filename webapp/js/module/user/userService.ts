/// <reference path="../../common/common.ts" />
module UserService {
    class User {
        private readonly userDto : UserDto;
        constructor(userDto : UserDto) {
            this.userDto = userDto;
        }

        public join() {
            $.ajax({
                method : "POST",
                url : "/api/user/create",
                contentType : "application/json",
                data : this.userDto
            }).always((data) => {
                console.log(data);
            });
        }
    }
    export function join(user : UserDto) {
        return new User(user).join();
    }
}

interface UserDto {
    email : string,
    name : string,
    nickName : string,
    password : string,
    fromSite : string
}