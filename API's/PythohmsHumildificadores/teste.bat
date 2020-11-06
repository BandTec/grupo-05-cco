@echo off

py -m pip install virtualenv

::definindo o nome da ambiente
set /p nome=Coloque o nome do ambiente virtual:
set libs = texto

if exist %nome% (
    echo Essa maquina ja existe entao vamos prosseguir com a execucao
    ::entrando na pasta do ambiente
    cd %nome%/Scripts

    ::ativando o ambiente
    call ./activate.bat

    ::volta para as libs
    cd ../Lib/site-packages


    echo verificando se estao as libs instaladas
    for /D %%s in (.\*) do (
	call set "libs=%%libs%%, %%s"
    )
    
    echo.%libs:~2% | findstr "mysql">null && (
	echo.estao todas as libs
    ) || (
	echo.nenhuma lib encontrada, baixando elas
	py -m pip install psutil mysql-connector-python mysql-connector requests pyodbc
    )

    echo iniciando o ambiente virtual
    echo.
    cd ..
    py main.py

) else (
    echo esse nome de ambiente nao existe entao vamos criar um ambiente virtual

    ::criando o ambiente
    py -m venv %nome%

    ::entrando na pasta do ambiente
    cd %nome%/Scripts

    ::ativando o ambiente
    call ./activate.bat

    ::instalando as libs
    py -m pip install psutil mysql-connector-python mysql-connector requests pyodbc
)

::voltando pra raiz
cd ../..

echo %nome%/ >> .gitignore

::executando o script
py main.py
