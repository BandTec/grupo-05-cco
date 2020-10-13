from services.mysql import Mysql
from services.dataGenerator import getData
from services.dataGenerator import alertaSlack
import time

#Inserir user, password, host, database
mysql = Mysql('ProjetoAPI','urubu100', 'localhost', 'PROJETOAPI')

mysql.connect()

while True:
    values = getData()
    mysql.insert(values)
    Slack = alertaSlack(values)
    print(Slack)
    time.sleep(2)
