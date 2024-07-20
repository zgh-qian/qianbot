@echo off
setlocal EnableDelayedExpansion

REM 切换到父级目录下
pushd ..

REM 查找target目录下的所有jar文件
set count=0
for %%f in (target\*.jar) do (
    set /A count+=1
    set "jarfile[!count!]=%%~ff"  REM 存储完整路径
)

REM 如果找到多个jar文件，则提示用户并退出
if %count% NEQ 1 (
    echo Multiple or no jar files found in 'target' directory.
    pause
    exit /B
)

REM 取出唯一的jar文件路径
set "jar_path=!jarfile[1]!"

REM 切换回原来的目录
popd

REM 运行该jar文件
echo Running the only jar file found: "%jar_path%"
java -jar "%jar_path%"

pause
exit /B
