@echo off
cd ..
echo [INFO] 调用eclipse 插件初始化eclipse 工程

call mvn eclipse:clean

call mvn eclipse:eclipse

call mvn clean install -U -Dmaven.test.skip=true

del /Q webapp\WEB-INF\lib\*.*
call mvn  dependency:copy-dependencies -DoutputDirectory=webapp/WEB-INF/lib -DincludeScope=runtime -Dsilent=true -DexcludeGroupIds=jspapi,javax.servlet

echo.
echo.

echo [INFO] eclipse配置已经生成，请在eclipse中导入整个目录
echo.
echo.
echo ================================ 提示=====================================
echo.
echo                         初次使用maven时
echo.
echo                         请在eclipse中增加M2_REPO变量
echo.
echo                         指向maven安装目录下的repository目录
echo.
pause
