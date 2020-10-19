from services.mysql import Mysql
from services.resgatardados import PuxandoBanco
import time

#Inserir user, password, host, database
mysql = Mysql('root','bandtec', 'localhost', 'PROJETOAPI')

mysql.connect()

while True:
    teste =  PuxandoBanco()
    values = teste.data
    print(values)
    mysql.insert(values)
