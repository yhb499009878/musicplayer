(function() {
    // 框架缓存
    var cache = {};
    // 模板,格式如下：<%= %> 将数据上下文内容填充至模板，<% %> 书写JS脚本，模板中不可注释
    this.$tmpl$ = function $tmpl$(str, data) {
        // 如果模板存在则返回模板，如果模板不存在则生成并缓存模板
        var fn = !/\W/.test(str) ? cache[str] = cache[str]
            || $tmpl$(document.getElementById(str).innerHTML) :
            // 生成一个模板生成器
            new Function("obj",
                    "var p=[],print=function(){p.push.apply(p,arguments);};"
                    +
                    // 使用 with(){} 将“数据”引入为局部变量
                    "with(obj){p.push('" +
                    // 将模板转化为纯粹的JavaScript函数代码块
                    str.replace(/[\r\t\n]/g, " ").split("<%")
                        .join("\t").replace(/((^|%>)[^\t]*)'/g,
                        "$1\r").replace(/\t=(.*?)%>/g,
                        "',$1,'").split("\t")
                        .join("');").split("%>")
                        .join("p.push('").split("\r")
                        .join("\\'") + "');}return p.join('');");
        // 根据data参数决定返回“模板”还是“结果”
        return data ? fn(data) : fn;
    };
})();
