/// <reference path="../../../node_modules/@types/jquery/JQuery.d.ts" />
module Common {
    class ScriptLoader {
        private static LOAD_LIST : string[] = [];

        public static initDefault() {
            const _this = this;
            _this.nowLoadScript("/js/jquery-3.5.1.min", () => {
                _this.nowLoadScript("/js/bootstrap.min");
                _this.nowLoadScript("/js/scripts");
            });
        }

        private static checkLoad(scriptPath : string) : boolean {
            return !!this.LOAD_LIST.find((scriptName) => scriptName === scriptPath);
        }

        private static nowLoadScript(scriptPath : string, callBack? : ((this: GlobalEventHandlers, ev: Event) => any)) {
            const _this = this;
            try {
                const script : HTMLScriptElement = document.createElement('script');
                script.src = scriptPath + ".js";
                console.log(scriptPath + " 실행");
                if (!!callBack) {
                    script.onload = callBack;
                }
                document.getElementsByTagName('head')[0].appendChild(script);
                this.LOAD_LIST.push(scriptPath);
            } catch (error) {
                console.error(error);
            }

            return function (scriptPath : string, callBack? : ((this: GlobalEventHandlers, ev: Event) => any)) {
                return _this.nowLoadScript(scriptPath, callBack);
            }
        }

        public static loadScript(scriptPath : string, callBack? : ((this: GlobalEventHandlers, ev: Event) => any)) {
            const _this = this;
            if (_this.checkLoad(scriptPath)) {
                console.log("이미 불러온 스크립트 : " + scriptPath);
                return;
            }

            window.addEventListener('load', (e) => {
                _this.nowLoadScript(scriptPath, callBack);
            })

            return function (scriptPath : string, callBack? : ((this: GlobalEventHandlers, ev: Event) => any)) {
                return loadScript(scriptPath, callBack);
            }
        }
    }

    export function loadScript(scriptPath : string, callBack? : ((this: GlobalEventHandlers, ev: Event) => any)) {
        ScriptLoader.loadScript(scriptPath, callBack);
    }

    export function initDefault() {
        ScriptLoader.initDefault();
    }
}
Common.initDefault();