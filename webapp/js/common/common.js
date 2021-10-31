var Common;
(function (Common) {
    class ScriptLoader {
        static initDefault() {
            const _this = this;
            _this.nowLoadScript("/js/jquery-3.5.1.min", () => {
                _this.nowLoadScript("/js/bootstrap.min");
                _this.nowLoadScript("/js/scripts");
            });
        }
        static checkLoad(scriptPath) {
            return !!this.LOAD_LIST.find((scriptName) => scriptName === scriptPath);
        }
        static nowLoadScript(scriptPath, callBack) {
            const _this = this;
            try {
                const script = document.createElement('script');
                script.src = scriptPath + ".js";
                console.log(scriptPath + " 실행");
                if (!!callBack) {
                    script.onload = callBack;
                }
                document.getElementsByTagName('head')[0].appendChild(script);
                this.LOAD_LIST.push(scriptPath);
            }
            catch (error) {
                console.error(error);
            }
            return function (scriptPath, callBack) {
                return _this.nowLoadScript(scriptPath, callBack);
            };
        }
        static loadScript(scriptPath, callBack) {
            const _this = this;
            if (_this.checkLoad(scriptPath)) {
                console.log("이미 불러온 스크립트 : " + scriptPath);
                return;
            }
            window.addEventListener('load', (e) => {
                _this.nowLoadScript(scriptPath, callBack);
            });
            return function (scriptPath, callBack) {
                return loadScript(scriptPath, callBack);
            };
        }
    }
    ScriptLoader.LOAD_LIST = [];
    function loadScript(scriptPath, callBack) {
        ScriptLoader.loadScript(scriptPath, callBack);
    }
    Common.loadScript = loadScript;
    function initDefault() {
        ScriptLoader.initDefault();
    }
    Common.initDefault = initDefault;
})(Common || (Common = {}));
Common.initDefault();
//# sourceMappingURL=common.js.map