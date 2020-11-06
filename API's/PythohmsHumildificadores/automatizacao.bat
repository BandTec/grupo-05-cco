@echo off

py -m pip install virtualenv
python -m pip install --user --upgrade pip

set /p ambientevirtual=Digite o nome do ambiente virtual em que deseja entrar:

if exist %ambientevirtual% (
    echo Essa maquina virtual ja existe, entao vamos adentra-la
    echo Entrando na maquina virtual
    @REM dir %ambientevirtual%
    @REM source .\%ambientevirtual%\Scripts\activate
    py -m pip install mysql-connector-python
    cd .\%ambientevirtual%\Scripts\
    call activate.bat
) else (
    echo esse nome de ambiente nao existe entao vamos criar um ambiente virtual
    py -m venv %ambientevirtual%
    py -m pip install psutil 
    py -m pip install mysql-connector-python 
    py -m pip install mysql-connector 
    py -m pip install mysql-connector-python-rf
    py -m pip install requests 
    py -m pip install pyodbc
)
